package org.softuni.carDealer.domain.dtos.view;

import java.math.BigDecimal;

public class TotalCustomerSalesView {
    private String name;
    private Integer boughtCars;
    private BigDecimal spentMoney;

    public TotalCustomerSalesView() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBoughtCars() {
        return boughtCars;
    }

    public void setBoughtCars(Integer boughtCars) {
        this.boughtCars = boughtCars;
    }

    public BigDecimal getSpentMoney() {
        return spentMoney;
    }

    public void setSpentMoney(BigDecimal spentMoney) {
        this.spentMoney = spentMoney;
    }
}
