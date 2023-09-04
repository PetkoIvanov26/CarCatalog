package com.petkoivanov.carCatalog.services;

import com.petkoivanov.carCatalog.exceptions.EntityNotFoundException;
import com.petkoivanov.carCatalog.mappers.FuelTypeMapper;
import com.petkoivanov.carCatalog.models.dtos.FuelTypeDto;
import com.petkoivanov.carCatalog.models.entities.FuelType;
import com.petkoivanov.carCatalog.repositories.FuelTypeRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static com.petkoivanov.carCatalog.testUtils.constants.FuelTypeConstants.ID;
import static com.petkoivanov.carCatalog.testUtils.constants.FuelTypeConstants.NAME;

import static com.petkoivanov.carCatalog.testUtils.factories.FuelTypeFactory.getDefaultFuelType;
import static com.petkoivanov.carCatalog.testUtils.factories.FuelTypeFactory.getDefaultFuelTypeDto;
import static com.petkoivanov.carCatalog.testUtils.factories.FuelTypeFactory.getDefaultFuelTypeDtoList;
import static com.petkoivanov.carCatalog.testUtils.factories.FuelTypeFactory.getDefaultFuelTypeList;
import static com.petkoivanov.carCatalog.testUtils.factories.FuelTypeFactory.getDefaultFuelTypeRequest;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FuelTypeServiceTest {
  @Mock
  private FuelTypeMapper fuelTypeMapper;
  @Mock
  private FuelTypeRepository fuelTypeRepository;

  @InjectMocks
  private FuelTypeService fuelTypeService;

  @Test
  public void testAddFuelType_noExceptions_success(){
    FuelType expected = getDefaultFuelType();
    when(fuelTypeRepository.save(any())).thenReturn(expected);

    FuelType fuelType = fuelTypeService.addFuelType(getDefaultFuelTypeRequest());

    assertEquals(expected,fuelType);
  }

  @Test
  public void testGetAllFuelTypes_noExceptions_success(){
    List<FuelTypeDto> expected = getDefaultFuelTypeDtoList();

    when(fuelTypeMapper.mapFuelTypeToFuelTypeDtoList(any())).thenReturn(expected);
    when(fuelTypeRepository.findAll()).thenReturn(getDefaultFuelTypeList());

    List<FuelTypeDto> result = fuelTypeService.getAllFuelTypes();

    assertEquals(expected,result);
  }

  @Test
  public void testGetFuelTypeDtoByName_noExceptions_success(){
    FuelTypeDto expected = getDefaultFuelTypeDto();

    when(fuelTypeMapper.mapFuelTypeToFuelTypeDto(getDefaultFuelType())).thenReturn(expected);
    when(fuelTypeRepository.findByName(anyString())).thenReturn(Optional.of(getDefaultFuelType()));

    FuelTypeDto result = fuelTypeService.getFuelTypeDtoByName(NAME);

    assertEquals(expected,result);
  }


  @Test(expected = EntityNotFoundException.class)
  public void testGetFuelTypeDtoByName_throwsEntityNotFoundException_fail(){
    when(fuelTypeRepository.findByName(anyString())).thenReturn(Optional.empty());

    fuelTypeService.getFuelTypeDtoByName(NAME);
  }

  @Test
  public void testGetFuelTypeByName_noExceptions_success(){
    FuelType expected = getDefaultFuelType();

    when(fuelTypeRepository.findByName(anyString())).thenReturn(Optional.of(getDefaultFuelType()));

    FuelType result = fuelTypeService.getFuelTypeByName(NAME);

    assertEquals(expected,result);
  }


  @Test(expected = EntityNotFoundException.class)
  public void testGetFuelTypeByName_throwsEntityNotFoundException_fail(){
    when(fuelTypeRepository.findByName(anyString())).thenReturn(Optional.empty());

    fuelTypeService.getFuelTypeByName(NAME);
  }

  @Test
  public void testGetFuelTypeDtoById_noExceptions_success(){
    FuelTypeDto expected = getDefaultFuelTypeDto();

    when(fuelTypeMapper.mapFuelTypeToFuelTypeDto(getDefaultFuelType())).thenReturn(expected);
    when(fuelTypeRepository.findById(anyInt())).thenReturn(Optional.of(getDefaultFuelType()));

    FuelTypeDto result = fuelTypeService.getFuelTypeDtoById(ID);

    assertEquals(expected,result);
  }

  @Test(expected = EntityNotFoundException.class)
  public void testGetFuelTypeDtoById_throwsEntityNotFoundException_fail(){
    when(fuelTypeRepository.findById(anyInt())).thenReturn(Optional.empty());

    fuelTypeService.getFuelTypeDtoById(ID);
  }

  @Test
  public void testGetFuelTypeById_noExceptions_success(){
    FuelType expected = getDefaultFuelType();

    when(fuelTypeRepository.findById(anyInt())).thenReturn(Optional.of(getDefaultFuelType()));

    FuelType result = fuelTypeService.getFuelTypeById(ID);

    assertEquals(expected,result);
  }

  @Test(expected = EntityNotFoundException.class)
  public void testGetFuelTypeById_throwsEntityNotFoundException_fail(){
    when(fuelTypeRepository.findById(anyInt())).thenReturn(Optional.empty());

    fuelTypeService.getFuelTypeById(ID);
  }

  @Test
  public void testUpdateFuelType_noExceptions_success(){
    FuelTypeDto expected = getDefaultFuelTypeDto();
    when(fuelTypeMapper.mapFuelTypeToFuelTypeDto(any())).thenReturn(expected);
    when(fuelTypeRepository.findById(anyInt())).thenReturn(Optional.of(getDefaultFuelType()));
    when(fuelTypeRepository.save(any())).thenReturn(getDefaultFuelType());

    FuelTypeDto result = fuelTypeService.updateFuelType(getDefaultFuelTypeRequest() , ID);

    assertEquals(expected,result);
  }

  @Test(expected = EntityNotFoundException.class)
  public void testUpdateFuelType_EntityNotFoundException_fail(){
    when(fuelTypeRepository.findById(anyInt())).thenReturn(Optional.empty());

    fuelTypeService.updateFuelType(getDefaultFuelTypeRequest(),ID);
  }

  @Test
  public void testDeleteFuelType_noExceptions_success(){
    FuelTypeDto expected = getDefaultFuelTypeDto();
    when(fuelTypeMapper.mapFuelTypeToFuelTypeDto(any())).thenReturn(expected);
    when(fuelTypeRepository.findById(anyInt())).thenReturn(Optional.of(getDefaultFuelType()));

    FuelTypeDto result = fuelTypeService.deleteFuelType(ID);

    assertEquals(expected,result);
  }

  @Test(expected = EntityNotFoundException.class)
  public void testDeleteFuelType_EntityNotFoundException_fail(){
    when(fuelTypeRepository.findById(anyInt())).thenReturn(Optional.empty());

    fuelTypeService.deleteFuelType(ID);
  }
}
