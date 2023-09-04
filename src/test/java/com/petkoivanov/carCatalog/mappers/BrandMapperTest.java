package com.petkoivanov.carCatalog.mappers;

import com.petkoivanov.carCatalog.models.dtos.BrandDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static com.petkoivanov.carCatalog.testUtils.factories.BrandFactory.getDefaultBrand;
import static com.petkoivanov.carCatalog.testUtils.factories.BrandFactory.getDefaultBrandList;
import static com.petkoivanov.carCatalog.testUtils.constants.BrandConstants.NAME;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class BrandMapperTest {

  @InjectMocks
  private BrandMapper brandMapper;

  @Test
  public void testMapBrandToBrandDtoList_success(){
    List<BrandDto> brands = brandMapper.mapBrandToBrandDtoList(getDefaultBrandList());

    BrandDto brandDto = brands.get(0);
    assertEquals(brandDto.getBrandName() , NAME);
  }

  @Test
  public void testMapBrandToBrandDto_success(){
    BrandDto actualDto = brandMapper.mapBrandToBrandDto(getDefaultBrand());

    assertEquals(actualDto.getBrandName(),NAME);
  }
}
