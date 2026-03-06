package com.kns.agms.zoneservice.service.impl;

import com.kns.agms.zoneservice.client.IoTClient;
import com.kns.agms.zoneservice.dto.DeviceResponse;
import com.kns.agms.zoneservice.dto.ZoneRequest;
import com.kns.agms.zoneservice.entity.Zone;
import com.kns.agms.zoneservice.repository.ZoneRepository;
import com.kns.agms.zoneservice.service.ZoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ZoneServiceImpl implements ZoneService {

    private final ZoneRepository repository;
    private final IoTClient ioTClient;

    @Override
    public Zone createZone(ZoneRequest request, String token) {

        // Business Validation
        if (request.getMinTemperature() >= request.getMaxTemperature()) {
            throw new RuntimeException("minTemperature must be less than maxTemperature");
        }

        if (request.getMinHumidity() >= request.getMaxHumidity()) {
            throw new RuntimeException("minHumidity must be less than maxHumidity");
        }

        // Register device in external IoT API
        Map<String, String> deviceRequest = new HashMap<>();
        deviceRequest.put("name", request.getName() + "-Sensor");
        deviceRequest.put("zoneId", request.getName());

        DeviceResponse deviceResponse =
                ioTClient.registerDevice("Bearer " + token, deviceRequest);

        // Save Zone
        Zone zone = Zone.builder()
                .name(request.getName())
                .minTemperature(request.getMinTemperature())
                .maxTemperature(request.getMaxTemperature())
                .minHumidity(request.getMinHumidity())
                .maxHumidity(request.getMaxHumidity())
                .deviceId(deviceResponse.getDeviceId())
                .build();

        return repository.save(zone);
    }

    @Override
    public Zone getZone(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Zone not found"));
    }

    @Override
    public Zone updateZone(Long id, ZoneRequest request) {

        Zone zone = getZone(id);

        zone.setMinTemperature(request.getMinTemperature());
        zone.setMaxTemperature(request.getMaxTemperature());
        zone.setMinHumidity(request.getMinHumidity());
        zone.setMaxHumidity(request.getMaxHumidity());

        return repository.save(zone);
    }

    @Override
    public void deleteZone(Long id) {
        repository.deleteById(id);
    }
}