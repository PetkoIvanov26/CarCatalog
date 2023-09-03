package com.petkoivanov.carCatalog.services;

import com.petkoivanov.carCatalog.exceptions.EntityAlreadyExistsException;
import com.petkoivanov.carCatalog.exceptions.EntityNotFoundException;
import com.petkoivanov.carCatalog.mappers.CarMapper;
import com.petkoivanov.carCatalog.models.dtos.CarDto;
import com.petkoivanov.carCatalog.models.entities.Car;
import com.petkoivanov.carCatalog.models.entities.FuelType;
import com.petkoivanov.carCatalog.models.entities.Model;
import com.petkoivanov.carCatalog.models.entities.Transmission;
import com.petkoivanov.carCatalog.models.requests.CarRequest;
import com.petkoivanov.carCatalog.repositories.CarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static com.petkoivanov.carCatalog.utils.ExceptionMessages.CAR_NOT_FOUND_MESSAGE;
import static com.petkoivanov.carCatalog.utils.ExceptionMessages.CAR_VIN_ALREADY_EXISTS_MESSAGE;

@Service
public class CarService {

  public final static Logger log = LoggerFactory.getLogger(CarService.class);

  private final CarMapper carMapper;
  private final CarRepository carRepository;
  private final ModelService modelService;
  private final FuelTypeService fuelTypeService;
  private final TransmissionService transmissionService;

  @Autowired
  public CarService(CarMapper carMapper , CarRepository carRepository ,
                    ModelService modelService , FuelTypeService fuelTypeService ,
                    TransmissionService transmissionService){
    this.carMapper = carMapper;
    this.carRepository = carRepository;
    this.modelService = modelService;
    this.fuelTypeService = fuelTypeService;
    this.transmissionService = transmissionService;
  }

  public Car addCar(CarRequest carRequest){
    log.info("An attempt to save new car to the database");
    Date regDate = carRequest.getRegistrationDate();
    carValidation(carRequest);

    return carRepository.save(new Car(carRequest.getVinNumber(),
                                      modelService.getModelById(carRequest.getModelId()),
                                      carRequest.getPrice(),carRequest.getRegistrationDate(),
                                      transmissionService.getTransmissionById(carRequest.getTransmissionId()),
                                      fuelTypeService.getFuelTypeById(carRequest.getFuelTypeId())));
  }

  public CarDto getCarByVinNumber(String vinNumber){
    log.info(String.format("An attempt to extract car with vin number %s from database",vinNumber));

    return carMapper.mapCarToCarDto(carRepository.findByVinNumber(vinNumber).orElseThrow(() ->{
      log.error(String.format("Exception caught: %s",CAR_NOT_FOUND_MESSAGE));

      throw new EntityNotFoundException(CAR_NOT_FOUND_MESSAGE);
    }));
  }

  public CarDto getCarByRegistrationDate(LocalDate regDate){
    log.info(String.format("An attempt to extract car with registration date %s from database",regDate.toString()));

    return carMapper.mapCarToCarDto(carRepository.findByRegistrationDate(regDate).orElseThrow(() ->{
      log.error(String.format("Exception caught: %s",CAR_NOT_FOUND_MESSAGE));

      throw new EntityNotFoundException(CAR_NOT_FOUND_MESSAGE);
    }));
  }

  public CarDto getCarDtoById(int id){
    log.info(String.format("An attempt to extract a car with id %d from database" , id));

    return carMapper.mapCarToCarDto(carRepository.findById(id).orElseThrow(() -> {
      log.error(String.format("Exception caught: %s",CAR_NOT_FOUND_MESSAGE));

      throw new EntityNotFoundException(CAR_NOT_FOUND_MESSAGE);
    }));
  }

