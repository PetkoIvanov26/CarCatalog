package com.petkoivanov.carCatalog.testUtils.factories;


import com.petkoivanov.carCatalog.models.dtos.FuelTypeDto;
import com.petkoivanov.carCatalog.models.entities.FuelType;
import com.petkoivanov.carCatalog.models.requests.FuelTypeRequest;

import java.util.Collections;
import java.util.List;

import static com.petkoivanov.carCatalog.testUtils.constants.FuelTypeConstants.ID;
import static com.petkoivanov.carCatalog.testUtils.constants.FuelTypeConstants.NAME;

public class FuelTypeFactory {
  private FuelTypeFactory() throws IllegalAccessException{
    throw new IllegalAccessException();
  }

  public static FuelTypeRequest getDefaultFuelTypeRequest(){
    return new FuelTypeRequest(NAME);
  }

  public static FuelType getDefaultFuelType(){
    return new FuelType(ID , NAME);
  }

  public static FuelTypeDto getDefaultFuelTypeDto(){
    return new FuelTypeDto(NAME);
  }

  public static List<FuelTypeDto> getDefaultFuelTypeDtoList(){
    return Collections.singletonList(getDefaultFuelTypeDto());
  }

  public static List<FuelType> getDefaultFuelTypeList(){
    return Collections.singletonList(getDefaultFuelType());
  }
}
