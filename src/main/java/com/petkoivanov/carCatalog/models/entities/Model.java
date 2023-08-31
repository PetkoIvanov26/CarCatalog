package com.petkoivanov.carCatalog.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "models")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Model {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String name;

  @OneToOne
  @JoinColumn(name = "brand_id")
  private Brand brand;

  public Model(String name , Brand brand){
    this.name = name;
    this.brand = brand;
  }
}
