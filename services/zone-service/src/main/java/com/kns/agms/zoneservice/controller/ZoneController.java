package com.kns.agms.zoneservice.controller;

import com.kns.agms.zoneservice.dto.ZoneRequest;
import com.kns.agms.zoneservice.entity.Zone;
import com.kns.agms.zoneservice.service.ZoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/zones")
@RequiredArgsConstructor

public class ZoneController {

    private final ZoneService service;

    @PostMapping
    public ResponseEntity<Zone> createZone(
            @RequestBody ZoneRequest request,
            @RequestHeader("Authorization") String token) {

        token = token.replace("Bearer ", "");

        return ResponseEntity.ok(service.createZone(request, token));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Zone> getZone(@PathVariable Long id) {
        return ResponseEntity.ok(service.getZone(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Zone> updateZone(
            @PathVariable Long id,
            @RequestBody ZoneRequest request) {

        return ResponseEntity.ok(service.updateZone(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteZone(@PathVariable Long id) {
        service.deleteZone(id);
        return ResponseEntity.noContent().build();
    }
}