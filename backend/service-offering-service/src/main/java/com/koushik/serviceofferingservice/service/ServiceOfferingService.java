package com.koushik.serviceofferingservice.service;


import com.koushik.serviceofferingservice.model.ServiceOffering;
import com.koushik.serviceofferingservice.payload.dto.CategoryDto;
import com.koushik.serviceofferingservice.payload.dto.SalonDto;
import com.koushik.serviceofferingservice.payload.dto.ServiceOfferingDto;

import java.util.Set;

public interface ServiceOfferingService {

    ServiceOffering createService(ServiceOfferingDto serviceOfferingDto, SalonDto salonDto, CategoryDto categoryDto);

    ServiceOffering updateService(Long ServiceId, ServiceOfferingDto serviceOfferingDto) throws Exception;

    Set<ServiceOffering> getAllServiceBySalonId(Long salonId, Long categoryId);

    Set<ServiceOffering> getAllServiceById(Set<Long> ids);

    ServiceOffering getServiceById(Long id) throws Exception;

}
