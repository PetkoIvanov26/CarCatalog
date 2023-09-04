package com.petkoivanov.carCatalog.testUtils.constants;

import java.sql.Date;

public class CarConstants {

  public static final int ID = 1;
  public static final String VIN_NUMBER = "Number";
  public static final int MODEL_ID = 1;
  public static final double PRICE = 0.0;
  public static final Date REGISTRATION_DATE = new Date(2023,9,4);
  public static final int TRANSMISSION_ID = 1;
  public static final int FUEL_TYPE_ID = 1;

  private CarConstants() throws IllegalAccessException{
    throw new IllegalAccessException();
  }

}
