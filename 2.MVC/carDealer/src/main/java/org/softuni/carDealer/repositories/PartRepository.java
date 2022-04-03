package org.softuni.carDealer.repositories;

import org.softuni.carDealer.domain.entities.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface PartRepository extends JpaRepository<Part, Long> {
    @Modifying
    @Query("update Part p set p.name = ?1, p.price = ?2 where p.id = ?3")
    void updatePart(String name, BigDecimal price, Long id);
}
