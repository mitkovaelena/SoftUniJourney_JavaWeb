package org.softuni.carDealer.controllers;

import org.softuni.carDealer.domain.dtos.binding.add.PartAddDto;
import org.softuni.carDealer.domain.dtos.view.PartView;
import org.softuni.carDealer.domain.dtos.view.SupplierView;
import org.softuni.carDealer.domain.entities.Part;
import org.softuni.carDealer.domain.entities.Supplier;
import org.softuni.carDealer.services.apis.PartService;
import org.softuni.carDealer.services.apis.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/parts")
public class PartsController {

    private final PartService<Part, Long> partService;
    private final SupplierService<Supplier, Long> supplierService;


    @Autowired
    public PartsController(PartService<Part, Long> partService, SupplierService<Supplier, Long> supplierService) {
        this.partService = partService;
        this.supplierService = supplierService;
    }

    @GetMapping("/all")
    public String allParts(Model model) {
        List<PartView> partViews = this.partService.findAllParts();
        model.addAttribute("partViews", partViews);

        return "parts/all";
    }

    @GetMapping("/add")
    public String showAddPart(Model model) {
        List<SupplierView> supplierViews =  this.supplierService.findAllSuppliers();
        model.addAttribute("supplierViews", supplierViews);
        return "parts/add";
    }

    @PostMapping("/add")
    public String addPart(HttpServletRequest request){
        PartAddDto partAddDto = new PartAddDto();
        partAddDto.setName(request.getParameter("name"));
        partAddDto.setPrice(new BigDecimal(request.getParameter("price")));
        partAddDto.setQuantity(Long.parseLong(request.getParameter("quantity")));
        partAddDto.setSupplier(this.supplierService.findSupplierById(Long.parseLong(request.getParameter("supplier"))));
        this.partService.save(partAddDto);
        return "redirect:/parts/all";
    }

    @GetMapping("edit/{id}")
    public String showEditPart(@PathVariable("id") Long id, Model model) {
        PartView partView = this.partService.findPartById(id);
        model.addAttribute("partView", partView);
        SupplierView supplierView = this.partService.findSupplierByPartId(id);
        model.addAttribute("supplierView", supplierView);

        return "parts/edit";
    }

    @PostMapping("edit/{id}")
    public String editPart(@PathVariable("id") Long id, @ModelAttribute PartView partView) {
        this.partService.editPart(id, partView);

        return "redirect:/parts/all";
    }

    @GetMapping("delete/{id}")
    public String showDeletePart(@PathVariable("id") Long id, Model model) {
        PartView partView = this.partService.findPartById(id);
        model.addAttribute("partView", partView);
        SupplierView supplierView = this.partService.findSupplierByPartId(id);
        model.addAttribute("supplierView", supplierView);

        return "parts/delete";
    }

    @PostMapping("delete/{id}")
    public String deletePart(@PathVariable("id") Long id) {
        this.partService.deletePart(id);

        return "redirect:/parts/all";
    }
}
