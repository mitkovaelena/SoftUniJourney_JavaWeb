package org.softuni.carDealer.services.impls;

import org.softuni.carDealer.domain.dtos.binding.add.CarAddDto;
import org.softuni.carDealer.domain.dtos.binding.relations.CarDto;
import org.softuni.carDealer.domain.dtos.view.CarMakeView;
import org.softuni.carDealer.domain.dtos.view.CarPartsView;
import org.softuni.carDealer.domain.entities.Car;
import org.softuni.carDealer.repositories.CarRepository;
import org.softuni.carDealer.services.apis.CarService;
import org.softuni.carDealer.utils.ModelParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CarServiceImpl implements CarService<Car, Long> {
    private final CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<CarMakeView> findAllCars() {
        List<Car> cars = this.carRepository.findAllByOrderByModelAscTravelledDistanceDesc();
        List<CarMakeView> carDtos = new ArrayList<>();

        for (Car car : cars) {
            carDtos.add(ModelParser.getInstance().map(car, CarMakeView.class));
        }

        return carDtos;
    }

    @Override
    public List<CarMakeView> findAllCarsByMake(String make) {
        List<Car> cars = this.carRepository.findAllByMakeOrderByModelAscTravelledDistanceDesc(make);
        List<CarMakeView> carViews = new ArrayList<>();
        for (Car car : cars) {
            carViews.add(ModelParser.getInstance().map(car, CarMakeView.class));
        }
        return carViews;
    }

    @Override
    public CarPartsView findCarWithParts(Long id) {
        Optional<Car> carOptional = this.carRepository.findById(id);
        CarPartsView carPartsView = null;
        if(carOptional.isPresent()) {
            carPartsView = (ModelParser.getInstance().map(carOptional.get(), CarPartsView.class));
        }
        return carPartsView;
    }

    @Override
    public void save(CarAddDto carAddDto) {
        Car car = new Car();
        ModelParser.getInstance().map(carAddDto, car);
        car.setParts(new HashSet<>());
        car.setSales(new HashSet<>());
        this.carRepository.save(car);
    }
}
