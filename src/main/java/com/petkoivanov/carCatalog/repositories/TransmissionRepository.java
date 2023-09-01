package com.petkoivanov.carCatalog.repositories;

import com.petkoivanov.carCatalog.models.entities.Transmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface TransmissionRepository extends JpaRepository<Transmission , Integer> {
  Optional<Transmission> findByName(String name);
}
