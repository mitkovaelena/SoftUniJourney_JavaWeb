package org.softuni.carDealer.domain.dtos.binding.add;

import org.softuni.carDealer.domain.dtos.binding.relations.SupplierDto;

import java.math.BigDecimal;

public class PartAddDto {
    private String name;
    private BigDecimal price;
    private Long quantity;
    private SupplierDto supplier;

    public PartAddDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public SupplierDto getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierDto supplier) {
        this.supplier = supplier;
    }
}
