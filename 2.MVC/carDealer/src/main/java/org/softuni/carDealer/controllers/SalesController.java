package org.softuni.carDealer.controllers;

import org.softuni.carDealer.domain.dtos.view.SaleView;
import org.softuni.carDealer.domain.dtos.view.SaleWithCarView;
import org.softuni.carDealer.domain.dtos.view.SupplierView;
import org.softuni.carDealer.domain.entities.Sale;
import org.softuni.carDealer.domain.entities.Supplier;
import org.softuni.carDealer.services.apis.SaleService;
import org.softuni.carDealer.services.apis.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/sales")
public class SalesController {

    private final SaleService<Sale, Long> saleService;

    @Autowired
    public SalesController(SaleService<Sale, Long> saleService) {
        this.saleService = saleService;
    }

    @GetMapping("")
    public String allSales(Model model){
        List<SaleWithCarView> saleViews =  this.saleService.allSalesWithCars();
        model.addAttribute("saleViews", saleViews);

        return "sales/salesWithCars";
    }

    @GetMapping("/{id}")
    public String suppliersByType(@PathVariable("id") Long id, Model model){
        List<SaleWithCarView> saleViews =  Collections.singletonList(this.saleService.salesWithCarsById(id));
        model.addAttribute("saleViews", saleViews);

        return "sales/salesWithCars";
    }

    @GetMapping("/discounted")
    public String allDiscountedSales(Model model){
        List<SaleWithCarView> saleViews =  this.saleService.allDiscountedSalesWithCars();
        model.addAttribute("saleViews", saleViews);

        return "sales/salesWithCars";
    }

    @GetMapping("/discounted/{percent}")
    public String allDiscountedSalesWithPercent(@PathVariable("percent") Double percent, Model model){
        List<SaleWithCarView> saleViews =  this.saleService.allDiscountedSalesWithCarsWithPercent(percent);
        model.addAttribute("saleViews", saleViews);

        return "sales/salesWithCars";
    }
}
