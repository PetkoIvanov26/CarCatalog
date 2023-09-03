package com.petkoivanov.carCatalog.controllers;

import com.petkoivanov.carCatalog.models.dtos.TransmissionDto;
import com.petkoivanov.carCatalog.models.entities.Transmission;
import com.petkoivanov.carCatalog.models.requests.TransmissionRequest;
import com.petkoivanov.carCatalog.services.TransmissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

import java.net.URI;
import java.util.List;

import static com.petkoivanov.carCatalog.utils.URIConstants.TRANSMISSIONS_ID_PATH;
import static com.petkoivanov.carCatalog.utils.URIConstants.TRANSMISSIONS_PATH;

@RestController
public class TransmissionController {

  private static final Logger log = LoggerFactory.getLogger(TransmissionController.class);

  private final TransmissionService transmissionService;

  @Autowired
  public TransmissionController(TransmissionService transmissionService){
    this.transmissionService = transmissionService;
  }

  @PostMapping(TRANSMISSIONS_PATH)
  public ResponseEntity<Void> addTransmission(@RequestBody @Valid TransmissionRequest transmissionRequest){
    log.info("A request for a transmission to be added has been submitted");
    Transmission transmission = transmissionService.addTransmission(transmissionRequest);

    URI location = UriComponentsBuilder
      .fromUriString(TRANSMISSIONS_ID_PATH)
      .buildAndExpand(transmission.getId())
      .toUri();

    return ResponseEntity.created(location).build();
  }

  @GetMapping(TRANSMISSIONS_PATH)
  public ResponseEntity<List<TransmissionDto>> getAllTransmissions(){
    log.info("All transmissions were requested from the database");
    List<TransmissionDto> transmissions = transmissionService.getAllTransmissions();

    return ResponseEntity.ok(transmissions);
  }

  @GetMapping(TRANSMISSIONS_PATH)
  public ResponseEntity<TransmissionDto> getTransmissionByName(@RequestParam String transmissionName){
    log.info(String.format("Transmission with name %s was requested from database" , transmissionName));
    TransmissionDto transmission = transmissionService.getTransmissionDtoByName(transmissionName);

    return ResponseEntity.ok(transmission);
  }

  @PostMapping(TRANSMISSIONS_ID_PATH)
  public ResponseEntity<TransmissionDto> updateTransmission(
    @RequestBody @Valid TransmissionRequest transmissionRequest ,
    @PathVariable int id,
    @RequestParam(required = false) boolean returnOld){

    TransmissionDto transmission = transmissionService.updateTransmission(transmissionRequest,id);
    log.info(String.format("Transmission with id %d was updated",id));

    return returnOld ? ResponseEntity.ok(transmission) : ResponseEntity.noContent().build();
  }

  @DeleteMapping(TRANSMISSIONS_ID_PATH)
  public ResponseEntity<TransmissionDto> deleteTransmission(
    @PathVariable int id,
    @RequestParam(required = false) boolean returnOld){
    TransmissionDto transmission = transmissionService.deleteTransmission(id);
    log.info(String.format("Transmission with id %d was deleted" , id));

    return returnOld ? ResponseEntity.ok(transmission) : ResponseEntity.noContent().build();
  }
}
