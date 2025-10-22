package com.datatrust360.processing;

import com.datatrust360.common.EventEnvelope;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.kinesis.KinesisClient;
import software.amazon.awssdk.services.kinesis.model.GetRecordsRequest;
import software.amazon.awssdk.services.kinesis.model.GetRecordsResponse;
import software.amazon.awssdk.services.kinesis.model.GetShardIteratorRequest;
import software.amazon.awssdk.services.kinesis.model.ListShardsRequest;
import software.amazon.awssdk.services.kinesis.model.ListShardsResponse;
import software.amazon.awssdk.services.kinesis.model.Record;
import software.amazon.awssdk.services.kinesis.model.Shard;
import software.amazon.awssdk.services.kinesis.model.ShardIteratorType;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Polls Kinesis for partner ingestion events and forwards them for storage and scoring.
 *
 * <p>Importance: Separates partner/edge ingestion (Kinesis) from internal Kafka streams.</p>
 * <p>Alternatives: Use Kinesis Client Library, but a lightweight poller keeps the MVP simple.</p>
 */
@Component
public class KinesisIngestPoller {

    private static final Logger logger = LoggerFactory.getLogger(KinesisIngestPoller.class);

    private final KinesisClient kinesisClient;
    private final ObjectMapper objectMapper;
    private final StorageClient storageClient;
    private final PartnerQueuePublisher queuePublisher;
    private final String streamName;
    private final boolean enabled;
    private final Map<String, String> shardIterators = new ConcurrentHashMap<>();

    /**
     * Creates the Kinesis poller with dependencies and configuration.
     *
     * <p>Importance: Ensures ingestion stays configurable per environment.</p>
     * <p>Alternatives: Hardcode stream names, but configuration is required for multi-env setups.</p>
     */
    public KinesisIngestPoller(
        KinesisClient kinesisClient,
        ObjectMapper objectMapper,
        StorageClient storageClient,
        PartnerQueuePublisher queuePublisher,
        @Value("${processing.kinesis.stream}") String streamName,
        @Value("${processing.kinesis.enabled:true}") boolean enabled
    ) {
        this.kinesisClient = kinesisClient;
        this.objectMapper = objectMapper;
        this.storageClient = storageClient;
        this.queuePublisher = queuePublisher;
        this.streamName = streamName;
        this.enabled = enabled;
    }

    /**
     * Polls Kinesis on a fixed schedule to fetch new records.
     *
     * <p>Importance: Provides a simple, deterministic ingestion loop for partner data.</p>
     * <p>Alternatives: Event-driven consumers, but polling is acceptable for a local MVP.</p>
     */
    @Scheduled(fixedDelayString = "${processing.kinesis.poll-delay-ms:5000}")
    public void poll() {
        if (!enabled) {
            return;
        }
        List<Shard> shards = listShards();
        for (Shard shard : shards) {
            String iterator = shardIterators.computeIfAbsent(
                shard.shardId(),
                id -> initialIterator(id)
            );
            if (iterator == null) {
                continue;
            }
            GetRecordsResponse records = kinesisClient.getRecords(
                GetRecordsRequest.builder()
                    .shardIterator(iterator)
                    .limit(100)
                    .build()
            );
            handleRecords(records.records());
            shardIterators.put(shard.shardId(), records.nextShardIterator());
        }
    }

    /**
     * Lists shards for the configured stream.
     *
     * <p>Importance: Allows polling across all active shards.</p>
     * <p>Alternatives: Poll a single shard only, but that would miss partner traffic.</p>
     */
    private List<Shard> listShards() {
        ListShardsResponse response = kinesisClient.listShards(
            ListShardsRequest.builder()
                .streamName(streamName)
                .build()
        );
        return response.shards();
    }

    /**
     * Creates an initial shard iterator.
     *
     * <p>Importance: Starts consumption from the earliest available records for learning demos.</p>
     * <p>Alternatives: Start at LATEST to reduce backlog, but TRIM_HORIZON showcases full ingestion.</p>
     */
    private String initialIterator(String shardId) {
        return kinesisClient.getShardIterator(
            GetShardIteratorRequest.builder()
                .streamName(streamName)
                .shardId(shardId)
                .shardIteratorType(ShardIteratorType.TRIM_HORIZON)
                .build()
        ).shardIterator();
    }

    /**
     * Processes Kinesis records by persisting and enqueueing for scoring.
     *
     * <p>Importance: Ensures partner events flow through the same storage and scoring pipeline.</p>
     * <p>Alternatives: Separate partner pipeline, but unified processing reduces duplication.</p>
     */
    private void handleRecords(List<Record> records) {
        for (Record record : records) {
            String json = StandardCharsets.UTF_8.decode(record.data().asByteBuffer()).toString();
            try {
                EventEnvelope envelope = objectMapper.readValue(json, EventEnvelope.class);
                storageClient.persistEvent(envelope);
                queuePublisher.enqueue(json);
            } catch (Exception ex) {
                logger.error("Failed to process Kinesis record", ex);
            }
        }
    }
}
