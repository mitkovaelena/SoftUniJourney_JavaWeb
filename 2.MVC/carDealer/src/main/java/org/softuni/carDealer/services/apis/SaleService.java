package org.softuni.carDealer.services.apis;

import org.softuni.carDealer.domain.dtos.binding.add.SaleAddDto;
import org.softuni.carDealer.domain.dtos.view.SaleWithCarView;

import java.util.List;

public interface SaleService<Sale, Long> {
    void saveSaleDto(SaleAddDto saleAddDto);

    List<SaleWithCarView> allSalesWithCars();

    SaleWithCarView salesWithCarsById(Long id);

    List<SaleWithCarView> allDiscountedSalesWithCars();

    List<SaleWithCarView> allDiscountedSalesWithCarsWithPercent(Double percent);


}
