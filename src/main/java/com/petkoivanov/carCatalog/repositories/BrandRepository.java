package com.petkoivanov.carCatalog.repositories;

import com.petkoivanov.carCatalog.models.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BrandRepository extends JpaRepository<Integer , Brand> {
  Optional<Brand> finByName(String brandName);
}
