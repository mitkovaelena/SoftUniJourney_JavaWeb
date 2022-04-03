package org.softuni.residentEvil.services;

import org.softuni.residentEvil.domain.models.binding.AddVirusDTO;
import org.softuni.residentEvil.domain.models.view.VirusViewModel;

import java.util.Date;
import java.util.Set;

public interface VirusService {
    void saveVirus(AddVirusDTO addVirusDTO);

    Set<VirusViewModel> getAllViruses();

    AddVirusDTO getVirusById(Long id);

    void removeVirus(Long id);

    Date getReleasedOnById(Long id);
}
