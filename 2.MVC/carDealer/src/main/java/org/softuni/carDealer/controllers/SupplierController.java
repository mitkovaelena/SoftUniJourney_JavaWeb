package org.softuni.carDealer.controllers;

import org.softuni.carDealer.domain.dtos.view.SupplierView;
import org.softuni.carDealer.domain.entities.Supplier;
import org.softuni.carDealer.services.apis.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/suppliers")
public class SupplierController {

    private final SupplierService<Supplier, Long> supplierService;

    @Autowired
    public SupplierController(SupplierService<Supplier, Long> supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping("/{type}")
    public String suppliersByType(@PathVariable("type") String type, Model model){
        List<SupplierView> orderedSuppliers =  this.supplierService.findAllSuppliersByType(type);
        model.addAttribute("type", type);
        model.addAttribute("orderedSuppliers", orderedSuppliers);

        return "suppliers/suppliersByType";
    }
}
