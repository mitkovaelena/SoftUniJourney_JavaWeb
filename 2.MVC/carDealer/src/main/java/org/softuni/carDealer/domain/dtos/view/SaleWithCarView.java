package org.softuni.carDealer.domain.dtos.view;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class SaleWithCarView {
    private CarSaleView car;
    private String customerName;
    private Double discount;
    private BigDecimal carPrice;

    public SaleWithCarView() {
    }

    public CarSaleView getCar() {
        return car;
    }

    public void setCar(CarSaleView car) {
        this.car = car;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public BigDecimal getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(BigDecimal carPrice) {
        this.carPrice = carPrice;
    }

    public BigDecimal getPriceWithDiscount() {
        return carPrice.multiply(BigDecimal.valueOf(1 - discount)).setScale(2, RoundingMode.HALF_UP);
    }

}
