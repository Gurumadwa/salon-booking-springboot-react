package com.koushik.serviceofferingservice.payload.dto;

import lombok.Data;

@Data
public class CategoryDto {

    private Long id;

    private String name;

    private String image;

    private Long salonId; //remove if needed
}
