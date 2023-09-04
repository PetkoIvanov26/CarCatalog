package com.petkoivanov.carCatalog.services;

import com.petkoivanov.carCatalog.mappers.CarMapper;
import com.petkoivanov.carCatalog.models.requests.CarRequest;
import com.petkoivanov.carCatalog.repositories.CarRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceTest {

  @Mock
  private ModelService modelService;
  @Mock
  private TransmissionService transmissionService;
  @Mock
  private FuelTypeService fuelTypeService;
  @Mock
  private CarMapper carMapper;
  @Mock
  private CarRepository carRepository;
  @InjectMocks
  private CarService carService;

  @Test
  public void testAddCar_noExceptions_success(){
    
  }

}
