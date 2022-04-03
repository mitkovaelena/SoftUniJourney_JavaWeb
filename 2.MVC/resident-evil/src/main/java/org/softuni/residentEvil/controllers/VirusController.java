package org.softuni.residentEvil.controllers;

import org.softuni.residentEvil.domain.models.binding.AddVirusDTO;
import org.softuni.residentEvil.domain.models.binding.EditVirusDTO;
import org.softuni.residentEvil.services.CapitalService;
import org.softuni.residentEvil.services.VirusService;
import org.softuni.residentEvil.utils.ModelParser;
import org.softuni.residentEvil.utils.enums.MagnitudeEnum;
import org.softuni.residentEvil.utils.enums.MutationEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("/viruses")
public class VirusController {
    private final VirusService virusService;
    private final CapitalService capitalService;

    @Autowired
    public VirusController(VirusService virusService, CapitalService capitalService) {
        this.virusService = virusService;
        this.capitalService = capitalService;
    }

    @GetMapping("/add")
    public ModelAndView addVirus(@ModelAttribute("virusModel") AddVirusDTO virusModel, ModelAndView modelAndView) {
        modelAndView.setViewName("add-virus");
        modelAndView.addObject("virusModel", virusModel);
        modelAndView.addObject("capitals", this.capitalService.getAllCapitals());
        modelAndView.addObject("mutations", Stream.of(MutationEnum.values()).map(Enum::name).collect(Collectors.toList()));
        modelAndView.addObject("magnitudes", Stream.of(MagnitudeEnum.values()).map(MagnitudeEnum::getName).collect(Collectors.toList()));

        return modelAndView;
    }

    @PostMapping("/add")
    public String addVirus(@Valid @ModelAttribute("virusModel") AddVirusDTO virusModel, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("capitals", this.capitalService.getAllCapitals());
            model.addAttribute("mutations", Stream.of(MutationEnum.values()).map(Enum::name).collect(Collectors.toList()));
            model.addAttribute("magnitudes", Stream.of(MagnitudeEnum.values()).map(MagnitudeEnum::getName).collect(Collectors.toList()));
            return "add-virus";
        }
        this.virusService.saveVirus(virusModel);
        return "redirect:/viruses/show";
    }


    @GetMapping("/show")
    public ModelAndView allViruses(ModelAndView modelAndView) {
        modelAndView.setViewName("all-viruses");
        modelAndView.addObject("viruses", this.virusService.getAllViruses());

        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editVirus(@PathVariable("id") Long id, ModelAndView modelAndView) {
        AddVirusDTO addVirusDTO =this.virusService.getVirusById(id);
        EditVirusDTO virusModel = ModelParser.getInstance().map(addVirusDTO, EditVirusDTO.class);
        modelAndView.setViewName("edit-virus");
        modelAndView.addObject("virusModel", virusModel);
        modelAndView.addObject("releasedOn", addVirusDTO.getReleasedOn());
        modelAndView.addObject("capitals", this.capitalService.getAllCapitals());
        modelAndView.addObject("mutations", Stream.of(MutationEnum.values()).map(Enum::name).collect(Collectors.toList()));
        modelAndView.addObject("magnitudes", Stream.of(MagnitudeEnum.values()).map(MagnitudeEnum::getName).collect(Collectors.toList()));

        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public String editVirus(@PathVariable("id") Long id, @Valid @ModelAttribute("virusModel") EditVirusDTO virusModel, BindingResult bindingResult, ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("capitals", this.capitalService.getAllCapitals());
            modelAndView.addObject("mutations", Stream.of(MutationEnum.values()).map(Enum::name).collect(Collectors.toList()));
            modelAndView.addObject("magnitudes", Stream.of(MagnitudeEnum.values()).map(MagnitudeEnum::getName).collect(Collectors.toList()));
            return "edit-virus";
        }
        AddVirusDTO addVirusDTO = ModelParser.getInstance().map(virusModel, AddVirusDTO.class);
        addVirusDTO.setReleasedOn(this.virusService.getReleasedOnById(id));
        this.virusService.saveVirus(addVirusDTO);
        return "redirect:/viruses/show";
    }


    @GetMapping("/delete/{id}")
    public ModelAndView deleteVirus(@PathVariable("id") Long id, ModelAndView modelAndView) {
        AddVirusDTO virusModel = this.virusService.getVirusById(id);
        modelAndView.setViewName("delete-virus");
        modelAndView.addObject("virusModel", virusModel);

        return modelAndView;
    }

    @PostMapping("/delete/{id}")
    public String deleteVirus(@PathVariable("id") Long id) {

        this.virusService.removeVirus(id);
        return "redirect:/viruses/show";
    }

}
