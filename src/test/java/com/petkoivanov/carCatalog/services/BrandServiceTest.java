package com.petkoivanov.carCatalog.services;

import com.petkoivanov.carCatalog.exceptions.EntityNotFoundException;
import com.petkoivanov.carCatalog.mappers.BrandMapper;
import com.petkoivanov.carCatalog.models.dtos.BrandDto;
import com.petkoivanov.carCatalog.models.entities.Brand;
import com.petkoivanov.carCatalog.repositories.BrandRepository;
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
import static com.petkoivanov.carCatalog.testUtils.factories.BrandFactory.getDefaultBrandDto;
import static com.petkoivanov.carCatalog.testUtils.factories.BrandFactory.getDefaultBrandDtoList;
import static com.petkoivanov.carCatalog.testUtils.factories.BrandFactory.getDefaultBrandList;
import static com.petkoivanov.carCatalog.testUtils.factories.BrandFactory.getDefaultBrandRequest;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BrandServiceTest {

  @Mock
  private BrandMapper brandMapper;
  @Mock
  private BrandRepository brandRepository;

  @InjectMocks
  private BrandService brandService;

  @Test
  public void testAddBrand_noExceptions_success(){
    Brand expected = getDefaultBrand();
    when(brandRepository.save(any())).thenReturn(expected);

    Brand brand = brandService.addBrand(getDefaultBrandRequest());

    assertEquals(expected,brand);
  }

  @Test
  public void testGetAllBrands_noExceptions_success(){
    List<BrandDto> expected = getDefaultBrandDtoList();

    when(brandMapper.mapBrandToBrandDtoList(any())).thenReturn(expected);
    when(brandRepository.findAll()).thenReturn(getDefaultBrandList());

    List<BrandDto> result = brandService.getAllBrands();

    assertEquals(expected,result);
  }

  @Test
  public void testGetBrandDtoByName_noExceptions_success(){
    BrandDto expected = getDefaultBrandDto();

    when(brandMapper.mapBrandToBrandDto(getDefaultBrand())).thenReturn(expected);
    when(brandRepository.findByName(anyString())).thenReturn(Optional.of(getDefaultBrand()));

    BrandDto result = brandService.getBrandDtoByName(NAME);

    assertEquals(expected,result);
  }


  @Test(expected = EntityNotFoundException.class)
  public void testGetBrandDtoByName_throwsEntityNotFoundException_fail(){
    when(brandRepository.findByName(anyString())).thenReturn(Optional.empty());

    brandService.getBrandDtoByName(NAME);
  }

  @Test
  public void testGetBrandDByName_noExceptions_success(){
    Brand expected = getDefaultBrand();

    when(brandRepository.findByName(anyString())).thenReturn(Optional.of(getDefaultBrand()));

    Brand result = brandService.getBrandByName(NAME);

    assertEquals(expected,result);
  }


  @Test(expected = EntityNotFoundException.class)
  public void testGetBrandByName_throwsEntityNotFoundException_fail(){
    when(brandRepository.findByName(anyString())).thenReturn(Optional.empty());

    brandService.getBrandByName(NAME);
  }

  @Test
  public void testGetBrandDtoById_noExceptions_success(){
    BrandDto expected = getDefaultBrandDto();

    when(brandMapper.mapBrandToBrandDto(getDefaultBrand())).thenReturn(expected);
    when(brandRepository.findById(anyInt())).thenReturn(Optional.of(getDefaultBrand()));

    BrandDto result = brandService.getBrandDtoById(ID);

    assertEquals(expected,result);
  }

  @Test(expected = EntityNotFoundException.class)
  public void testGetBrandDtoById_throwsEntityNotFoundException_fail(){
    when(brandRepository.findById(anyInt())).thenReturn(Optional.empty());

    brandService.getBrandDtoById(ID);
  }

  @Test
  public void testGetBrandById_noExceptions_success(){
    Brand expected = getDefaultBrand();

    when(brandRepository.findById(anyInt())).thenReturn(Optional.of(getDefaultBrand()));

    Brand result = brandService.getBrandById(ID);

    assertEquals(expected,result);
  }

  @Test(expected = EntityNotFoundException.class)
  public void testGetBrandById_throwsEntityNotFoundException_fail(){
    when(brandRepository.findById(anyInt())).thenReturn(Optional.empty());

    brandService.getBrandById(ID);
  }

  @Test
  public void testUpdateBrand_noExceptions_success(){
    BrandDto expected = getDefaultBrandDto();
    when(brandMapper.mapBrandToBrandDto(any())).thenReturn(expected);
    when(brandRepository.findById(anyInt())).thenReturn(Optional.of(getDefaultBrand()));
    when(brandRepository.save(any())).thenReturn(getDefaultBrand());

    BrandDto result = brandService.updateBrand(getDefaultBrandRequest() , ID);

    assertEquals(expected,result);
  }

  @Test(expected = EntityNotFoundException.class)
  public void testUpdateBrand_EntityNotFoundException_fail(){
    when(brandRepository.findById(anyInt())).thenReturn(Optional.empty());

    brandService.updateBrand(getDefaultBrandRequest(),ID);
  }

  @Test
  public void testDeleteBrand_noExceptions_success(){
    BrandDto expected = getDefaultBrandDto();
    when(brandMapper.mapBrandToBrandDto(any())).thenReturn(expected);
    when(brandRepository.findById(anyInt())).thenReturn(Optional.of(getDefaultBrand()));

    BrandDto result = brandService.deleteBrand(ID);

    assertEquals(expected,result);
  }

  @Test(expected = EntityNotFoundException.class)
  public void testDeleteBrand_EntityNotFoundException_fail(){
    when(brandRepository.findById(anyInt())).thenReturn(Optional.empty());

    brandService.deleteBrand(ID);
  }
}
