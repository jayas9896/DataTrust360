package com.datatrust360.storage;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * MongoDB repository for event payload documents.
 *
 * <p>Importance: Enables retrieval of dynamic event payloads without manual queries.</p>
 * <p>Alternatives: Use the MongoTemplate directly, but repository interfaces are simpler.</p>
 */
public interface EventDocumentRepository extends MongoRepository<EventDocument, String> {
}
