package com.petkoivanov.carCatalog.repositories;

import com.petkoivanov.carCatalog.models.entities.FuelType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface FuelTypeRepository extends JpaRepository<FuelType , Integer> {
  Optional<FuelType> findByName(String name);
}
