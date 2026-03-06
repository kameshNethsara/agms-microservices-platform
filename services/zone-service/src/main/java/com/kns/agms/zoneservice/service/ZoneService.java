package com.kns.agms.zoneservice.service;

import com.kns.agms.zoneservice.dto.ZoneRequest;
import com.kns.agms.zoneservice.entity.Zone;

public interface ZoneService {

    Zone createZone(ZoneRequest request, String token);
    Zone getZone(Long id);
    Zone updateZone(Long id, ZoneRequest request);
    void deleteZone(Long id);
}