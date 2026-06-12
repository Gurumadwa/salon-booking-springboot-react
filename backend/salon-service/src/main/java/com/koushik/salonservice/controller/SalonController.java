package com.koushik.salonservice.controller;

import com.koushik.salonservice.mapper.SalonMapper;
import com.koushik.salonservice.model.Salon;
import com.koushik.salonservice.payload.dto.SalonDto;
import com.koushik.salonservice.payload.dto.UserDto;
import com.koushik.salonservice.service.SalonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salons")
@RequiredArgsConstructor
public class SalonController {

    private final SalonService salonService;

    @PostMapping
    public ResponseEntity<SalonDto> createSalon(@RequestBody SalonDto salonDto){
        UserDto userDto = new UserDto();
        userDto.setId(1L);  //userDto we will get from keyclock from token

        Salon salon = salonService.createSalon(salonDto, userDto);

        // convert above salon entiy to dto to return.
        SalonDto salonDto1 = SalonMapper.mapToDto(salon);

        return ResponseEntity.ok(salonDto1);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SalonDto> updateSalon(@RequestBody SalonDto salonDto, @PathVariable("id") Long salonId) throws Exception {
        UserDto userDto = new UserDto();
        userDto.setId(1L);  //userDto we will get from keyclock from token

        Salon salon = salonService.updateSalon(salonDto, userDto,salonId);

        // convert above salon entiy to dto to return.
        SalonDto salonDto1 = SalonMapper.mapToDto(salon);

        return ResponseEntity.ok(salonDto1);
    }

    @GetMapping
    public ResponseEntity<List<SalonDto>> getSalons() {
        List<Salon> salons = salonService.getAllSalons();

        List<SalonDto> salonDtos = salons.stream().map((salon) ->
                {
                    SalonDto salonDto = SalonMapper.mapToDto(salon);
                    return salonDto;
                }
                ).toList();

        return ResponseEntity.ok(salonDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalonDto> getSalonById(@PathVariable("id") Long salonId) throws Exception {
        Salon salon = salonService.getSalonBySalonId(salonId);

        SalonDto salonDto = SalonMapper.mapToDto(salon);

        return ResponseEntity.ok(salonDto);
    }

    @GetMapping("/search")
    public ResponseEntity<List<SalonDto>> searchSalons(@RequestParam("city") String city){

        List<Salon> salonByCity = salonService.searchSalonByCity(city);

        List<SalonDto> salonDtoStream = salonByCity.stream().map((salon) -> {
            SalonDto salonDto = SalonMapper.mapToDto(salon);
            return salonDto;
        }).toList();

        return ResponseEntity.ok(salonDtoStream);
    }

    @GetMapping("/owner")
    public ResponseEntity<SalonDto> getSalonByOwnerId(@PathVariable("id") Long salonId) throws Exception {
        UserDto userDto = new UserDto();
        userDto.setId(1L);  //userDto (owner id) we will get from keyclock from token

        Salon salon = salonService.getSalonByOwnerId(userDto.getId());

        SalonDto salonDto = SalonMapper.mapToDto(salon);

        return ResponseEntity.ok(salonDto);
    }

}
