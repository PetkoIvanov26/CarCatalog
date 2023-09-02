package com.petkoivanov.carCatalog.services;

import com.petkoivanov.carCatalog.exceptions.EntityAlreadyExistsException;
import com.petkoivanov.carCatalog.exceptions.EntityNotFoundException;
import com.petkoivanov.carCatalog.mappers.FuelTypeMapper;
import com.petkoivanov.carCatalog.models.dtos.FuelTypeDto;
import com.petkoivanov.carCatalog.models.entities.FuelType;
import com.petkoivanov.carCatalog.models.requests.FuelTypeRequest;
import com.petkoivanov.carCatalog.repositories.FuelTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.petkoivanov.carCatalog.utils.ExceptionMessages.FUEL_TYPE_ALREADY_EXISTS_MESSAGE;
import static com.petkoivanov.carCatalog.utils.ExceptionMessages.FUEL_TYPE_NOT_FOUND_MESSAGE;

@Service
public class FuelTypeService {

  private static final Logger log = LoggerFactory.getLogger(FuelTypeService.class);

  private final FuelTypeMapper fuelTypeMapper;
  private final FuelTypeRepository fuelTypeRepository;

  @Autowired
  private FuelTypeService(FuelTypeMapper fuelTypeMapper , FuelTypeRepository fuelTypeRepository){
    this.fuelTypeMapper = fuelTypeMapper;
    this.fuelTypeRepository = fuelTypeRepository;
  }

  public FuelType addFuelType(FuelTypeRequest fuelTypeRequest){
    log.info("An attempt to save a new fuel type in the database");

    fuelTypeValidation(fuelTypeRequest);

    return fuelTypeRepository.save(new FuelType(fuelTypeRequest.getFuelName()));
  }

  public List<FuelTypeDto> getAllFuelTypes(){
    log.info("An attempt to extract all fuel types from database");

    return fuelTypeMapper.mapFuelTypeToFuelDtoTypeList(fuelTypeRepository.findAll());
  }

  public FuelTypeDto getFuelTypeDtoById(int id){
    log.info(String.format("An attempt to extract fuel type by id %d from database", id));

    return fuelTypeMapper.mapFuelTypeToFuelTypeDto(fuelTypeRepository.findById(id).orElseThrow(() -> {
      log.error(String.format("Exception caught: %s",FUEL_TYPE_NOT_FOUND_MESSAGE));

      throw new EntityNotFoundException(FUEL_TYPE_NOT_FOUND_MESSAGE);
    }));
  }

  public FuelTypeDto getFuelTypeDtoByName(String name){
    log.info(String.format("An attempt to extract fuel type with name %s from database", name));

    return fuelTypeMapper.mapFuelTypeToFuelTypeDto(fuelTypeRepository.findByName(name).orElseThrow(() -> {
      log.error(String.format("Exception caught: %s",FUEL_TYPE_NOT_FOUND_MESSAGE));

      throw new EntityNotFoundException(FUEL_TYPE_NOT_FOUND_MESSAGE);
    }));
  }

  public FuelType getFuelTypeById(int id){
    log.info(String.format("An attempt to extract fuel type by id %d from database", id));

    return fuelTypeRepository.findById(id).orElseThrow(() -> {
      log.error(String.format("Exception caught: %s",FUEL_TYPE_NOT_FOUND_MESSAGE));

      throw new EntityNotFoundException(FUEL_TYPE_NOT_FOUND_MESSAGE);
    });
  }

  public FuelType getFuelTypeByName(String name){
    log.info(String.format("An attempt to extract fuel type by name %s from database", name));

    return fuelTypeRepository.findByName(name).orElseThrow(() -> {
      log.error(String.format("Exception caught: %s",FUEL_TYPE_NOT_FOUND_MESSAGE));

      throw new EntityNotFoundException(FUEL_TYPE_NOT_FOUND_MESSAGE);
    });
  }

  public FuelTypeDto updateFuelType(FuelTypeRequest fuelTypeRequest , int fuelTypeId){
    FuelTypeDto fuelTypeDto = getFuelTypeDtoById(fuelTypeId);

    log.info(String.format("An attempt to update fuel type with id %d", fuelTypeId));

    fuelTypeRepository.save(new FuelType(fuelTypeId,fuelTypeRequest.getFuelName()));
    return fuelTypeDto;
  }

  public FuelTypeDto deleteFuelType(int id){
    FuelTypeDto fuelTypeDto = getFuelTypeDtoById(id);

    fuelTypeRepository.deleteById(id);

    log.info(String.format("Fuel type with id %d was deleted from database", id));
    return fuelTypeDto;
  }
  private void fuelTypeValidation(FuelTypeRequest fuelTypeRequest){
    fuelTypeRepository.findByName(fuelTypeRequest.getFuelName()).ifPresent(fuelType -> {
      log.info(String.format("Exception caught: %s",FUEL_TYPE_ALREADY_EXISTS_MESSAGE));

      throw new EntityAlreadyExistsException(FUEL_TYPE_ALREADY_EXISTS_MESSAGE);
    });
  }
}
