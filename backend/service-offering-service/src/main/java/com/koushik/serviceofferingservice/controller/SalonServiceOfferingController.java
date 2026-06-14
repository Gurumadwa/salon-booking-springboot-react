package com.koushik.serviceofferingservice.controller;

import com.koushik.serviceofferingservice.model.ServiceOffering;
import com.koushik.serviceofferingservice.payload.dto.CategoryDto;
import com.koushik.serviceofferingservice.payload.dto.SalonDto;
import com.koushik.serviceofferingservice.payload.dto.ServiceOfferingDto;
import com.koushik.serviceofferingservice.service.ServiceOfferingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/service-offering/salon-owner")
@RequiredArgsConstructor
public class SalonServiceOfferingController {

    private final ServiceOfferingService serviceOfferingService;

    @PostMapping
    public ResponseEntity<ServiceOffering> createServiceOffering( @RequestBody ServiceOfferingDto serviceOfferingDto) {
        SalonDto salonDto = new SalonDto();
        salonDto.setId(1L);

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(serviceOfferingDto.getCategoryId());

        ServiceOffering service = serviceOfferingService.createService(serviceOfferingDto, salonDto, categoryDto);

        return ResponseEntity.ok(service);
    }

    @PostMapping("/{id}")
    public ResponseEntity<ServiceOffering> createServiceOffering(@PathVariable Long id, @RequestBody ServiceOfferingDto serviceOfferingDto) throws Exception {

        ServiceOffering service = serviceOfferingService.updateService(id, serviceOfferingDto);

        return ResponseEntity.ok(service);
    }

}
