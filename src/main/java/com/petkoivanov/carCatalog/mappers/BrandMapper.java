package com.petkoivanov.carCatalog.mappers;

import com.petkoivanov.carCatalog.models.dtos.BrandDto;
import com.petkoivanov.carCatalog.models.entities.Brand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BrandMapper {

  private static final Logger log = LoggerFactory.getLogger(BrandMapper.class);

  public BrandDto mapBrandToBrandDto(Brand brand) {
    log.info(String.format("Brand  %s is being mapped to a dto", brand.getName()));
    return new BrandDto(brand.getName());
  }

  public List<BrandDto> mapBrandToBrandDtoList(List<Brand> brands) {
    log.info("Brand list is being mapped to dto");
    return brands.stream()
                 .map(this::mapBrandToBrandDto)
                 .collect(Collectors.toList());
  }
}
