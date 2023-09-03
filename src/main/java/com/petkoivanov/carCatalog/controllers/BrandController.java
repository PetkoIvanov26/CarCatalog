package com.petkoivanov.carCatalog.controllers;

import com.petkoivanov.carCatalog.models.dtos.BrandDto;
import com.petkoivanov.carCatalog.models.entities.Brand;
import com.petkoivanov.carCatalog.models.requests.BrandRequest;
import com.petkoivanov.carCatalog.services.BrandService;
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
import static com.petkoivanov.carCatalog.utils.URIConstants.BRANDS_PATH;

@RestController
public class BrandController {

  private static final Logger log = LoggerFactory.getLogger(BrandController.class);

  private final BrandService brandService;

  @Autowired
  public BrandController(BrandService brandService){
    this.brandService = brandService;
  }

  @PostMapping(BRANDS_PATH)
  public ResponseEntity<Void> addBrand(@RequestBody @Valid BrandRequest brandRequest){
    log.info("A request for brand to be added has been submitted");
    Brand brand = brandService.addBrand(brandRequest);

    URI location = UriComponentsBuilder
      .fromUriString(BRANDS_ID_PATH)
      .buildAndExpand(brand.getId())
      .toUri();

    return ResponseEntity.created(location).build();
  }

  @GetMapping(BRANDS_PATH)
  public ResponseEntity<List<BrandDto>> getAllBrands(){
    log.info("All brands were requested from the database");
    List<BrandDto> brands = brandService.getAllBrands();

    return ResponseEntity.ok(brands);
  }

  @GetMapping(value = BRANDS_PATH , params = "brandName")
  public ResponseEntity<BrandDto> getBrandByName(@RequestParam String brandName){
    log.info(String.format("Brand with name %s was requested from database",brandName));
    BrandDto brand = brandService.getBrandDtoByName(brandName);

    return ResponseEntity.ok(brand);
  }

  @PutMapping(BRANDS_ID_PATH)
  private ResponseEntity<BrandDto> updateBrand(
    @RequestBody @Valid BrandRequest brandRequest,
    @PathVariable int id,
    @RequestParam(required = false) boolean returnOld){

    BrandDto brand = brandService.updateBrand(brandRequest,id);
    log.info(String.format("Brand with id %d was updated",id));

    return returnOld ? ResponseEntity.ok(brand) : ResponseEntity.noContent().build();
  }

  @DeleteMapping(BRANDS_ID_PATH)
  private ResponseEntity<BrandDto> deleteBrand(@PathVariable int id,
                                               @RequestParam(required = false) boolean returnOld){

    BrandDto brand = brandService.deleteBrand(id);
    log.info(String.format("Brand with id %d was deleted",id));

    return returnOld ? ResponseEntity.ok(brand) : ResponseEntity.noContent().build();
  }
}
