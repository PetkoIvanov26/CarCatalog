package com.petkoivanov.carCatalog.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ModelDto {
  private String modelName;
  private BrandDto brand;
}
