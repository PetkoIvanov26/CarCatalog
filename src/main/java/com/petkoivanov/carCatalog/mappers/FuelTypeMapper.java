package com.petkoivanov.carCatalog.mappers;

import com.petkoivanov.carCatalog.models.dtos.FuelTypeDto;
import com.petkoivanov.carCatalog.models.entities.FuelType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FuelTypeMapper {

  private static final Logger log = LoggerFactory.getLogger(FuelTypeMapper.class);

  public FuelTypeDto mapFuelTypeToFuelTypeDto(FuelType fuelType) {
    log.info(String.format("The fuelType %s is being mapped to a dto", fuelType.getName()));
    return new FuelTypeDto(fuelType.getName());
  }

  public List<FuelTypeDto> mapFuelTypeToFuelDtoTypeList(List<FuelType> fuelTypes) {
    log.info("FuelType list is being mapped to dto");
    return fuelTypes.stream()
                    .map(this::mapFuelTypeToFuelTypeDto)
                    .collect(Collectors.toList());
  }
}
