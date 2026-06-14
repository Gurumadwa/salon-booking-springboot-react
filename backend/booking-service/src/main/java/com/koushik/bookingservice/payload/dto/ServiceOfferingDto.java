package com.koushik.bookingservice.payload.dto;

import lombok.Data;

@Data
public class ServiceOfferingDto {


    private Long id;

    private String name;

    private String description;

    private int price;

    private String image;

    private int duration;

    private Long categoryId;

    private Long salonId;

}
