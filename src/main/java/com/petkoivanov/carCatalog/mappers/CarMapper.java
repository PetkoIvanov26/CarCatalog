package com.petkoivanov.carCatalog.mappers;

import com.petkoivanov.carCatalog.models.dtos.CarDto;
import com.petkoivanov.carCatalog.models.entities.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarMapper {

  private static final Logger log = LoggerFactory.getLogger(BrandMapper.class);

  private final ModelMapper modelMapper;
  private final TransmissionMapper transmissionMapper;
  private final FuelTypeMapper fuelTypeMapper;

  @Autowired
  public CarMapper(ModelMapper modelMapper, TransmissionMapper transmissionMapper, FuelTypeMapper fuelTypeMapper) {
    this.modelMapper = modelMapper;
    this.transmissionMapper = transmissionMapper;
    this.fuelTypeMapper = fuelTypeMapper;
  }

  public CarDto mapCarToCarDto(Car car) {
    log.info(String.format("Car with id %d is being mapped to a dto", car.getId()));
    return new CarDto(car.getVinNumber()
      , modelMapper.mapModelToModelDto(car.getModel())
      , car.getPrice(), car.getRegistrationDate()
      , transmissionMapper.mapTransmissionToTransmissionDto(car.getTransmission())
      , fuelTypeMapper.mapFuelTypeToFuelTypeDto(car.getFuelType()));
  }

  public List<CarDto> mapCarToCarDtoList(List<Car> cars) {
    log.info("Car list is being mapped to dto");
    return cars.stream()
               .map(this::mapCarToCarDto)
               .collect(Collectors.toList());
  }
}
