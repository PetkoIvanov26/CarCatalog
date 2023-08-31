package com.petkoivanov.carCatalog.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarDto {
  private String vinNumber;
  private ModelDto model;
  private double price;
  private TransmissionDto transmission;
  private FuelTypeDto fuelType;
}
