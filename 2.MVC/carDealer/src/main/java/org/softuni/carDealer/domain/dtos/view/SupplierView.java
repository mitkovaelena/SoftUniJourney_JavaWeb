package org.softuni.carDealer.domain.dtos.view;

import org.softuni.carDealer.domain.dtos.binding.relations.PartDto;

import java.util.Set;

public class SupplierView {
    private Long id;
    private String name;
    private Set<PartView> parts;

    public SupplierView() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPartsCount() {
        return parts.size();
    }

    public Set<PartView> getParts() {
        return this.parts;
    }

    public void setParts(Set<PartView> parts) {
        this.parts = parts;
    }
}
