package com.koushik.serviceofferingservice.model;

import jakarta.persistence.*;

@Entity
public class ServiceOffering {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String price;

    private String image;

    @Column(nullable = false)
    private int duration;

    @Column(nullable = false)
    private Long categoryId;

    @Column(nullable = false)
    private Long salonId;

}
