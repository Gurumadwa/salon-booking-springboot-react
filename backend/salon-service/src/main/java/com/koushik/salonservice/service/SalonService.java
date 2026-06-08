package com.koushik.salonservice.service;

import com.koushik.salonservice.model.Salon;
import com.koushik.salonservice.payload.dto.SalonDto;
import com.koushik.salonservice.payload.dto.UserDto;

import java.util.List;
public interface SalonService {

    Salon createSalon(SalonDto salon, UserDto user);

    Salon updateSalon(SalonDto salon, UserDto user, Long salonId) throws Exception;

    List<Salon> getAllSalons();

    Salon getSalonBySalonId(Long salonId) throws Exception;

    Salon getSalonByOwnerId(Long ownerId);

    List<Salon> searchSalonByCity(String city);

}
