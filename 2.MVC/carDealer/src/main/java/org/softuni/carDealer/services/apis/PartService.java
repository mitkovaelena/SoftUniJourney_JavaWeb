package org.softuni.carDealer.services.apis;

import org.softuni.carDealer.domain.dtos.binding.add.PartAddDto;
import org.softuni.carDealer.domain.dtos.binding.relations.PartDto;
import org.softuni.carDealer.domain.dtos.view.PartView;
import org.softuni.carDealer.domain.dtos.view.SupplierView;

import java.util.List;

public interface PartService<Part, Long> {
    void save(PartAddDto partAddDto);

    List<PartView> findAllParts();

    PartView findPartById(Long id);

    void editPart(Long id, PartView partView);

    SupplierView findSupplierByPartId(Long id);

    void deletePart(Long id);
}