  public List<CarDto> getAllByFilter(String modelName , String transmissionName , String fuelTypeName , double price ,
                                     boolean isAsc , boolean isDesc  , boolean isGreaterThan , boolean isLowerThan){

      if (modelName != null && transmissionName == null && fuelTypeName == null &&
          price == 0.0 && !isAsc && !isDesc && !isGreaterThan && !isLowerThan){

        return getAllByModelId(modelName);
      }

      if(modelName == null && transmissionName != null && fuelTypeName == null && price == 0.0 && !isAsc &&
         !isDesc && !isGreaterThan && !isLowerThan){

        return getAllByTransmissionId(transmissionName);
      }

      if(modelName == null && transmissionName == null && fuelTypeName != null && price == 0.0 && !isAsc &&
         !isDesc && !isGreaterThan && !isLowerThan){

        return getAllByFuelTypeId(fuelTypeName);
      }
      if(modelName == null && transmissionName == null && fuelTypeName == null &&
         price == 0.0 && isAsc && !isDesc && !isGreaterThan && !isLowerThan){

        return carMapper.mapCarToCarDtoList(carRepository.findAllByOrderByPriceAsc());
      }

      if(modelName == null && transmissionName == null && fuelTypeName == null &&
         price == 0.0 && !isAsc && !isDesc && !isGreaterThan && !isLowerThan){

        return carMapper.mapCarToCarDtoList(carRepository.findAllByOrderByPriceDesc());
      }
      if(modelName == null && transmissionName == null && fuelTypeName == null &&
         price > 0.0 && !isAsc && !isDesc && isGreaterThan && !isLowerThan){

        return carMapper.mapCarToCarDtoList(carRepository.findAlLByPriceGreaterThan(price));
      }
      if(modelName == null && transmissionName == null && fuelTypeName == null &&
         price > 0.0 && !isAsc && !isDesc && !isGreaterThan && isLowerThan){

        return carMapper.mapCarToCarDtoList(carRepository.findAllByPriceLessThan(price));
      }
      if(modelName != null && transmissionName != null && fuelTypeName == null && price == 0.0 &&
         !isAsc && !isDesc && !isGreaterThan && !isLowerThan){
        return getAllByTransmissionIdAndModelId(transmissionName , modelName);
      }
      if(modelName == null && transmissionName != null && fuelTypeName != null && price == 0.0 &&
         !isAsc && !isDesc && !isGreaterThan && !isLowerThan){

        return getAllByTransmissionIdAndFuelTypeId(transmissionName , fuelTypeName);
      }
      if(modelName != null && transmissionName == null && fuelTypeName != null && price == 0.0 &&
         !isAsc && !isDesc && !isGreaterThan && !isLowerThan){

        return getAllByModelIdAndFuelTypeId(modelName , fuelTypeName);
      }
      if(modelName != null && transmissionName != null && fuelTypeName != null && price == 0.0 &&
         !isAsc && !isDesc && !isGreaterThan && !isLowerThan){

        return getAllByModelIdAndTransmissionIdAndFuelTypeId(modelName , transmissionName , fuelTypeName);
      }
      if(modelName != null && transmissionName != null && fuelTypeName != null && price != 0.0 &&
         !isAsc && !isDesc && isGreaterThan && !isLowerThan){

        return getByModelIdAndFuelTypeIdAndTransmissionIdAndPriceGreaterThan(modelName , transmissionName , fuelTypeName , price);
      }
      if(modelName != null && transmissionName != null && fuelTypeName != null && price != 0.0 &&
         !isAsc && !isDesc && !isGreaterThan && isLowerThan){

        Model model = modelService.getModelByName(modelName);
        FuelType fuelType = fuelTypeService.getFuelTypeByName(fuelTypeName);
        Transmission transmission = transmissionService.getTransmissionByName(transmissionName);

        return carMapper.mapCarToCarDtoList(carRepository.findByModelIdAndFuelTypeIdAndTransmissionIdAndPriceLessThan(model.getId(),price,fuelType.getId(),transmission.getId()));
      }
      if(modelName != null && transmissionName != null && fuelTypeName != null && price > 0.0 &&
         isAsc && !isDesc && !isGreaterThan && !isLowerThan){

        Model model = modelService.getModelByName(modelName);
        FuelType fuelType = fuelTypeService.getFuelTypeByName(fuelTypeName);
        Transmission transmission = transmissionService.getTransmissionByName(transmissionName);

        return carMapper.mapCarToCarDtoList(carRepository.findByModelIdAndFuelTypeIdAndTransmissionIdOrderByPriceAsc(model.getId(),fuelType.getId(),transmission.getId()));
      }
      if(modelName != null && transmissionName != null && fuelTypeName != null && price > 0.0 &&
         !isAsc && isDesc && !isGreaterThan && !isLowerThan){

        Model model = modelService.getModelByName(modelName);
        FuelType fuelType = fuelTypeService.getFuelTypeByName(fuelTypeName);
        Transmission transmission = transmissionService.getTransmissionByName(transmissionName);

        return carMapper.mapCarToCarDtoList(carRepository.findByModelIdAndFuelTypeIdAndTransmissionIdOrderByPriceDesc(model.getId(),fuelType.getId(),transmission.getId()));
      }
      return carMapper.mapCarToCarDtoList(carRepository.findAll());
  }

