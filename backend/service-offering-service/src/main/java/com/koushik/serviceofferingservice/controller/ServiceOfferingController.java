package com.koushik.serviceofferingservice.controller;

import com.koushik.serviceofferingservice.model.ServiceOffering;
import com.koushik.serviceofferingservice.service.ServiceOfferingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/service-offering")
@RequiredArgsConstructor
public class ServiceOfferingController {
    private final ServiceOfferingService serviceOfferingService;


    @GetMapping("/salon/{salonId}")
    public ResponseEntity<Set<ServiceOffering>> getServicesBySalonId(@PathVariable Long salonId, @RequestParam(required = false) Long categoryId) throws Exception{
        Set<ServiceOffering> allServiceBySalonId = serviceOfferingService.getAllServiceBySalonId(salonId, categoryId);
        return ResponseEntity.ok(allServiceBySalonId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceOffering> getServiceId(@PathVariable Long id) throws Exception{
        ServiceOffering ServiceById = serviceOfferingService.getServiceById(id);
        return ResponseEntity.ok(ServiceById);
    }

    @GetMapping("/list/{ids}")
    public ResponseEntity<Set<ServiceOffering>> getServicesByIds(@PathVariable Set<Long> ids) throws Exception{
        Set<ServiceOffering> allServiceBySalonId = serviceOfferingService.getAllServiceById(ids);
        return ResponseEntity.ok(allServiceBySalonId);
    }

}
