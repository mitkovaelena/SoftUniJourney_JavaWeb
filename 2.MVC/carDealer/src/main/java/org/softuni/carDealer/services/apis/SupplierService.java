package org.softuni.carDealer.services.apis;

import org.softuni.carDealer.domain.dtos.binding.add.SupplierAddDto;
import org.softuni.carDealer.domain.dtos.binding.relations.SupplierDto;
import org.softuni.carDealer.domain.dtos.view.SupplierView;

import java.util.List;

public interface SupplierService<Supplier, Long> {
    void save(SupplierAddDto supplierDao);

    List<SupplierView> findAllSuppliers();

    List<SupplierView> findAllSuppliersByType(String type);

    SupplierDto findSupplierById(long id);
}
