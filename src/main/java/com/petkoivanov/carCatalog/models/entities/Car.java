package com.petkoivanov.carCatalog.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "cars")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Car {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "vin_number")
  private String vinNumber;

  @OneToOne
  @JoinColumn(name = "model_id")
  private Model model;

  private double price;

  @Column(name = "reg_date")
  private Date registrationDate;

  @OneToOne
  @JoinColumn(name = "transmission_id")
  private Transmission transmission;

  @OneToOne
  @JoinColumn(name = "fuel_type_id")
  private FuelType fuelType;

  public Car(String vinNumber , Model model ,
             double price , Date registrationDate ,
             Transmission transmission , FuelType fuelType){
    this.vinNumber = vinNumber;
    this.model = model;
    this.price = price;
    this.registrationDate = registrationDate;
    this.transmission = transmission;
    this.fuelType = fuelType;
  }
}
