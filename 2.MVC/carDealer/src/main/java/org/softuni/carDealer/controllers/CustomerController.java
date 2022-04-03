package org.softuni.carDealer.controllers;

import org.softuni.carDealer.domain.dtos.binding.add.CustomerAddDto;
import org.softuni.carDealer.domain.dtos.view.CustomerView;
import org.softuni.carDealer.domain.dtos.view.TotalCustomerSalesView;
import org.softuni.carDealer.domain.entities.Customer;
import org.softuni.carDealer.services.apis.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService<Customer, Long> customerService;

    @Autowired
    public CustomerController(CustomerService<Customer, Long> customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/all/{order}")
    public String orderedCustomers(@PathVariable("order") String order, Model model) {
        List<CustomerView> orderedCustomers = this.customerService.findAllOrderedCustomers(order);
        model.addAttribute("orderedCustomers", orderedCustomers);

        return "customers/all";
    }

    @GetMapping("/{id}")
    public String totalSales(@PathVariable("id") Long id, Model model) {
        TotalCustomerSalesView totalCustomerSalesView = this.customerService.totalCustomerSales(id);
        model.addAttribute("totalCustomerSalesView", totalCustomerSalesView);

        return "customers/totalSales";
    }

    @GetMapping("/add")
    public String showAddCustomer() {
        return "customers/add";
    }

    @PostMapping("/add")
    public String addCustomer(@ModelAttribute CustomerAddDto customerToAdd) throws ParseException {
        this.customerService.save(customerToAdd);
        return "redirect:/customers/all/ascending";
    }

    @GetMapping("edit/{id}")
    public String showEditCustomer(@PathVariable("id") Long id, Model model) {
        CustomerView customerView = this.customerService.findCustomerById(id);
        model.addAttribute("customerView", customerView);

        return "customers/edit";
    }

    @PostMapping("edit/{id}")
    public String editCustomer(@PathVariable("id") Long id, @ModelAttribute CustomerAddDto customerToAdd) throws ParseException {
        this.customerService.editCustomer(id, customerToAdd);

        return "redirect:/customers/" + id;
    }
}
