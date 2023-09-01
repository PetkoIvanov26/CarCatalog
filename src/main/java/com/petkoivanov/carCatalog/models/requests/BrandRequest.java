package com.petkoivanov.carCatalog.models.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BrandRequest {
  @Pattern(regexp = "^[A-Z][A-Za-z-, .]*$]",
           message = "The brand name must with capital letter and " +
                     "should not contain number or special signs")
  @NotNull(message = "The brand name must cant be empty")
  @NotBlank(message = "The brand name can't be blank")
  private String brandName;
}
