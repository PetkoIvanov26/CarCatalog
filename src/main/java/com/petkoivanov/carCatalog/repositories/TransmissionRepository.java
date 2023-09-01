package com.petkoivanov.carCatalog.repositories;

import com.petkoivanov.carCatalog.models.entities.Transmission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransmissionRepository extends JpaRepository<Integer , Transmission> {
  Optional<Transmission> findByName(String name);
}
