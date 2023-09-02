package com.petkoivanov.carCatalog.repositories;

import com.petkoivanov.carCatalog.models.entities.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ModelRepository extends JpaRepository<Model , Integer> {
  Optional<Model> findByName(String name);
  List<Model> findAllByBrandId(int id);
}
