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
public class FuelTypeRequest {
  @Pattern(regexp = "^[A-Z][A-Za-z-, .]*$]" ,
           message = "Fuel type must start with capital letter and not contain numbers")
  @NotNull(message = "Can't be null")
  @NotBlank(message = "Can't be blank")
  private String fuelName;
}
