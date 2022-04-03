package org.softuni.carDealer.repositories;

import org.softuni.carDealer.domain.dtos.view.SaleView;
import org.softuni.carDealer.domain.dtos.view.TotalCustomerSalesView;
import org.softuni.carDealer.domain.entities.Customer;
import org.softuni.carDealer.domain.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findAllByOrderByBirthDateAscYoungDriverAsc();

    List<Customer> findAllByOrderByBirthDateDescYoungDriverAsc();

    @Modifying
    @Query("update Customer c set c.name = ?1, c.birthDate = ?2 where c.id = ?3")
    void updateCustomer(String name, Date birthDate, Long id);

}
