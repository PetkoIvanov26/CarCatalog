package com.petkoivanov.carCatalog.repositories;

import com.petkoivanov.carCatalog.models.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
@Repository
public interface CarRepository extends JpaRepository<Integer , Car> {
  Optional<Car> findByVinNumber(String vinNumber);
  Optional<Car> findByRegDate(LocalDate regDate);
  Optional<Car> findByModelId(int id);
  Optional<Car> findByFuelTypeId(int id);
  Optional<Car> findAllByOrderByPriceAsc();
  Optional<Car> findAllByOrderByPriceDesc();
  Optional<Car> findByTransmissionId(int id);
  Optional<Car> findByTransmissionIdAndModelId(int transmissionId , int modelId);
  Optional<Car> findByTransmissionIdAndFuelType(int transmissionId , int fuelTypeId);
  Optional<Car> findByModelIdAndFuelType(int modelId , int fuelTypeId);
  Optional<Car> findByModelIdAndPriceAndFuelTypeIdAndTransmissionId(int modelId , double price , int fuelType , int transmissionId);
  Optional<Car> findByModelIdAndFuelTypeIdAndTransmissionIdAndOrderByPriceAsc(int modelId , int fuelId , int transmissionId);
  Optional<Car> findByModelIdAndFuelTypeIdAndTransmissionIdAndOrderByPriceDesc(int modelId , int fuelId , int transmissionId);
}
