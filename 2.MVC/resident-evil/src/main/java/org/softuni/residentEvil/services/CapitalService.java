package org.softuni.residentEvil.services;

import org.softuni.residentEvil.domain.models.view.CapitalViewModel;

import java.util.List;
import java.util.Set;

public interface CapitalService{
    Set<CapitalViewModel> getAllCapitals();
}
