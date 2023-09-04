package com.petkoivanov.carCatalog.mappers;

import com.petkoivanov.carCatalog.models.dtos.ModelDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static com.petkoivanov.carCatalog.testUtils.constants.ModelConstants.NAME;
import static com.petkoivanov.carCatalog.testUtils.factories.BrandFactory.getDefaultBrandDto;
import static com.petkoivanov.carCatalog.testUtils.factories.ModelFactory.getDefaultModel;
import static com.petkoivanov.carCatalog.testUtils.factories.ModelFactory.getDefaultModelList;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ModelMapperTest {

  @Mock
  private BrandMapper brandMapper;

  @InjectMocks
  private ModelMapper modelMapper;

  @Test
  public void testMapModelToModelDtoList_success(){
    when(brandMapper.mapBrandToBrandDto(any())).thenReturn(getDefaultBrandDto());

    List<ModelDto> models = modelMapper.mapModelToModelDtoList(getDefaultModelList());

    ModelDto modelDto = models.get(0);

    assertEquals(modelDto.getModelName() , NAME);
    assertEquals(modelDto.getBrand(), getDefaultBrandDto());
  }

  @Test
  public void testMapModelToModelDto_success(){
    when(brandMapper.mapBrandToBrandDto(any())).thenReturn(getDefaultBrandDto());

    ModelDto modelDto = modelMapper.mapModelToModelDto(getDefaultModel());
    
    assertEquals(modelDto.getModelName() , NAME);
    assertEquals(modelDto.getBrand(), getDefaultBrandDto());
  }

}
