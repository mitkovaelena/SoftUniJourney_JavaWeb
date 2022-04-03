package org.softuni.residentEvil.services;

import org.softuni.residentEvil.domain.entities.Capital;
import org.softuni.residentEvil.domain.models.view.CapitalViewModel;
import org.softuni.residentEvil.repositorities.CapitalRepository;
import org.softuni.residentEvil.utils.ModelParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CapitalServiceImpl implements CapitalService{
    private final CapitalRepository capitalRepository;

    @Autowired
    public CapitalServiceImpl(CapitalRepository capitalRepository) {
        this.capitalRepository = capitalRepository;
    }

    @Override
    public Set<CapitalViewModel> getAllCapitals() {
        List<Capital> capitals =  this.capitalRepository.findAll();
        Set<CapitalViewModel> capitalViewModels = new HashSet<>();

        for (Capital capital : capitals) {
            CapitalViewModel capitalViewModel = ModelParser.getInstance().map(capital, CapitalViewModel.class);
            capitalViewModels.add(capitalViewModel);
        }

        return capitalViewModels;
    }
}
