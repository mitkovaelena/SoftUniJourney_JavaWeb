package org.softuni.carDealer.services.apis;

import org.softuni.carDealer.domain.dtos.binding.add.CustomerAddDto;
import org.softuni.carDealer.domain.dtos.binding.relations.CustomerDto;
import org.softuni.carDealer.domain.dtos.view.CustomerView;
import org.softuni.carDealer.domain.dtos.view.TotalCustomerSalesView;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

public interface CustomerService<Customer, Long> {
    void save(CustomerAddDto customerAddDto) throws ParseException;

    List<CustomerView> findAllOrderedCustomers(String order);

    TotalCustomerSalesView totalCustomerSales(Long id);

    CustomerView findCustomerById(Long id);

    void editCustomer(Long id, CustomerAddDto customerToAdd) throws ParseException;
}
