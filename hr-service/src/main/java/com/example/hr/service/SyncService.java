package com.example.hr.service;

import com.example.hr.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SyncService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${payroll.service.url}")
    private String payrollServiceUrl;

    public void sendSyncEvent(String eventType, Employee employee) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("event", eventType);
        payload.put("data", employee);

        try {
            restTemplate.postForEntity(payrollServiceUrl + "/sync/employees", payload, Void.class);
        } catch (Exception ex) {
            System.err.println("❌ Ошибка синхронизации: " + ex.getMessage());
        }
    }
}
