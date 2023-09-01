package com.petkoivanov.carCatalog.repositories;

import com.petkoivanov.carCatalog.models.entities.FuelType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.font.OpenType;
import java.util.Optional;

public interface FuelTypeRepository extends JpaRepository<Integer , FuelType> {
  Optional<FuelType> findByName(String name);
}
