package org.softuni.carDealer.services.apis;


import org.softuni.carDealer.domain.dtos.binding.add.CarAddDto;
import org.softuni.carDealer.domain.dtos.binding.relations.CarDto;
import org.softuni.carDealer.domain.dtos.view.CarMakeView;
import org.softuni.carDealer.domain.dtos.view.CarPartsView;

import java.util.List;

public interface CarService<Car, Long> {

    List<CarMakeView> findAllCars();

    List<CarMakeView> findAllCarsByMake(String make);

    CarPartsView findCarWithParts(Long id);

    void save(CarAddDto carAddDto);
}