  public CarDto updateCar(CarRequest carRequest , int id){
    CarDto carDto = getCarDtoById(id);

    log.info(String.format("An attempt to update car with id %d",id));

    carRepository.save(new Car(id,carRequest.getVinNumber(),
                               modelService.getModelById(carRequest.getModelId()),
                               carRequest.getPrice(),carRequest.getRegistrationDate(),
                               transmissionService.getTransmissionById(carRequest.getTransmissionId()),
                               fuelTypeService.getFuelTypeById(carRequest.getFuelTypeId())));

    return carDto;
  }

  public CarDto deleteCar(int id){
    CarDto carDto = getCarDtoById(id);

    log.info(String.format("An attempt to delete car with id %d",id));

    carRepository.deleteById(id);
    return carDto;
  }

  private List<CarDto> getAllByModelId(String modelName){
    Model model = modelService.getModelByName(modelName);

    return carMapper.mapCarToCarDtoList(carRepository.findAllByModelId(model.getId()));
  }

  private List<CarDto> getAllByTransmissionId(String transmissionName){
    Transmission transmission = transmissionService.getTransmissionByName(transmissionName);

    return carMapper.mapCarToCarDtoList(carRepository.findAllByTransmissionId(transmission.getId()));
  }

  private List<CarDto> getAllByFuelTypeId(String fuelTypeName){
    FuelType fuelType = fuelTypeService.getFuelTypeByName(fuelTypeName);

    return carMapper.mapCarToCarDtoList(carRepository.findAllByFuelTypeId(fuelType.getId()));
  }

  private List<CarDto> getAllByTransmissionIdAndModelId(String transmissionName , String modelName){
    Model model = modelService.getModelByName(modelName);
    Transmission transmission = transmissionService.getTransmissionByName(transmissionName);

    return carMapper.mapCarToCarDtoList(carRepository.findAllByModelIdAndTransmissionId(model.getId(), transmission.getId()));
  }

  private List<CarDto> getAllByTransmissionIdAndFuelTypeId(String transmissionName , String fuelTypeName){
    FuelType fuelType = fuelTypeService.getFuelTypeByName(fuelTypeName);
    Transmission transmission = transmissionService.getTransmissionByName(transmissionName);

    return carMapper.mapCarToCarDtoList(carRepository.findAllByTransmissionIdAndFuelTypeId(fuelType.getId(),transmission.getId()));
  }

  private List<CarDto> getAllByModelIdAndFuelTypeId(String modelName , String fuelTypeName){
    Model model = modelService.getModelByName(modelName);
    FuelType fuelType = fuelTypeService.getFuelTypeByName(fuelTypeName);

    return carMapper.mapCarToCarDtoList(carRepository.findAllByModelIdAndFuelTypeId(model.getId(),fuelType.getId()));
  }

  private List<CarDto> getAllByModelIdAndTransmissionIdAndFuelTypeId(String modelName , String transmissionName , String fuelTypeName){
    Model model = modelService.getModelByName(modelName);
    FuelType fuelType = fuelTypeService.getFuelTypeByName(fuelTypeName);
    Transmission transmission = transmissionService.getTransmissionByName(transmissionName);

    return carMapper.mapCarToCarDtoList(carRepository.findAllByModelIdAndFuelTypeIdAndTransmissionId(model.getId(),fuelType.getId(),transmission.getId()));
  }

  private List<CarDto> getByModelIdAndFuelTypeIdAndTransmissionIdAndPriceGreaterThan(String modelName , String transmissionName , String fuelTypeName , double price){
    Model model = modelService.getModelByName(modelName);
    FuelType fuelType = fuelTypeService.getFuelTypeByName(fuelTypeName);
    Transmission transmission = transmissionService.getTransmissionByName(transmissionName);

    return carMapper.mapCarToCarDtoList(carRepository.findByModelIdAndFuelTypeIdAndTransmissionIdAndPriceGreaterThan(model.getId(),price,fuelType.getId(),transmission.getId()));
  }
  private void carValidation(CarRequest carRequest){
    carRepository.findByVinNumber(carRequest.getVinNumber()).ifPresent(car -> {
      log.error(String.format("Exception caught: %s",CAR_VIN_ALREADY_EXISTS_MESSAGE));

      throw new EntityAlreadyExistsException(CAR_VIN_ALREADY_EXISTS_MESSAGE);
    });
  }

}
