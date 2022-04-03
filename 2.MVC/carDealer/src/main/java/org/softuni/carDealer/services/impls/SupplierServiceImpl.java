package org.softuni.carDealer.services.impls;

import org.softuni.carDealer.domain.dtos.binding.add.SupplierAddDto;
import org.softuni.carDealer.domain.dtos.binding.relations.SupplierDto;
import org.softuni.carDealer.domain.dtos.view.SupplierView;
import org.softuni.carDealer.domain.entities.Supplier;
import org.softuni.carDealer.repositories.SupplierRepository;
import org.softuni.carDealer.services.apis.SupplierService;
import org.softuni.carDealer.utils.ModelParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierServiceImpl(SupplierRepository carRepository) {
        this.supplierRepository = carRepository;
    }

    @Override
    public void save(SupplierAddDto supplierDao) {
        Supplier supplier = ModelParser.getInstance().map(supplierDao, Supplier.class);
        this.supplierRepository.saveAndFlush(supplier);
    }

    @Override
    public List<SupplierView> findAllSuppliers() {
        List<Supplier> suppliers = this.supplierRepository.findAll();
        List<SupplierView> supplierViews = new ArrayList<>();

        for (Supplier supplier : suppliers) {
            supplierViews.add(ModelParser.getInstance().map(supplier, SupplierView.class));
        }
        return supplierViews;
    }

    @Override
    public SupplierDto findSupplierById(long id) {
        Supplier supplier =  this.supplierRepository.findById((Long)id).get();
        return ModelParser.getInstance().map(supplier, SupplierDto.class);
    }

    @Override
    public List<SupplierView> findAllSuppliersByType(String type) {
        List<Supplier> suppliers = new ArrayList<>();
        if (type.equals("local")) {
            suppliers = this.supplierRepository.findAllByImporterEquals(false);
        } else if (type.equals("importers")) {
            suppliers = this.supplierRepository.findAllByImporterEquals(true);
        } else {
            //ToDo ?
        }

        List<SupplierView> supplierViews = new ArrayList<>();
        for (Supplier supplier : suppliers) {
            supplierViews.add(ModelParser.getInstance().map(supplier, SupplierView.class));
        }
        return supplierViews;


    }


}
