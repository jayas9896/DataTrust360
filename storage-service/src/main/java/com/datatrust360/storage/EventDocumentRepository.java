package com.datatrust360.storage;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventDocumentRepository extends MongoRepository<EventDocument, String> {
}
