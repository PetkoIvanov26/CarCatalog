package com.petkoivanov.carCatalog.repositories;

import com.petkoivanov.carCatalog.models.entities.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ModelRepository extends JpaRepository<Integer , Model> {
  Optional<Model> findByName(String name);
  Optional<Model> findByBrandId(int id);
}
