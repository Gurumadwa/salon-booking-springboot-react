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
    public Salon createSalon(SalonDto reqData, UserDto user) {

        Salon salon = new Salon();

        salon.setName(reqData.getName());
        salon.setEmail(reqData.getEmail());
        salon.setCity(reqData.getCity());
        salon.setAddress(reqData.getAddress());
        salon.setImages(reqData.getImages());
        salon.setOpenTime(reqData.getOpenTime());
        salon.setCloseTime(reqData.getCloseTime());
        salon.setOwnerId(user.getId());                 //set id from user dto
        salon.setPhoneNumber(reqData.getPhoneNumber());

        return salonRepository.save(salon);
    }

    @Override
    public Salon updateSalon(SalonDto salon, UserDto user, Long salonId) throws Exception {

        Salon existingSalon = salonRepository.findById(salonId).orElse(null);

        if(!existingSalon.getOwnerId().equals(user.getId())){
            throw new Exception("You are not authorized to update this salon");
        }

        if(existingSalon != null){
            existingSalon.setName(salon.getName());
            existingSalon.setCity(salon.getCity());
            existingSalon.setAddress(salon.getAddress());
            existingSalon.setEmail(salon.getEmail());
            existingSalon.setImages(salon.getImages());
            existingSalon.setOwnerId(user.getId());             //set id from user dto
            existingSalon.setPhoneNumber(salon.getPhoneNumber());
            existingSalon.setOpenTime(salon.getOpenTime());
            existingSalon.setCloseTime(salon.getCloseTime());

            return salonRepository.save(existingSalon);
        }
        throw new Exception("Salon does not exist");
    }

    @Override
    public List<Salon> getAllSalons() {
        return salonRepository.findAll();
    }

    @Override
    public Salon getSalonBySalonId(Long salonId) throws Exception {

        Salon salon = salonRepository.findById(salonId).orElse(null);

        if(salon == null){
            throw new Exception("Salon not found");
        }
        return salon;
    }

    @Override
    public Salon getSalonByOwnerId(Long ownerId) {
        return salonRepository.findByOwnerId(ownerId);
    }

    @Override
    public List<Salon> searchSalonByCity(String city) {
        return salonRepository.searchSalons(city);
    }
}
