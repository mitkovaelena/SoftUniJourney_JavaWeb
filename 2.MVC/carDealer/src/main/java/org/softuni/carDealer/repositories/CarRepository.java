package org.softuni.carDealer.repositories;

import org.softuni.carDealer.domain.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findAllByMakeOrderByModelAscTravelledDistanceDesc(String make);
    List<Car> findAllByOrderByModelAscTravelledDistanceDesc();

}
