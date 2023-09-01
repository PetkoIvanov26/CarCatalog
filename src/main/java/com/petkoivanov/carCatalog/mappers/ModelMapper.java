package com.petkoivanov.carCatalog.mappers;

import com.petkoivanov.carCatalog.models.dtos.ModelDto;
import com.petkoivanov.carCatalog.models.entities.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ModelMapper {

  private static final Logger log = LoggerFactory.getLogger(ModelMapper.class);

  private final BrandMapper brandMapper;

  @Autowired
  public ModelMapper(BrandMapper brandMapper) {
    this.brandMapper = brandMapper;
  }

  public ModelDto mapModelToModelDto(Model model) {
    log.info(String.format("Model with id %d is being mapped to dto", model.getId()));
    return new ModelDto(model.getName(), brandMapper.mapBrandToBrandDto(model.getBrand()));
  }

  public List<ModelDto> mapModelToModelDtoList(List<Model> models) {
    log.info("Model list is being mapped to dto list");
    return models.stream()
                 .map(this::mapModelToModelDto)
                 .collect(Collectors.toList());
  }
}
