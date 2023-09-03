package com.petkoivanov.carCatalog.controllers;

import com.petkoivanov.carCatalog.models.dtos.CarDto;
import com.petkoivanov.carCatalog.models.entities.Car;
import com.petkoivanov.carCatalog.models.requests.CarRequest;
import com.petkoivanov.carCatalog.services.CarService;
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

import static com.petkoivanov.carCatalog.utils.URIConstants.CARS_ID_PATH;
import static com.petkoivanov.carCatalog.utils.URIConstants.CARS_PATH;

@RestController
public class CarController {

  private static final Logger log = LoggerFactory.getLogger(CarController.class);

  private final CarService carService;

  @Autowired
  public CarController(CarService carService){
    this.carService = carService;
  }

  @GetMapping(CARS_PATH)
  public ResponseEntity<List<CarDto>> getCarsByFilter(
    @RequestParam(required = false) String modelName,
    @RequestParam(required = false) String transmissionName,
    @RequestParam(required = false) String fuelTypeName,
    @RequestParam(required = false) double price ,
    @RequestParam(required = false) boolean isAsc,
    @RequestParam(required = false) boolean isDesc,
    @RequestParam(required = false) boolean isGreaterThan,
    @RequestParam(required = false) boolean isLowerThan){

    log.info("Requesting all cars with the applied filter(s) from database");
    List<CarDto> cars = carService.getAllByFilter(modelName,transmissionName,fuelTypeName,price,isAsc,isDesc,isGreaterThan,isLowerThan);

    return ResponseEntity.ok(cars);
  }

  @PutMapping(CARS_ID_PATH)
  public ResponseEntity<CarDto> updateCar(
    @RequestBody @Valid CarRequest carRequest ,
    @PathVariable int id,
    @RequestParam(required = false) boolean returnOld){

    CarDto car = carService.updateCar(carRequest,id);
    log.info(String.format("Car with id %d was updated",id));

    return returnOld ? ResponseEntity.ok(car) : ResponseEntity.noContent().build();
  }

  @DeleteMapping(CARS_ID_PATH)
  public ResponseEntity<CarDto> deleteCar(
    @PathVariable int id,
    @RequestParam(required = false) boolean returnOld){

    CarDto car = carService.deleteCar(id);
    log.info(String.format("Car with id %d was deleted",id));

    return returnOld ? ResponseEntity.ok(car) : ResponseEntity.noContent().build();
  }
}
