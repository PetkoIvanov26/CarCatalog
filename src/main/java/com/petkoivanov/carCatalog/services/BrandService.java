package com.petkoivanov.carCatalog.services;

import com.petkoivanov.carCatalog.exceptions.EntityAlreadyExistsException;
import com.petkoivanov.carCatalog.exceptions.EntityNotFoundException;
import com.petkoivanov.carCatalog.mappers.BrandMapper;
import com.petkoivanov.carCatalog.models.dtos.BrandDto;
import com.petkoivanov.carCatalog.models.entities.Brand;
import com.petkoivanov.carCatalog.models.requests.BrandRequest;
import com.petkoivanov.carCatalog.repositories.BrandRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.petkoivanov.carCatalog.utils.ExceptionMessages.BRAND_ALREADY_EXISTS_MESSAGE;
import static com.petkoivanov.carCatalog.utils.ExceptionMessages.BRAND_NOT_FOUND_MESSAGE;

@Service
public class BrandService {

  private final static Logger log = LoggerFactory.getLogger(BrandService.class);

  private final BrandMapper brandMapper;
  private final BrandRepository brandRepository;

  @Autowired
  public BrandService(BrandMapper brandMapper , BrandRepository brandRepository) {
    this.brandMapper = brandMapper;
    this.brandRepository = brandRepository;
  }

  public Brand addBrand(BrandRequest brandRequest){
    log.info("An attempt to save a brand in the database");

    brandValidation(brandRequest);

    return brandRepository.save(new Brand(brandRequest.getBrandName()));
  }

  public List<BrandDto> getAllBrands(){
    log.info("An attempt to extract all brand from database");

    return brandMapper.mapBrandToBrandDtoList(brandRepository.findAll());
  }

  public BrandDto getBrandDtoById(int id){
    log.info(String.format("An attempt to extract brand with id %d from database",id));

    return brandMapper.mapBrandToBrandDto(brandRepository.findById(id).orElseThrow(() -> {
      log.error(String.format("Exception caught: %s",BRAND_NOT_FOUND_MESSAGE));

      throw new EntityNotFoundException(BRAND_NOT_FOUND_MESSAGE);
    }));
  }

  public BrandDto getBrandDtoByName(String name){
    log.info(String.format("An attempt to extract brand with name %s from database",name));

    return brandMapper.mapBrandToBrandDto(brandRepository.findByName(name).orElseThrow(() -> {
      log.error(String.format("Exception caught: %s",BRAND_NOT_FOUND_MESSAGE));

      throw new EntityNotFoundException(BRAND_NOT_FOUND_MESSAGE);
    }));
  }

  public Brand getBrandById(int id){
    log.info(String.format("An attempt to extract brand with id %d from database",id));

    return brandRepository.findById(id).orElseThrow(() -> {
      log.error(String.format("Exception caught: %s",BRAND_NOT_FOUND_MESSAGE));

      throw new EntityNotFoundException(BRAND_NOT_FOUND_MESSAGE);
    });
  }

  public Brand getBrandByName(String name){
    log.info(String.format("An attempt to extract brand with name %s from database",name));

    return brandRepository.findByName(name).orElseThrow(() -> {
      log.error(String.format("Exception caught: %s",BRAND_NOT_FOUND_MESSAGE));

      throw new EntityNotFoundException(BRAND_NOT_FOUND_MESSAGE);
    });
  }

  public BrandDto updateBrand(BrandRequest brandRequest , int brandId){
    BrandDto brandDto = getBrandDtoById(brandId);

    log.info(String.format("An attempt to update brand with id %d",brandId));

    brandRepository.save(new Brand(brandId , brandRequest.getBrandName()));

    return brandDto;
  }

  public BrandDto deleteBrand(int brandId){
    BrandDto brandDto = getBrandDtoById(brandId);
    brandRepository.deleteById(brandId);

    log.info(String.format("Brand with id %d was deleted from the database", brandId));

    return brandDto;
  }

  private void brandValidation(BrandRequest brandRequest){
    brandRepository.findByName(brandRequest.getBrandName()).ifPresent(brand -> {
      log.error(String.format("Exception caught : %s" , BRAND_ALREADY_EXISTS_MESSAGE));

      throw new EntityAlreadyExistsException(BRAND_ALREADY_EXISTS_MESSAGE);
    });
  }
}
