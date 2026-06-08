package com.koushik.salonservice.service.impl;

import com.koushik.salonservice.model.Salon;
import com.koushik.salonservice.payload.dto.SalonDto;
import com.koushik.salonservice.payload.dto.UserDto;
import com.koushik.salonservice.repository.SalonRepository;
import com.koushik.salonservice.service.SalonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SalonServiceImpl implements SalonService {

    private final SalonRepository salonRepository;

    @Override
    public Salon createSalon(SalonDto salon, UserDto user) {
        return null;
    }

    @Override
    public Salon updateSalon(SalonDto salon, UserDto user, Long salonId) {
        return null;
    }

    @Override
    public List<Salon> getAllSalons() {
        return List.of();
    }

    @Override
    public Salon getSalonBySalonId(Long salonId) {
        return null;
    }

    @Override
    public Salon getSalonByOwnerId(Long ownerId) {
        return null;
    }

    @Override
    public List<Salon> searchSalonByCity(String city) {
        return List.of();
    }
}
