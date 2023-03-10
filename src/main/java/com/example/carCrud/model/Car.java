package com.example.carCrud.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @NotNull(message = "Brand is required")
    @JoinColumn(name = "brand_id")
    private Brand brand;
    private String model;
    private String vin;
    @Column(unique = true)
    private String registrationNumber;
}