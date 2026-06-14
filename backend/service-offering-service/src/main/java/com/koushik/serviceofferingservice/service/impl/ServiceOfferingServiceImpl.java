package com.koushik.serviceofferingservice.service.impl;

import com.koushik.serviceofferingservice.model.ServiceOffering;
import com.koushik.serviceofferingservice.payload.dto.CategoryDto;
import com.koushik.serviceofferingservice.payload.dto.SalonDto;
import com.koushik.serviceofferingservice.payload.dto.ServiceOfferingDto;
import com.koushik.serviceofferingservice.repository.ServiceOfferingRepository;
import com.koushik.serviceofferingservice.service.ServiceOfferingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ServiceOfferingServiceImpl implements ServiceOfferingService {

    private final ServiceOfferingRepository serviceOfferingRepository;

    @Override
    public ServiceOffering createService(ServiceOfferingDto serviceOfferingDto, SalonDto salonDto, CategoryDto categoryDto) {

        log.info("Creating service payload. ServiceOfferingDto={}, SalonDto={}, CategoryDto={}", serviceOfferingDto, salonDto, categoryDto);

        ServiceOffering newServiceOffering = new ServiceOffering();

        newServiceOffering.setName(serviceOfferingDto.getName());
        newServiceOffering.setDescription(serviceOfferingDto.getDescription());
        newServiceOffering.setImage(serviceOfferingDto.getImage());
        newServiceOffering.setSalonId(salonDto.getId());
        newServiceOffering.setCategoryId(categoryDto.getId());
        newServiceOffering.setPrice(serviceOfferingDto.getPrice());
        newServiceOffering.setDuration(serviceOfferingDto.getDuration());

        ServiceOffering savedService = serviceOfferingRepository.save(newServiceOffering);

        log.info("Service created successfully with id={}", savedService.getId());

        return savedService;
    }

    @Override
    public ServiceOffering updateService(Long ServiceId, ServiceOfferingDto serviceOfferingDto) throws Exception {

        log.info("Updating service payload. serviceId={}, payload={}", ServiceId, serviceOfferingDto);

        Optional<ServiceOffering> existingServiceOfferingOptional = serviceOfferingRepository.findById(ServiceId);
        if (existingServiceOfferingOptional.isEmpty()) {
            throw new Exception("Service does not exist");
        }

        ServiceOffering existingServiceOffering = existingServiceOfferingOptional.get();

        existingServiceOffering.setName(serviceOfferingDto.getName());
        existingServiceOffering.setDescription(serviceOfferingDto.getDescription());
        existingServiceOffering.setImage(serviceOfferingDto.getImage());
        existingServiceOffering.setPrice(serviceOfferingDto.getPrice());
        existingServiceOffering.setDuration(serviceOfferingDto.getDuration());

        ServiceOffering updatedService = serviceOfferingRepository.save(existingServiceOffering);

        log.info("Service updated successfully. serviceId={}", updatedService.getId());

        return updatedService;
    }

    @Override
    public Set<ServiceOffering> getAllServiceBySalonId(Long salonId, Long categoryId) {
        Set<ServiceOffering> serviceOfferings = serviceOfferingRepository.findBySalonId(salonId);


        if(categoryId != null){
            serviceOfferings = serviceOfferings.stream().filter((service)->{
               return service.getCategoryId()!=null && service.getCategoryId().equals(categoryId);
            }).collect(Collectors.toSet());
        }

        return serviceOfferings;
    }

    @Override
    public Set<ServiceOffering> getAllServiceById(Set<Long> ids) {
        List<ServiceOffering> serviceOfferingList = serviceOfferingRepository.findAllById(ids);
        return new HashSet<>(serviceOfferingList);
    }

    @Override
    public ServiceOffering getServiceById(Long id) throws Exception {
        Optional<ServiceOffering> existingServiceOfferingOptional = serviceOfferingRepository.findById(id);
        if (existingServiceOfferingOptional.isEmpty()) {
            throw new Exception("Service does not exist");
        }

        return existingServiceOfferingOptional.get();
    }
}
