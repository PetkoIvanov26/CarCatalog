package com.petkoivanov.carCatalog.repositories;

import com.petkoivanov.carCatalog.models.entities.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ModelRepository extends JpaRepository<Integer , Model> {
  Optional<Model> findByName(String name);
  Optional<Model> findByBrandId(int id);
}
