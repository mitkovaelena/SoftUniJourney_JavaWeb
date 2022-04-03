package org.softuni.residentEvil.services;

import org.softuni.residentEvil.domain.entities.Capital;
import org.softuni.residentEvil.domain.entities.Virus;
import org.softuni.residentEvil.domain.models.binding.AddVirusDTO;
import org.softuni.residentEvil.domain.models.view.VirusViewModel;
import org.softuni.residentEvil.repositorities.CapitalRepository;
import org.softuni.residentEvil.repositorities.VirusRepository;
import org.softuni.residentEvil.utils.ModelParser;
import org.softuni.residentEvil.utils.enums.MagnitudeEnum;
import org.softuni.residentEvil.utils.enums.MutationEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class VirusServiceImpl implements VirusService{
    private final VirusRepository virusRepository;
    private final CapitalRepository capitalRepository;

    @Autowired
    public VirusServiceImpl(VirusRepository virusRepository, CapitalRepository capitalRepository) {
        this.virusRepository = virusRepository;
        this.capitalRepository = capitalRepository;
    }

    @Override
    public void saveVirus(AddVirusDTO addVirusDTO) {
        Virus virus = ModelParser.getInstance().map(addVirusDTO, Virus.class);
        virus.setMagnitude(MagnitudeEnum.valueOf(addVirusDTO.getMagnitude().toUpperCase()));
        virus.setMutation(MutationEnum.valueOf(addVirusDTO.getMutation()));
        virus.setIsCurable(addVirusDTO.getIsCurable() != null);
        virus.setIsDeadly(addVirusDTO.getIsDeadly() != null);
        Set<Capital> capitals = new HashSet<>();
        if(addVirusDTO.getCapitals() != null) {
            for (String capitalName : addVirusDTO.getCapitals()) {
                Capital capital = this.capitalRepository.findCapitalByName(capitalName);
                if (capital != null) {
                    capitals.add(capital);
                }
            }
        }
        virus.setCapitals(capitals);

        this.virusRepository.save(virus);
    }

    @Override
    public Set<VirusViewModel> getAllViruses() {
        List<Virus> viruses =  this.virusRepository.findAll();
        Set<VirusViewModel> virusViewModels = new HashSet<>();

        for (Virus virus : viruses) {
            VirusViewModel virusViewModel = ModelParser.getInstance().map(virus, VirusViewModel.class);
            virusViewModel.setMagnitude(virus.getMagnitude().getName());
            virusViewModels.add(virusViewModel);
        }

        return virusViewModels;
    }

    @Override
    public AddVirusDTO getVirusById(Long id) {
        Optional<Virus> virusOptional = this.virusRepository.findById(id);
        AddVirusDTO virusViewModel = null;
        if (virusOptional.isPresent()) {
            virusViewModel = ModelParser.getInstance().map(virusOptional.get(), AddVirusDTO.class);
        }

        return virusViewModel;
    }

    @Override
    public void removeVirus(Long id) {
        this.virusRepository.deleteById(id);
    }

    @Override
    public Date getReleasedOnById(Long id) {
        return this.getVirusById(id).getReleasedOn();
    }
}
