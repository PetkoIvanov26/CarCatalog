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
public class TransmissionRequest {
  @Pattern(regexp = "^[A-Z0-9][A-Z0-9a-z]*$")
  @NotNull
  @NotBlank
  private String transmissionName;
}
