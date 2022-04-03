package org.softuni.carDealer.controllers;

import org.modelmapper.ModelMapper;
import org.softuni.carDealer.domain.dtos.binding.add.CarAddDto;
import org.softuni.carDealer.domain.dtos.view.*;
import org.softuni.carDealer.domain.entities.Car;
import org.softuni.carDealer.domain.entities.Part;
import org.softuni.carDealer.domain.entities.Supplier;
import org.softuni.carDealer.services.apis.CarService;
import org.softuni.carDealer.services.apis.PartService;
import org.softuni.carDealer.services.apis.SupplierService;
import org.softuni.carDealer.utils.ModelParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/cars")
public class CarsController {

    private final CarService<Car, Long> carService;
    private final PartService<Part, Long> partService;

    @Autowired
    public CarsController(CarService<Car, Long> carService, PartService<Part, Long> partService) {
        this.carService = carService;
        this.partService = partService;
    }


    @GetMapping("/all")
    public String allCars(Model model) {
        List<CarMakeView> orderedCars = this.carService.findAllCars();
        model.addAttribute("orderedCars", orderedCars);

        return "cars/all";
    }

    @GetMapping("/{make}")
    public String carsByMake(@PathVariable("make") String make, Model model) {
        List<CarMakeView> orderedCars = this.carService.findAllCarsByMake(make);
        model.addAttribute("make", make);
        model.addAttribute("orderedCars", orderedCars);

        return "cars/all";
    }

    @GetMapping("/{id}/parts")
    public String carsWithParts(@PathVariable("id") Long id, Model model) {
        CarPartsView carWithPart = this.carService.findCarWithParts(id);
        model.addAttribute("carWithParts", carWithPart);

        return "cars/CarParts";
    }

    @GetMapping("/add")
    public String showAddCar(Model model) {
        List<PartView> parts = this.partService.findAllParts();
        model.addAttribute("parts", parts);
        return "cars/add";
    }

    @PostMapping("/add")
    public String addCar(HttpServletRequest request) throws ParseException {

        CarAddDto carAddDto = new CarAddDto();
        carAddDto.setMake(request.getParameter("make"));
        carAddDto.setModel(request.getParameter("model"));
        carAddDto.setTravelledDistance(Long.parseLong(request.getParameter("travelledDistance")));

        Set<PartView> parts = new HashSet<>();
        for (String partId : request.getParameterMap().get("parts")) {
            PartView part = this.partService.findPartById(Long.valueOf(partId));
            parts.add(part);
        }
        carAddDto.setParts(parts);

        this.carService.save(carAddDto);
        return "redirect:/cars/all";
    }
}
