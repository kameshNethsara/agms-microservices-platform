package com.kns.agms.zoneservice.client;

import com.kns.agms.zoneservice.dto.DeviceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name = "external-iot", url = "http://104.211.95.241:8080/api")
public interface IoTClient {

    @PostMapping("/devices")
    DeviceResponse registerDevice(
            @RequestHeader("Authorization") String token,
            @RequestBody Map<String, String> request
    );
}