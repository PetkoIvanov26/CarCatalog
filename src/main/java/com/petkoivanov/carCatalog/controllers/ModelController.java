package com.petkoivanov.carCatalog.controllers;

import com.petkoivanov.carCatalog.models.dtos.ModelDto;
import com.petkoivanov.carCatalog.models.entities.Model;
import com.petkoivanov.carCatalog.models.requests.ModelRequest;
import com.petkoivanov.carCatalog.services.ModelService;
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

import static com.petkoivanov.carCatalog.utils.URIConstants.BRANDS_ID_PATH;
import static com.petkoivanov.carCatalog.utils.URIConstants.MODELS_ID_PATH;
import static com.petkoivanov.carCatalog.utils.URIConstants.MODELS_PATH;

@RestController
public class ModelController {

  private static final Logger log = LoggerFactory.getLogger(ModelController.class);

  private final ModelService modelService;

  @Autowired
  public ModelController(ModelService modelService){
    this.modelService = modelService;
  }

  @PostMapping(MODELS_PATH)
  public ResponseEntity<Void> addModel(@RequestBody @Valid ModelRequest modelRequest){
    log.info("A request for a model to be added was submitted");
    Model model = modelService.addModel(modelRequest);

    URI location = UriComponentsBuilder
      .fromUriString(MODELS_ID_PATH)
      .buildAndExpand(model.getId())
      .toUri();

    return ResponseEntity.created(location).build();
  }

  @GetMapping(MODELS_PATH)
  public ResponseEntity<List<ModelDto>> getAllModels(){
    log.info("All models were requested from the database");
    List<ModelDto> models = modelService.getAllModels();

    return ResponseEntity.ok(models);
  }

  @GetMapping(MODELS_PATH+BRANDS_ID_PATH)
  public ResponseEntity<List<ModelDto>> getAllModelsWithBrandId(@PathVariable int id){
    log.info(String.format("All models with brand id %d requested from the database",id));
    List<ModelDto> models =modelService.getAllModelsByBrandId(id);

    return ResponseEntity.ok(models);
  }

  @GetMapping(value = MODELS_PATH , params = "modelName")
  public ResponseEntity<ModelDto> getModelByName(@RequestParam String modelName){
    log.info(String.format("Model with name %s requested from database",modelName));
    ModelDto model = modelService.getModelDtoByName(modelName);

    return ResponseEntity.ok(model);
  }

  @PutMapping(MODELS_ID_PATH)
  public ResponseEntity<ModelDto> updateModel(
    @RequestBody @Valid ModelRequest modelRequest ,
    @PathVariable int id ,
    @RequestParam(required = false) boolean returnOld){

    ModelDto model = modelService.updateModel(modelRequest , id);
    log.info(String.format("Model with id %d was updated" , id));

    return returnOld ? ResponseEntity.ok(model) : ResponseEntity.noContent().build();
  }

  @DeleteMapping(MODELS_ID_PATH)
  public ResponseEntity<ModelDto> deleteModel(
    @PathVariable int id ,
    @RequestParam(required = false) boolean returnOld){

    ModelDto model = modelService.deleteModel(id);
    log.info(String.format("Model with id %d was deleted" , id));

    return returnOld ? ResponseEntity.ok(model) : ResponseEntity.noContent().build();
  }
}
