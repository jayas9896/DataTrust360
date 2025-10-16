package com.datatrust360.ingest.rest;

import com.datatrust360.common.EventEnvelope;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST API for ingesting operational events into streaming pipelines.
 *
 * <p>Importance: Provides a standard HTTP ingestion path for most clients.</p>
 * <p>Alternatives: Force all clients to use gRPC, but REST remains accessible
 * for web and legacy systems.</p>
 */
@RestController
@RequestMapping("/api/v1")
@Tag(name = "Ingestion")
public class IngestController {

    private final IngestService ingestService;

    /**
     * Creates the controller with ingestion service dependency.
     *
     * <p>Importance: Enables testable injection of publish logic.</p>
     * <p>Alternatives: Instantiate service directly, but that makes unit testing harder.</p>
     */
    public IngestController(IngestService ingestService) {
        this.ingestService = ingestService;
    }

    /**
     * Ingests events via REST and publishes to Kafka.
     *
     * <p>Importance: Kafka provides low-latency internal streaming and replayability.</p>
     * <p>Alternatives: Write directly to storage, but streaming enables async processing.</p>
     */
    @PostMapping("/events")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Ingest operational events via REST and forward to Kafka")
    public void ingest(@RequestBody EventEnvelope envelope) {
        ingestService.publishToKafka(envelope);
    }

    /**
     * Ingests partner events via REST and publishes to Kinesis.
     *
     * <p>Importance: Kinesis supports partner/edge ingestion with AWS-native scaling.</p>
     * <p>Alternatives: Use Kafka for all producers, but Kinesis provides managed partner ingest.</p>
     */
    @PostMapping("/partners/events")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Partner bulk ingestion via REST and forward to Kinesis")
    public void ingestPartner(@RequestBody EventEnvelope envelope) {
        ingestService.publishToKinesis(envelope);
    }
}
