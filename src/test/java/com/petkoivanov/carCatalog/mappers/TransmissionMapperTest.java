package com.petkoivanov.carCatalog.mappers;

import com.petkoivanov.carCatalog.models.dtos.TransmissionDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static com.petkoivanov.carCatalog.testUtils.constants.TransmissionConstants.NAME;
import static com.petkoivanov.carCatalog.testUtils.factories.TransmissionFactory.getDefaultTransmission;
import static com.petkoivanov.carCatalog.testUtils.factories.TransmissionFactory.getDefaultTransmissionList;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TransmissionMapperTest {
  @InjectMocks
  private TransmissionMapper transmissionMapper;

  @Test
  public void mapFuelTypeToFuelTypeDtoList_success(){
    List<TransmissionDto> transmissions = transmissionMapper.mapTransmissionToTransmissionDtoList(getDefaultTransmissionList());

    TransmissionDto fuelTypeDto = transmissions.get(0);

    assertEquals(fuelTypeDto.getTransmissionName() , NAME);
  }

  @Test
  public void mapFuelTypeToFuelTypeDto_success(){
    TransmissionDto transmissionDto = transmissionMapper.mapTransmissionToTransmissionDto(getDefaultTransmission());

    assertEquals(transmissionDto.getTransmissionName() , NAME);
  }
}
