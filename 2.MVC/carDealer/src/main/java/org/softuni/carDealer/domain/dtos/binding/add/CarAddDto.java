package org.softuni.carDealer.domain.dtos.binding.add;

import org.softuni.carDealer.domain.dtos.binding.relations.PartDto;
import org.softuni.carDealer.domain.dtos.view.PartView;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Set;

public class CarAddDto {

    private String make;
    private String model;
    private Long travelledDistance;
    private Set<PartView> parts;

    public CarAddDto() {
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(Long travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    public Set<PartView> getParts() {
        return this.parts;
    }

    public void setParts(Set<PartView> parts) {
        this.parts = parts;
    }


}
