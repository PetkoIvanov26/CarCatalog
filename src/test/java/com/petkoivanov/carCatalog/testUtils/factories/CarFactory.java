package com.petkoivanov.carCatalog.testUtils.factories;

import com.petkoivanov.carCatalog.models.dtos.CarDto;
import com.petkoivanov.carCatalog.models.entities.Car;
import com.petkoivanov.carCatalog.models.requests.CarRequest;

import java.util.Collections;
import java.util.List;

import static com.petkoivanov.carCatalog.testUtils.constants.CarConstants.FUEL_TYPE_ID;
import static com.petkoivanov.carCatalog.testUtils.constants.CarConstants.ID;
import static com.petkoivanov.carCatalog.testUtils.constants.CarConstants.MODEL_ID;
import static com.petkoivanov.carCatalog.testUtils.constants.CarConstants.PRICE;
import static com.petkoivanov.carCatalog.testUtils.constants.CarConstants.REGISTRATION_DATE;
import static com.petkoivanov.carCatalog.testUtils.constants.CarConstants.TRANSMISSION_ID;
import static com.petkoivanov.carCatalog.testUtils.constants.CarConstants.VIN_NUMBER;

public class CarFactory {
  private CarFactory() throws IllegalAccessException{
    throw new IllegalAccessException();
  }

  public static CarRequest getDefaultCarRequest(){
    return new CarRequest(VIN_NUMBER , MODEL_ID , PRICE,REGISTRATION_DATE,TRANSMISSION_ID,FUEL_TYPE_ID);
  }

  public static Car getDefaultCar(){
    return new Car(ID , VIN_NUMBER , ModelFactory.getDefaultModel() , PRICE,REGISTRATION_DATE,TransmissionFactory.getDefaultTransmission(),FuelTypeFactory.getDefaultFuelType());
  }

  public static CarDto getDefaultCarDto(){
    return new CarDto(VIN_NUMBER , ModelFactory.getDefaultModelDto() , PRICE,REGISTRATION_DATE,TransmissionFactory.getDefaultTransmissionDto(),FuelTypeFactory.getDefaultFuelTypeDto());
  }

  public static List<CarDto> getDefaultCarDtoList(){
    return Collections.singletonList(getDefaultCarDto());
  }

  public static List<Car> getDefaultCarList(){
    return Collections.singletonList(getDefaultCar());
  }
}
