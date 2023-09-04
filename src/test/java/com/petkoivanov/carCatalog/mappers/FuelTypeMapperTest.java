package com.petkoivanov.carCatalog.mappers;

import com.petkoivanov.carCatalog.models.dtos.FuelTypeDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static com.petkoivanov.carCatalog.testUtils.factories.FuelTypeFactory.getDefaultFuelType;
import static com.petkoivanov.carCatalog.testUtils.factories.FuelTypeFactory.getDefaultFuelTypeList;
import static com.petkoivanov.carCatalog.testUtils.constants.FuelTypeConstants.NAME;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class FuelTypeMapperTest {

  @InjectMocks
  private FuelTypeMapper fuelTypeMapper;

  @Test
  public void mapFuelTypeToFuelTypeDtoList_success(){
    List<FuelTypeDto> fuelTypes = fuelTypeMapper.mapFuelTypeToFuelTypeDtoList(getDefaultFuelTypeList());

    FuelTypeDto fuelTypeDto = fuelTypes.get(0);

    assertEquals(fuelTypeDto.getFuelType() , NAME);
  }

  @Test
  public void mapFuelTypeToFuelTypeDto_success(){
    FuelTypeDto fuelTypeDto = fuelTypeMapper.mapFuelTypeToFuelTypeDto(getDefaultFuelType());

    assertEquals(fuelTypeDto.getFuelType() , NAME);
  }

}
