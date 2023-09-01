package com.petkoivanov.carCatalog.repositories;

import com.petkoivanov.carCatalog.models.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface BrandRepository extends JpaRepository<Brand , Integer> {
  Optional<Brand> findByName(String brandName);
}
