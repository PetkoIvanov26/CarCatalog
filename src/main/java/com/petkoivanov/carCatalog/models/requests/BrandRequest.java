package com.petkoivanov.carCatalog.models.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BrandRequest {
  @NotNull(message = "The brand name must cant be empty")
  @NotBlank(message = "The brand name can't be blank")
  private String brandName;
}
