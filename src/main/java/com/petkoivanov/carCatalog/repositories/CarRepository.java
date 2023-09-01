package com.petkoivanov.carCatalog.repositories;

import com.petkoivanov.carCatalog.models.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Repository
public interface CarRepository extends JpaRepository<Integer , Car> {
  Optional<Car> findByVinNumber(String vinNumber);
  Optional<Car> findByRegDate(LocalDate regDate);
  List<Car> findAllByModelId(int id);
  List<Car> findAllByFuelTypeId(int id);
  List<Car> findAllByOrderByPriceAsc();
  List<Car> findAllByOrderByPriceDesc();
  List<Car> findAllByTransmissionId(int id);
  List<Car> findAllByTransmissionIdAndModelId(int transmissionId , int modelId);
  List<Car> findAllByTransmissionIdAndFuelType(int transmissionId , int fuelTypeId);
  List<Car> findAllByModelIdAndFuelType(int modelId , int fuelTypeId);
  List<Car> findByModelIdAndFuelTypeIdAndTransmissionIdAndPriceGreaterThan(int modelId , double price , int fuelType , int transmissionId);
  List<Car> findByModelIdAndFuelTypeIdAndTransmissionIdAndPriceLessThan(int modelId , double price , int fuelType , int transmissionId);
  List<Car> findByModelIdAndFuelTypeIdAndTransmissionIdAndOrderByPriceAsc(int modelId , int fuelId , int transmissionId);
  List<Car> findByModelIdAndFuelTypeIdAndTransmissionIdAndOrderByPriceDesc(int modelId , int fuelId , int transmissionId);
}
