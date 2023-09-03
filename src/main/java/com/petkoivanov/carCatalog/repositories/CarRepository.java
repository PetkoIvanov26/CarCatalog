package com.petkoivanov.carCatalog.repositories;

import com.petkoivanov.carCatalog.models.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Repository
public interface CarRepository extends JpaRepository<Car , Integer> {
  Optional<Car> findByVinNumber(String vinNumber);
  Optional<Car> findByRegistrationDate(LocalDate regDate);
  List<Car> findAllByModelId(int id);
  List<Car> findAllByFuelTypeId(int id);
  List<Car> findAllByTransmissionId(int id);
  List<Car> findAllByOrderByPriceAsc();
  List<Car> findAllByOrderByPriceDesc();
  List<Car> findAlLByPriceGreaterThan(double price);
  List<Car> findAllByPriceLessThan(double price);
  List<Car> findAllByModelIdAndTransmissionId(int transmissionId , int modelId);
  List<Car> findAllByTransmissionIdAndFuelTypeId(int transmissionId , int fuelTypeId);
  List<Car> findAllByModelIdAndFuelTypeId(int modelId , int fuelTypeId);
  List<Car> findAllByModelIdAndFuelTypeIdAndTransmissionId(int modelId,int fuelTypeId,int transmissionId);
  List<Car> findByModelIdAndFuelTypeIdAndTransmissionIdAndPriceGreaterThan(int modelId , double price , int fuelType , int transmissionId);
  List<Car> findByModelIdAndFuelTypeIdAndTransmissionIdAndPriceLessThan(int modelId , double price , int fuelType , int transmissionId);
  List<Car> findByModelIdAndFuelTypeIdAndTransmissionIdOrderByPriceAsc(int modelId , int fuelId , int transmissionId);
  List<Car> findByModelIdAndFuelTypeIdAndTransmissionIdOrderByPriceDesc(int modelId , int fuelId , int transmissionId);
}
