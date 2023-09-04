package com.petkoivanov.carCatalog.mappers;

import com.petkoivanov.carCatalog.models.dtos.CarDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static com.petkoivanov.carCatalog.testUtils.constants.CarConstants.PRICE;
import static com.petkoivanov.carCatalog.testUtils.constants.CarConstants.REGISTRATION_DATE;
import static com.petkoivanov.carCatalog.testUtils.constants.CarConstants.VIN_NUMBER;
import static com.petkoivanov.carCatalog.testUtils.factories.CarFactory.getDefaultCar;
import static com.petkoivanov.carCatalog.testUtils.factories.CarFactory.getDefaultCarList;
import static com.petkoivanov.carCatalog.testUtils.factories.FuelTypeFactory.getDefaultFuelTypeDto;
import static com.petkoivanov.carCatalog.testUtils.factories.ModelFactory.getDefaultModelDto;
import static com.petkoivanov.carCatalog.testUtils.factories.TransmissionFactory.getDefaultTransmissionDto;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CarMapperTest {

  @Mock
  private ModelMapper modelMapper;
  @Mock
  private TransmissionMapper transmissionMapper;
  @Mock
  private FuelTypeMapper fuelTypeMapper;

  @InjectMocks
  private CarMapper carMapper;

  @Test
  public void testMapCarToCarDtoList_success(){
    when(modelMapper.mapModelToModelDto(any())).thenReturn(getDefaultModelDto());
    when(transmissionMapper.mapTransmissionToTransmissionDto(any())).thenReturn(getDefaultTransmissionDto());
    when(fuelTypeMapper.mapFuelTypeToFuelTypeDto(any())).thenReturn(getDefaultFuelTypeDto());

    List<CarDto> cars = carMapper.mapCarToCarDtoList(getDefaultCarList());

    CarDto carDto = cars.get(0);
    assertEquals(carDto.getVinNumber() , VIN_NUMBER);
    assertEquals(carDto.getModel() , getDefaultModelDto());
    assertEquals(carDto.getRegistrationDate() , REGISTRATION_DATE);
    assertEquals(carDto.getTransmission() , getDefaultTransmissionDto());
    assertEquals(carDto.getFuelType() , getDefaultFuelTypeDto());
  }

  @Test
  public void testMapCarToCarDto_success(){
    when(modelMapper.mapModelToModelDto(any())).thenReturn(getDefaultModelDto());
    when(transmissionMapper.mapTransmissionToTransmissionDto(any())).thenReturn(getDefaultTransmissionDto());
    when(fuelTypeMapper.mapFuelTypeToFuelTypeDto(any())).thenReturn(getDefaultFuelTypeDto());

    CarDto carDto = carMapper.mapCarToCarDto(getDefaultCar());

    assertEquals(carDto.getVinNumber() , VIN_NUMBER);
    assertEquals(carDto.getModel() , getDefaultModelDto());
    assertEquals(carDto.getRegistrationDate() , REGISTRATION_DATE);
    assertEquals(carDto.getTransmission() , getDefaultTransmissionDto());
    assertEquals(carDto.getFuelType() , getDefaultFuelTypeDto());
  }
}
