package com.kns.agms.zoneservice.controller;



import com.kns.agms.zoneservice.entity.Zone;
import com.kns.agms.zoneservice.repository.ZoneRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/zones")

public class ZoneController {
    private final ZoneRepository zoneRepository;

    public ZoneController(ZoneRepository zoneRepository) {
        this.zoneRepository = zoneRepository;
    }

    // Create Zone
    @PostMapping
    public Zone createZone(@RequestBody Zone zone) {
        return zoneRepository.save(zone);
    }

    // Get All Zones
    @GetMapping
    public List<Zone> getAllZones() {
        return zoneRepository.findAll();
    }

    // Get Zone By ID
    @GetMapping("/{id}")
    public Zone getZoneById(@PathVariable Long id) {
        return zoneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Zone not found"));
    }

    // Update Zone
    @PutMapping("/{id}")
    public Zone updateZone(@PathVariable Long id, @RequestBody Zone updatedZone) {
        Zone zone = zoneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Zone not found"));

        zone.setName(updatedZone.getName());
        zone.setMinTemperature(updatedZone.getMinTemperature());
        zone.setMaxTemperature(updatedZone.getMaxTemperature());
        zone.setMinHumidity(updatedZone.getMinHumidity());
        zone.setMaxHumidity(updatedZone.getMaxHumidity());

        return zoneRepository.save(zone);
    }

    // Delete Zone
    @DeleteMapping("/{id}")
    public String deleteZone(@PathVariable Long id) {
        zoneRepository.deleteById(id);
        return "Zone deleted successfully";
    }
}
