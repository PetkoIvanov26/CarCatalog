package com.petkoivanov.carCatalog.testUtils.factories;

import com.petkoivanov.carCatalog.models.dtos.BrandDto;
import com.petkoivanov.carCatalog.models.entities.Brand;
import com.petkoivanov.carCatalog.models.requests.BrandRequest;

import java.util.Collections;
import java.util.List;

import static com.petkoivanov.carCatalog.testUtils.constants.BrandConstants.ID;
import static com.petkoivanov.carCatalog.testUtils.constants.BrandConstants.NAME;

public class BrandFactory {

  private BrandFactory() throws IllegalAccessException{
    throw new IllegalAccessException();
  }

  public static BrandRequest getDefaultBrandRequest(){
    return new BrandRequest(NAME);
  }

  public static Brand getDefaultBrand(){
    return new Brand(ID , NAME);
  }

  public static BrandDto getDefaultBrandDto(){
    return new BrandDto(NAME);
  }

  public static List<BrandDto> getDefaultBrandDtoList(){
    return Collections.singletonList(getDefaultBrandDto());
  }

  public static List<Brand> getDefaultBrandList(){
    return Collections.singletonList(getDefaultBrand());
  }

}
