package com.petkoivanov.carCatalog.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarDto {
  private String vinNumber;
  private ModelDto model;
  private double price;
  private LocalDate registrationDate;
  private TransmissionDto transmission;
  private FuelTypeDto fuelType;
}
