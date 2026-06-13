package com.koushik.serviceofferingservice.service.impl;

import com.koushik.serviceofferingservice.model.ServiceOffering;
import com.koushik.serviceofferingservice.payload.dto.CategoryDto;
import com.koushik.serviceofferingservice.payload.dto.SalonDto;
import com.koushik.serviceofferingservice.payload.dto.ServiceOfferingDto;
import com.koushik.serviceofferingservice.repository.ServiceOfferingRepository;
import com.koushik.serviceofferingservice.service.ServiceOfferingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class ServiceOfferingServiceImpl implements ServiceOfferingService {

    private final ServiceOfferingRepository serviceOfferingRepository;

    @Override
    public ServiceOffering createService(ServiceOfferingDto serviceOfferingDto, SalonDto salonDto, CategoryDto categoryDto) {
        return null;
    }

    @Override
    public ServiceOffering updateService(Long ServiceId, ServiceOfferingDto serviceOfferingDto) {
        return null;
    }

    @Override
    public Set<ServiceOffering> getAllServiceBySalonId(Long salonId, Long categoryId) {
        return Set.of();
    }

    @Override
    public Set<ServiceOffering> getAllServiceById(Set<Long> ids) {
        return Set.of();
    }
}
