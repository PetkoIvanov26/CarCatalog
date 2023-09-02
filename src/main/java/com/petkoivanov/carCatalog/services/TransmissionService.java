package com.petkoivanov.carCatalog.services;

import com.petkoivanov.carCatalog.exceptions.EntityAlreadyExistsException;
import com.petkoivanov.carCatalog.exceptions.EntityNotFoundException;
import com.petkoivanov.carCatalog.mappers.TransmissionMapper;
import com.petkoivanov.carCatalog.models.dtos.TransmissionDto;
import com.petkoivanov.carCatalog.models.entities.Transmission;
import com.petkoivanov.carCatalog.models.requests.TransmissionRequest;
import com.petkoivanov.carCatalog.repositories.TransmissionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.petkoivanov.carCatalog.utils.ExceptionMessages.TRANSMISSION_ALREADY_EXISTS_MESSAGE;
import static com.petkoivanov.carCatalog.utils.ExceptionMessages.TRANSMISSION_NOT_FOUND_MESSAGE;

@Service
public class TransmissionService {

  private final static Logger log = LoggerFactory.getLogger(TransmissionService.class);

  private final TransmissionMapper transmissionMapper;
  private final TransmissionRepository transmissionRepository;

  @Autowired
  public TransmissionService(TransmissionMapper transmissionMapper, TransmissionRepository transmissionRepository){
    this.transmissionMapper = transmissionMapper;
    this.transmissionRepository = transmissionRepository;
  }

  public Transmission addTransmission(TransmissionRequest transmissionRequest){
    log.info("An attempt to save new transmission to database");

    transmissionValidation(transmissionRequest);

    return transmissionRepository.save(new Transmission(transmissionRequest.getTransmissionName()));
  }

  public List<TransmissionDto> getAllTransmissions(){
    log.info("An attempt to extract all transmissions from database");

    return transmissionMapper.mapTransmissionToTransmissionDtoList(transmissionRepository.findAll());
  }

  public TransmissionDto getTransmissionDtoById(int id){
    log.info(String.format("An attempt to extract transmission with id %d from database" , id));

    return transmissionMapper.mapTransmissionToTransmissionDto(transmissionRepository.findById(id).orElseThrow(() -> {
      log.error(String.format("Exception caught: %s",TRANSMISSION_NOT_FOUND_MESSAGE));

      throw new EntityNotFoundException(TRANSMISSION_NOT_FOUND_MESSAGE);
    }));
  }

  public TransmissionDto getTransmissionDtoByName(String name){
    log.info(String.format("An attempt to extract transmission with name %s from database" , name));

    return transmissionMapper.mapTransmissionToTransmissionDto(transmissionRepository.findByName(name).orElseThrow(() -> {
      log.error(String.format("Exception caught: %s",TRANSMISSION_NOT_FOUND_MESSAGE));

      throw new EntityNotFoundException(TRANSMISSION_NOT_FOUND_MESSAGE);
    }));
  }

  public Transmission getTransmissionById(int id){
    log.info(String.format("An attempt to extract transmission with id %d from database" , id));

    return transmissionRepository.findById(id).orElseThrow(() -> {
      log.error(String.format("Exception caught: %s",TRANSMISSION_NOT_FOUND_MESSAGE));

      throw new EntityNotFoundException(TRANSMISSION_NOT_FOUND_MESSAGE);
    });
  }

  public Transmission getTransmissionByName(String name){
    log.info(String.format("An attempt to extract transmission with name %s from database" , name));

    return transmissionRepository.findByName(name).orElseThrow(() -> {
      log.error(String.format("Exception caught: %s",TRANSMISSION_NOT_FOUND_MESSAGE));

      throw new EntityNotFoundException(TRANSMISSION_NOT_FOUND_MESSAGE);
    });
  }

  public TransmissionDto updateTransmission(TransmissionRequest transmissionRequest , int id){
    TransmissionDto transmissionDto = getTransmissionDtoById(id);

    log.info(String.format("An attempt to update model with id %d",id));

    transmissionRepository.save(new Transmission(id,transmissionRequest.getTransmissionName()));
    return transmissionDto;
  }

  public TransmissionDto deleteTransmission(int id){
    TransmissionDto transmissionDto = getTransmissionDtoById(id);

    transmissionRepository.deleteById(id);

    log.info(String.format("Transmission with id %d was deleted",id));
    return transmissionDto;
  }

  private void transmissionValidation(TransmissionRequest transmissionRequest){
    transmissionRepository.findByName(transmissionRequest.getTransmissionName()).ifPresent(brand -> {
      log.error(String.format("Exception caught : %s" , TRANSMISSION_ALREADY_EXISTS_MESSAGE));

      throw new EntityAlreadyExistsException(TRANSMISSION_ALREADY_EXISTS_MESSAGE);
    });
  }
}
