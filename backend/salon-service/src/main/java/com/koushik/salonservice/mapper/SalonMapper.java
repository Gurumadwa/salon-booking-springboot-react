package com.koushik.salonservice.mapper;

import com.koushik.salonservice.model.Salon;
import com.koushik.salonservice.payload.dto.SalonDto;

public class SalonMapper {

    public static SalonDto mapToDto(Salon salon){
        SalonDto salonDto = new SalonDto();

        salonDto.setId(salon.getId());
        salonDto.setName(salon.getName());
        salonDto.setEmail(salon.getEmail());
        salonDto.setCity(salon.getCity());
        salonDto.setOwnerId(salon.getOwnerId());
        salonDto.setAddress(salon.getAddress());
        salonDto.setImages(salon.getImages());
        salonDto.setPhoneNumber(salon.getPhoneNumber());
        salonDto.setOpenTime(salon.getOpenTime());
        salonDto.setCloseTime(salon.getCloseTime());

        return salonDto;
    }

}
