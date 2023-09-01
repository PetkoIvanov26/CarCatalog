package com.petkoivanov.carCatalog.models.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarRequest {
  @Pattern(regexp = "^[A-Z0-9]*$" ,
           message = "There must be no lowercase letters")
  @NotNull(message = "vin number can't be empty")
  private String vinNumber;

  @Positive(message = "Model id can't be a negative number")
  @NotNull(message = "Model can't be empty")
  private int modelId;

  @Positive(message = "Price can't be a negative number")
  @NotNull(message = "Price can't be empty")
  private double price;

  @DateTimeFormat(pattern = "dd-MM-yyyy")
  @NotNull(message = "registration date can't be empty")
  private LocalDate registrationDate;

  @Positive(message = "Transmission id cant be a negative number")
  @NotNull(message = "Transmission can't be empty")
  private int transmissionId;

  @Positive(message = "FuelType id can't be a negative number")
  @NotNull(message = "FuelType cant be empty")
  private int fuelTypeId;
}
