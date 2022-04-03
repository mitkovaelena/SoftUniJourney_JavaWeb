package org.softuni.carDealer.services.impls;

import org.softuni.carDealer.domain.dtos.binding.add.PartAddDto;
import org.softuni.carDealer.domain.dtos.view.PartView;
import org.softuni.carDealer.domain.dtos.view.SupplierView;
import org.softuni.carDealer.domain.entities.Car;
import org.softuni.carDealer.domain.entities.Part;
import org.softuni.carDealer.domain.entities.Supplier;
import org.softuni.carDealer.repositories.CarRepository;
import org.softuni.carDealer.repositories.PartRepository;
import org.softuni.carDealer.repositories.SupplierRepository;
import org.softuni.carDealer.services.apis.PartService;
import org.softuni.carDealer.utils.ModelParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PartServiceImpl implements PartService<Part, Long> {
    private final PartRepository partRepository;
    private final CarRepository carRepository;
    private final SupplierRepository supplierRepository;

    @Autowired
    public PartServiceImpl(PartRepository carRepository, CarRepository carRepository1, SupplierRepository supplierRepository) {
        this.partRepository = carRepository;
        this.carRepository = carRepository1;
        this.supplierRepository = supplierRepository;
    }

    @Override
    public void save(PartAddDto partAddDto) {
        Part part = ModelParser.getInstance().map(partAddDto, Part.class);
        this.partRepository.save(part);
    }

    @Override
    public List<PartView> findAllParts() {
        List<Part> parts = this.partRepository.findAll();
        List<PartView> partViews = new ArrayList<>();

        for (Part part : parts) {
            partViews.add(ModelParser.getInstance().map(part, PartView.class));
        }
        return partViews;
    }

    @Override
    public PartView findPartById(Long id) {
        Part part = this.partRepository.findById(id).get();
        return ModelParser.getInstance().map(part, PartView.class);
    }

    @Override
    public void editPart(Long id, PartView partView) {
        Part part = this.partRepository.findById(id).get();
        part.setName(partView.getName());
        part.setPrice(partView.getPrice());
        this.partRepository.save(part);
    }

    @Override
    public SupplierView findSupplierByPartId(Long id) {
        Supplier supplier =  this.partRepository.findById(id).get().getSupplier();
        return ModelParser.getInstance().map(supplier, SupplierView.class);
    }

    @Override
    public void deletePart(Long id) {
        Part part = this.partRepository.findById(id).get();
        for (Car car : part.getCars()) {
            car.getParts().remove(part);
            this.carRepository.save(car);
        }
        Supplier supplier = part.getSupplier();
        supplier.getParts().remove(part);
        this.supplierRepository.save(supplier);

        this.partRepository.deleteById(id);
    }
}
