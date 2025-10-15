package com.datatrust360.ingest.rest;

import com.datatrust360.common.EventEnvelope;

public interface IngestService {
    void publishToKafka(EventEnvelope envelope);
    void publishToKinesis(EventEnvelope envelope);
}
