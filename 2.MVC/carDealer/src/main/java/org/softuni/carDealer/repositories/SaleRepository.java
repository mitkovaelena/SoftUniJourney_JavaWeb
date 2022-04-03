package org.softuni.carDealer.repositories;

import org.softuni.carDealer.domain.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
    List<Sale> findAllByOrderByDiscountAsc();

    List<Sale> findAllByDiscountGreaterThanOrderByDiscountAsc(double loBound);

    List<Sale> findAllByDiscountEqualsOrderByDiscountAsc(double percent);
}
