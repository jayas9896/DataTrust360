package com.datatrust360.storage;

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

@RestController
@RequestMapping("/api/v1/storage")
@Tag(name = "Storage")
public class StorageController {

    private final TenantRepository tenantRepository;
    private final EventDocumentRepository eventRepository;

    public StorageController(TenantRepository tenantRepository, EventDocumentRepository eventRepository) {
        this.tenantRepository = tenantRepository;
        this.eventRepository = eventRepository;
    }

    @GetMapping("/tenants")
    @Operation(summary = "List tenants stored in SQL")
    public List<Tenant> tenants() {
        return tenantRepository.findAll();
    }

    @PostMapping("/tenants")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create tenant stored in SQL")
    public Tenant createTenant(@RequestBody Tenant tenant) {
        return tenantRepository.save(tenant);
    }

    @GetMapping("/events/{id}")
    @Operation(summary = "Get event payload from NoSQL")
    public EventDocument eventById(@PathVariable String id) {
        return eventRepository.findById(id).orElseThrow();
    }
}
