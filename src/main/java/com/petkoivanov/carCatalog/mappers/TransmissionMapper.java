package com.petkoivanov.carCatalog.mappers;

import com.petkoivanov.carCatalog.models.dtos.TransmissionDto;
import com.petkoivanov.carCatalog.models.entities.Transmission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransmissionMapper {

  private static final Logger log = LoggerFactory.getLogger(TransmissionMapper.class);

  public TransmissionDto mapTransmissionToTransmissionDto(Transmission transmission) {
    log.info(String.format("Transmission with name %s is being mapped to dto", transmission.getName()));
    return new TransmissionDto(transmission.getName());
  }

  public List<TransmissionDto> mapTransmissionToTransmissionDtoList(List<Transmission> transmissions) {
    log.info("Transmissions list is being mapped to dto list");
    return transmissions.stream()
                        .map(this::mapTransmissionToTransmissionDto)
                        .collect(Collectors.toList());
  }
}
