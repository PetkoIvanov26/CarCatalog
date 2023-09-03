package com.petkoivanov.carCatalog.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarDto {
  private String vinNumber;
  private ModelDto model;
  private double price;
  private Date registrationDate;
  private TransmissionDto transmission;
  private FuelTypeDto fuelType;
}
