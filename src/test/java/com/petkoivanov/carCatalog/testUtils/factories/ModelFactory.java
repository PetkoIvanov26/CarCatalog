package com.petkoivanov.carCatalog.testUtils.factories;


import com.petkoivanov.carCatalog.models.dtos.ModelDto;
import com.petkoivanov.carCatalog.models.entities.Model;
import com.petkoivanov.carCatalog.models.requests.ModelRequest;

import java.util.Collections;
import java.util.List;

import static com.petkoivanov.carCatalog.testUtils.constants.ModelConstants.ID;
import static com.petkoivanov.carCatalog.testUtils.constants.ModelConstants.NAME;
import static com.petkoivanov.carCatalog.testUtils.constants.ModelConstants.BRAND_ID;

public class ModelFactory {

  private ModelFactory() throws IllegalAccessException{
    throw new IllegalAccessException();
  }

  public static ModelRequest getDefaultModelRequest(){
    return new ModelRequest(NAME , BRAND_ID);
  }

  public static Model getDefaultModel(){
    return new Model(ID , NAME , BrandFactory.getDefaultBrand());
  }

  public static ModelDto getDefaultModelDto(){
    return new ModelDto(NAME , BrandFactory.getDefaultBrandDto());
  }

  public static List<ModelDto> getDefaultModelDtoList(){
    return Collections.singletonList(getDefaultModelDto());
  }

  public static List<Model> getDefaultModelList(){
    return Collections.singletonList(getDefaultModel());
  }

}
