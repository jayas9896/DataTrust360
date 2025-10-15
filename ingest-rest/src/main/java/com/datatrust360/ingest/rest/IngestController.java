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

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Ingestion")
public class IngestController {

    private final IngestService ingestService;

    public IngestController(IngestService ingestService) {
        this.ingestService = ingestService;
    }

    @PostMapping("/events")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Ingest operational events via REST and forward to Kafka")
    public void ingest(@RequestBody EventEnvelope envelope) {
        ingestService.publishToKafka(envelope);
    }

    @PostMapping("/partners/events")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Partner bulk ingestion via REST and forward to Kinesis")
    public void ingestPartner(@RequestBody EventEnvelope envelope) {
        ingestService.publishToKinesis(envelope);
    }
}
