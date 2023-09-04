package com.petkoivanov.carCatalog.services;

import com.petkoivanov.carCatalog.exceptions.EntityNotFoundException;
import com.petkoivanov.carCatalog.mappers.ModelMapper;
import com.petkoivanov.carCatalog.models.dtos.ModelDto;
import com.petkoivanov.carCatalog.models.entities.Model;
import com.petkoivanov.carCatalog.repositories.ModelRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static com.petkoivanov.carCatalog.testUtils.constants.BrandConstants.ID;
import static com.petkoivanov.carCatalog.testUtils.constants.BrandConstants.NAME;
import static com.petkoivanov.carCatalog.testUtils.factories.BrandFactory.getDefaultBrand;
import static com.petkoivanov.carCatalog.testUtils.factories.ModelFactory.getDefaultModel;
import static com.petkoivanov.carCatalog.testUtils.factories.ModelFactory.getDefaultModelDto;
import static com.petkoivanov.carCatalog.testUtils.factories.ModelFactory.getDefaultModelDtoList;
import static com.petkoivanov.carCatalog.testUtils.factories.ModelFactory.getDefaultModelList;
import static com.petkoivanov.carCatalog.testUtils.factories.ModelFactory.getDefaultModelRequest;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)
public class ModelServiceTest {
  @Mock
  private ModelMapper modelMapper;
  @Mock
  private ModelRepository modelRepository;

  @Mock
  private BrandService brandService;

  @InjectMocks
  private ModelService modelService;

  @Test
  public void testAddModel_noExceptions_success(){
    Model expected = getDefaultModel();
    when(brandService.getBrandById(anyInt())).thenReturn(getDefaultBrand());
    when(modelRepository.save(any())).thenReturn(expected);

    Model result = modelService.addModel(getDefaultModelRequest());

    assertEquals(expected,result);
  }

  @Test
  public void testGetAllModels_noExceptions_success(){
    List<ModelDto> expected = getDefaultModelDtoList();

    when(modelMapper.mapModelToModelDtoList(any())).thenReturn(expected);
    when(modelRepository.findAll()).thenReturn(getDefaultModelList());

    List<ModelDto> result = modelService.getAllModels();

    assertEquals(expected,result);
  }

  @Test
  public void testGetModelDtoByName_noExceptions_success(){
    ModelDto expected = getDefaultModelDto();

    when(modelMapper.mapModelToModelDto(getDefaultModel())).thenReturn(expected);
    when(modelRepository.findByName(anyString())).thenReturn(Optional.of(getDefaultModel()));

    ModelDto result = modelService.getModelDtoByName(NAME);

    assertEquals(expected,result);
  }

  @Test(expected = EntityNotFoundException.class)
  public void testGetModelDtoByName_throwsEntityNotFoundException_fail(){
    when(modelRepository.findByName(anyString())).thenReturn(Optional.empty());

    modelService.getModelDtoByName(NAME);
  }

  @Test
  public void testGetModelDByName_noExceptions_success(){
    Model expected = getDefaultModel();

    when(modelRepository.findByName(anyString())).thenReturn(Optional.of(getDefaultModel()));

    Model result = modelService.getModelByName(NAME);

    assertEquals(expected,result);
  }

  @Test(expected = EntityNotFoundException.class)
  public void testGetModelByName_throwsEntityNotFoundException_fail(){
    when(modelRepository.findByName(anyString())).thenReturn(Optional.empty());

    modelService.getModelByName(NAME);
  }

  @Test
  public void testGetModelDtoById_noExceptions_success(){
    ModelDto expected = getDefaultModelDto();

    when(modelMapper.mapModelToModelDto(getDefaultModel())).thenReturn(expected);
    when(modelRepository.findById(anyInt())).thenReturn(Optional.of(getDefaultModel()));

    ModelDto result = modelService.getModelDtoById(ID);

    assertEquals(expected,result);
  }

  @Test(expected = EntityNotFoundException.class)
  public void testGetModelDtoById_throwsEntityNotFoundException_fail(){
    when(modelRepository.findById(anyInt())).thenReturn(Optional.empty());

    modelService.getModelDtoById(ID);
  }

  @Test
  public void testGetModelById_noExceptions_success(){
    Model expected = getDefaultModel();

    when(modelRepository.findById(anyInt())).thenReturn(Optional.of(getDefaultModel()));

    Model result = modelService.getModelById(ID);

    assertEquals(expected,result);
  }

  @Test(expected = EntityNotFoundException.class)
  public void testGetModelById_throwsEntityNotFoundException_fail(){
    when(modelRepository.findById(anyInt())).thenReturn(Optional.empty());

    modelService.getModelById(ID);
  }

  @Test
  public void testUpdateModel_noExceptions_success(){
    ModelDto expected = getDefaultModelDto();
    when(modelMapper.mapModelToModelDto(any())).thenReturn(expected);
    when(modelRepository.findById(anyInt())).thenReturn(Optional.of(getDefaultModel()));
    when(modelRepository.save(any())).thenReturn(getDefaultModel());

    ModelDto result = modelService.updateModel(getDefaultModelRequest() , ID);

    assertEquals(expected,result);
  }

  @Test(expected = EntityNotFoundException.class)
  public void testUpdateModel_EntityNotFoundException_fail(){
    when(modelRepository.findById(anyInt())).thenReturn(Optional.empty());

    modelService.updateModel(getDefaultModelRequest(),ID);
  }

  @Test
  public void testDeleteModel_noExceptions_success(){
    ModelDto expected = getDefaultModelDto();
    when(modelMapper.mapModelToModelDto(any())).thenReturn(expected);
    when(modelRepository.findById(anyInt())).thenReturn(Optional.of(getDefaultModel()));

    ModelDto result = modelService.deleteModel(ID);

    assertEquals(expected,result);
  }

  @Test(expected = EntityNotFoundException.class)
  public void testDeleteModel_EntityNotFoundException_fail(){
    when(modelRepository.findById(anyInt())).thenReturn(Optional.empty());

    modelService.deleteModel(ID);
  }
}
