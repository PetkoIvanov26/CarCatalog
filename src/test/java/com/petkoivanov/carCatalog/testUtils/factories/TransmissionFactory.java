package com.petkoivanov.carCatalog.testUtils.factories;

import com.petkoivanov.carCatalog.models.dtos.TransmissionDto;
import com.petkoivanov.carCatalog.models.entities.Transmission;
import com.petkoivanov.carCatalog.models.requests.TransmissionRequest;

import java.util.Collections;
import java.util.List;

import static com.petkoivanov.carCatalog.testUtils.constants.TransmissionConstants.ID;
import static com.petkoivanov.carCatalog.testUtils.constants.TransmissionConstants.NAME;

public class TransmissionFactory {

  private TransmissionFactory() throws IllegalAccessException{
    throw new IllegalAccessException();
  }

  public static TransmissionRequest getDefaultTransmissionRequest(){
    return new TransmissionRequest(NAME);
  }

  public static Transmission getDefaultTransmission(){
    return new Transmission(ID , NAME);
  }

  public static TransmissionDto getDefaultTransmissionDto(){
    return new TransmissionDto(NAME);
  }

  public static List<TransmissionDto> getDefaultTransmissionDtoList(){
    return Collections.singletonList(getDefaultTransmissionDto());
  }

  public static List<Transmission> getDefaultTransmissionList(){
    return Collections.singletonList(getDefaultTransmission());
  }

}
