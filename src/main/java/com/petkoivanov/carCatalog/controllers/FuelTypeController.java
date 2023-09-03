package com.petkoivanov.carCatalog.controllers;

import com.petkoivanov.carCatalog.models.dtos.FuelTypeDto;
import com.petkoivanov.carCatalog.models.entities.FuelType;
import com.petkoivanov.carCatalog.models.requests.FuelTypeRequest;
import com.petkoivanov.carCatalog.services.FuelTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static com.petkoivanov.carCatalog.utils.URIConstants.FUEL_TYPES_ID_PATH;
import static com.petkoivanov.carCatalog.utils.URIConstants.FUEL_TYPES_PATH;

@RestController
public class FuelTypeController {

  private static final Logger log = LoggerFactory.getLogger(FuelTypeController.class);

  private final FuelTypeService fuelTypeService;

  @Autowired
  public FuelTypeController(FuelTypeService fuelTypeService){
    this.fuelTypeService = fuelTypeService;
  }

  @PostMapping(FUEL_TYPES_PATH)
  public ResponseEntity<Void> addFuelType(@RequestBody @Valid FuelTypeRequest fuelTypeRequest){
    log.info("A request for a fuel type to be added has been submitted");
    FuelType fuelType = fuelTypeService.addFuelType(fuelTypeRequest);

    URI location = UriComponentsBuilder
      .fromUriString(FUEL_TYPES_ID_PATH)
      .buildAndExpand(fuelType.getId())
      .toUri();

    return ResponseEntity.created(location).build();
  }

  @GetMapping(FUEL_TYPES_PATH)
  public ResponseEntity<List<FuelTypeDto>> getAllFuelTypes(){
    log.info("All fuel types were requested from the database");
    List<FuelTypeDto> fuelTypes = fuelTypeService.getAllFuelTypes();

    return ResponseEntity.ok(fuelTypes);
  }

  @GetMapping(FUEL_TYPES_PATH)
  public ResponseEntity<FuelTypeDto> getFuelTypeByName(@RequestParam String fuelTypeName){
    log.info(String.format("Fuel type with name %s was requested from database", fuelTypeName));
    FuelTypeDto fuelType = fuelTypeService.getFuelTypeDtoByName(fuelTypeName);

    return ResponseEntity.ok(fuelType);
  }

  @PutMapping(FUEL_TYPES_ID_PATH)
  public ResponseEntity<FuelTypeDto> updateFuelType(
    @RequestBody @Valid FuelTypeRequest fuelTypeRequest ,
    @PathVariable int id ,
    @RequestParam(required = false) boolean returnOld){

    FuelTypeDto fuelType = fuelTypeService.updateFuelType(fuelTypeRequest,id);
    log.info(String.format("Fuel type with id %d was updated",id));

    return returnOld ? ResponseEntity.ok(fuelType) : ResponseEntity.noContent().build();
  }

  @DeleteMapping(FUEL_TYPES_ID_PATH)
  public ResponseEntity<FuelTypeDto> deleteFuelType(
    @PathVariable int id ,
    @RequestParam(required = false) boolean returnOld){

      FuelTypeDto fuelType = fuelTypeService.deleteFuelType(id);
      log.info(String.format("Fuel type with id %d was deleted",id));

      return returnOld ? ResponseEntity.ok(fuelType) : ResponseEntity.noContent().build();
  }
}
