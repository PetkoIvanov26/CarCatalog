package com.petkoivanov.carCatalog.services;

import com.petkoivanov.carCatalog.exceptions.EntityAlreadyExistsException;
import com.petkoivanov.carCatalog.exceptions.EntityNotFoundException;
import com.petkoivanov.carCatalog.mappers.ModelMapper;
import com.petkoivanov.carCatalog.models.dtos.ModelDto;
import com.petkoivanov.carCatalog.models.entities.Model;
import com.petkoivanov.carCatalog.models.requests.ModelRequest;
import com.petkoivanov.carCatalog.repositories.ModelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.petkoivanov.carCatalog.utils.ExceptionMessages.MODEL_ALREADY_EXISTS_MESSAGE;
import static com.petkoivanov.carCatalog.utils.ExceptionMessages.MODEL_NOT_FOUND_MESSAGE;

@Service
public class ModelService {

  private final static Logger log = LoggerFactory.getLogger(ModelService.class);

  private final ModelMapper modelMapper;
  private final ModelRepository modelRepository;
  private final BrandService brandService;

  @Autowired
  public ModelService(ModelMapper modelMapper , ModelRepository modelRepository , BrandService brandService){
    this.modelMapper = modelMapper;
    this.modelRepository = modelRepository;
    this.brandService = brandService;
  }

  public Model addModel(ModelRequest modelRequest){
    log.info("An attempt to save new model in database");

    validateModel(modelRequest);

    return modelRepository.save(new Model(modelRequest.getModelName(),
                                          brandService.getBrandById(modelRequest.getBrandId())));
  }

  public List<ModelDto> getAllModels(){
    log.info("An attempt to extract all models from database");

    return modelMapper.mapModelToModelDtoList(modelRepository.findAll());
  }

  public ModelDto getModelDtoById(int id){
    log.info(String.format("An attempt to extract model with id %d from database",id));

    return modelMapper.mapModelToModelDto(modelRepository.findById(id).orElseThrow(() -> {
      log.error(String.format("Exception caught: %s", MODEL_NOT_FOUND_MESSAGE));

      throw new EntityNotFoundException(MODEL_NOT_FOUND_MESSAGE);
    }));
  }

  public ModelDto getModelDtoByName(String name){
    log.info(String.format("An attempt to extract model with name %s from database" , name));

    return modelMapper.mapModelToModelDto(modelRepository.findByName(name).orElseThrow(() -> {
      log.error(String.format("Exception caught: %s",MODEL_NOT_FOUND_MESSAGE));

      throw new EntityNotFoundException(MODEL_NOT_FOUND_MESSAGE);
    }));
  }

  public List<ModelDto> getAllModelsByBrandId(int brandId){
    log.info(String.format("An attempt to extract all models with brand id %d from database" , brandId));

    return modelMapper.mapModelToModelDtoList(modelRepository.findAllByBrandId(brandId));
  }

  public Model getModelById(int id){
    log.info(String.format("An attempt to extract model with id  %d from database",id));

    return modelRepository.findById(id).orElseThrow(() -> {
      log.error(String.format("Exception caught: %s", MODEL_NOT_FOUND_MESSAGE));

      throw new EntityNotFoundException(MODEL_NOT_FOUND_MESSAGE);
    });
  }

  public Model getModelByName(String name){
    log.info(String.format("An attempt to extract model with name %s from database",name));

    return modelRepository.findByName(name).orElseThrow(() -> {
      log.error(String.format("Exception caught: %s", MODEL_NOT_FOUND_MESSAGE));

      throw new EntityNotFoundException(MODEL_NOT_FOUND_MESSAGE);
    });
  }

  public ModelDto updateModel(ModelRequest modelRequest , int id){
    ModelDto modelDto = getModelDtoById(id);

    log.info(String.format("An attempt to update model with id %d",id));

    modelRepository.save(new Model(id, modelDto.getModelName(),brandService.getBrandById(modelRequest.getBrandId())));

    return modelDto;
  }

  public ModelDto deleteModel(int id){
    ModelDto modelDto = getModelDtoById(id);

    modelRepository.deleteById(id);

    log.info(String.format("Model with id %d was deleted",id));
    return modelDto;
  }

  private void validateModel(ModelRequest modelRequest){
    modelRepository.findByName(modelRequest.getModelName()).ifPresent(model ->{
      log.error(String.format("Exception caught: %s",MODEL_ALREADY_EXISTS_MESSAGE));

      throw new EntityAlreadyExistsException(MODEL_ALREADY_EXISTS_MESSAGE);
    });
  }
}
