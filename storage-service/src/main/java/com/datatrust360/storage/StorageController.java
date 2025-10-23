package com.datatrust360.storage;

import com.datatrust360.common.EventEnvelope;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST API for tenant metadata and event payload storage access.
 *
 * <p>Importance: Exposes storage operations for admin and operational workflows.</p>
 * <p>Alternatives: Access repositories directly from other services, but HTTP APIs
 * enforce boundaries and enable reuse.</p>
 */
@RestController
@RequestMapping("/api/v1/storage")
@Tag(name = "Storage")
public class StorageController {

    private final TenantRepository tenantRepository;
    private final EventDocumentRepository eventRepository;

    /**
     * Creates the storage controller with repository dependencies.
     *
     * <p>Importance: Enables clean injection and unit testing of storage endpoints.</p>
     * <p>Alternatives: Instantiate repositories manually, but DI is standard in Spring.</p>
     */
    public StorageController(TenantRepository tenantRepository, EventDocumentRepository eventRepository) {
        this.tenantRepository = tenantRepository;
        this.eventRepository = eventRepository;
    }

    /**
     * Returns all tenants stored in SQL.
     *
     * <p>Importance: Enables admin oversight of tenant inventory.</p>
     * <p>Alternatives: Provide paged endpoints only, but full list is adequate for MVP.</p>
     */
    @GetMapping("/tenants")
    @Operation(summary = "List tenants stored in SQL")
    public List<Tenant> tenants() {
        return tenantRepository.findAll();
    }

    /**
     * Creates a tenant stored in SQL.
     *
     * <p>Importance: Allows onboarding of new organizations.</p>
     * <p>Alternatives: Provision tenants via scripts, but APIs enable automation.</p>
     */
    @PostMapping("/tenants")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create tenant stored in SQL")
    public Tenant createTenant(@RequestBody Tenant tenant) {
        return tenantRepository.save(tenant);
    }

    /**
     * Persists an event payload into NoSQL storage.
     *
     * <p>Importance: Retains raw events for investigation and downstream analytics.</p>
     * <p>Alternatives: Store only aggregated results, but raw payloads are critical for forensics.</p>
     */
    @PostMapping("/events")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Store event payload in NoSQL")
    public EventDocument createEvent(@RequestBody EventEnvelope envelope) {
        return eventRepository.save(toDocument(envelope));
    }

    /**
     * Retrieves a single event payload document by ID.
     *
     * <p>Importance: Supports investigation and forensic analysis of raw events.</p>
     * <p>Alternatives: Search by filters only, but direct ID retrieval is faster for drill-downs.</p>
     */
    @GetMapping("/events/{id}")
    @Operation(summary = "Get event payload from NoSQL")
    public EventDocument eventById(@PathVariable String id) {
        return eventRepository.findById(id).orElseThrow();
    }

    /**
     * Maps an event envelope into a MongoDB document.
     *
     * <p>Importance: Keeps a single mapping definition for storage consistency.</p>
     * <p>Alternatives: Inline mapping per endpoint, but that duplicates logic.</p>
     */
    private EventDocument toDocument(EventEnvelope envelope) {
        EventDocument document = new EventDocument();
        document.setTenantId(envelope.getTenantId());
        document.setSource(envelope.getSource());
        document.setSchemaVersion(envelope.getSchemaVersion());
        document.setReceivedAt(envelope.getReceivedAt());
        document.setPayload(envelope.getPayload());
        return document;
    }
}
