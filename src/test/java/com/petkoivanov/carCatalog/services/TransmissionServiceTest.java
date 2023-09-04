package com.petkoivanov.carCatalog.services;

import com.petkoivanov.carCatalog.exceptions.EntityNotFoundException;
import com.petkoivanov.carCatalog.mappers.TransmissionMapper;
import com.petkoivanov.carCatalog.models.dtos.TransmissionDto;
import com.petkoivanov.carCatalog.models.entities.Transmission;
import com.petkoivanov.carCatalog.repositories.TransmissionRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static com.petkoivanov.carCatalog.testUtils.constants.TransmissionConstants.ID;
import static com.petkoivanov.carCatalog.testUtils.constants.TransmissionConstants.NAME;
import static com.petkoivanov.carCatalog.testUtils.factories.TransmissionFactory.getDefaultTransmission;
import static com.petkoivanov.carCatalog.testUtils.factories.TransmissionFactory.getDefaultTransmissionDto;
import static com.petkoivanov.carCatalog.testUtils.factories.TransmissionFactory.getDefaultTransmissionDtoList;
import static com.petkoivanov.carCatalog.testUtils.factories.TransmissionFactory.getDefaultTransmissionList;
import static com.petkoivanov.carCatalog.testUtils.factories.TransmissionFactory.getDefaultTransmissionRequest;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TransmissionServiceTest {
  @Mock
  private TransmissionMapper transmissionMapper;
  @Mock
  private TransmissionRepository transmissionRepository;

  @InjectMocks
  private TransmissionService transmissionService;

  @Test
  public void testAddTransmission_noExceptions_success(){
    Transmission expected = getDefaultTransmission();
    when(transmissionRepository.save(any())).thenReturn(expected);

    Transmission brand = transmissionService.addTransmission(getDefaultTransmissionRequest());

    assertEquals(expected,brand);
  }

  @Test
  public void testGetAllTransmissions_noExceptions_success(){
    List<TransmissionDto> expected = getDefaultTransmissionDtoList();

    when(transmissionMapper.mapTransmissionToTransmissionDtoList(any())).thenReturn(expected);
    when(transmissionRepository.findAll()).thenReturn(getDefaultTransmissionList());

    List<TransmissionDto> result = transmissionService.getAllTransmissions();

    assertEquals(expected,result);
  }

  @Test
  public void testGetTransmissionDtoByName_noExceptions_success(){
    TransmissionDto expected = getDefaultTransmissionDto();

    when(transmissionMapper.mapTransmissionToTransmissionDto(getDefaultTransmission())).thenReturn(expected);
    when(transmissionRepository.findByName(anyString())).thenReturn(Optional.of(getDefaultTransmission()));

    TransmissionDto result = transmissionService.getTransmissionDtoByName(NAME);

    assertEquals(expected,result);
  }


  @Test(expected = EntityNotFoundException.class)
  public void testGetTransmissionDtoByName_throwsEntityNotFoundException_fail(){
    when(transmissionRepository.findByName(anyString())).thenReturn(Optional.empty());

    transmissionService.getTransmissionDtoByName(NAME);
  }

  @Test
  public void testGetTransmissionByName_noExceptions_success(){
    Transmission expected = getDefaultTransmission();

    when(transmissionRepository.findByName(anyString())).thenReturn(Optional.of(getDefaultTransmission()));

    Transmission result = transmissionService.getTransmissionByName(NAME);

    assertEquals(expected,result);
  }


  @Test(expected = EntityNotFoundException.class)
  public void testGetTransmissionByName_throwsEntityNotFoundException_fail(){
    when(transmissionRepository.findByName(anyString())).thenReturn(Optional.empty());

    transmissionService.getTransmissionByName(NAME);
  }

  @Test
  public void testGetTransmissionDtoById_noExceptions_success(){
    TransmissionDto expected = getDefaultTransmissionDto();

    when(transmissionMapper.mapTransmissionToTransmissionDto(getDefaultTransmission())).thenReturn(expected);
    when(transmissionRepository.findById(anyInt())).thenReturn(Optional.of(getDefaultTransmission()));

    TransmissionDto result = transmissionService.getTransmissionDtoById(ID);

    assertEquals(expected,result);
  }

  @Test(expected = EntityNotFoundException.class)
  public void testGetTransmissionDtoById_throwsEntityNotFoundException_fail(){
    when(transmissionRepository.findById(anyInt())).thenReturn(Optional.empty());

    transmissionService.getTransmissionDtoById(ID);
  }

  @Test
  public void testGetTransmissionById_noExceptions_success(){
    Transmission expected = getDefaultTransmission();

    when(transmissionRepository.findById(anyInt())).thenReturn(Optional.of(getDefaultTransmission()));

    Transmission result = transmissionService.getTransmissionById(ID);

    assertEquals(expected,result);
  }

  @Test(expected = EntityNotFoundException.class)
  public void testGetTransmissionById_throwsEntityNotFoundException_fail(){
    when(transmissionRepository.findById(anyInt())).thenReturn(Optional.empty());

    transmissionService.getTransmissionById(ID);
  }

  @Test
  public void testUpdateTransmission_noExceptions_success(){
    TransmissionDto expected = getDefaultTransmissionDto();
    when(transmissionMapper.mapTransmissionToTransmissionDto(any())).thenReturn(expected);
    when(transmissionRepository.findById(anyInt())).thenReturn(Optional.of(getDefaultTransmission()));
    when(transmissionRepository.save(any())).thenReturn(getDefaultTransmission());

    TransmissionDto result = transmissionService.updateTransmission(getDefaultTransmissionRequest() , ID);

    assertEquals(expected,result);
  }

  @Test(expected = EntityNotFoundException.class)
  public void testUpdateTransmission_EntityNotFoundException_fail(){
    when(transmissionRepository.findById(anyInt())).thenReturn(Optional.empty());

    transmissionService.updateTransmission(getDefaultTransmissionRequest(),ID);
  }

  @Test
  public void testDeleteTransmission_noExceptions_success(){
    TransmissionDto expected = getDefaultTransmissionDto();
    when(transmissionMapper.mapTransmissionToTransmissionDto(any())).thenReturn(expected);
    when(transmissionRepository.findById(anyInt())).thenReturn(Optional.of(getDefaultTransmission()));

    TransmissionDto result = transmissionService.deleteTransmission(ID);

    assertEquals(expected,result);
  }

  @Test(expected = EntityNotFoundException.class)
  public void testDeleteTransmission_EntityNotFoundException_fail(){
    when(transmissionRepository.findById(anyInt())).thenReturn(Optional.empty());

    transmissionService.deleteTransmission(ID);
  }
}
