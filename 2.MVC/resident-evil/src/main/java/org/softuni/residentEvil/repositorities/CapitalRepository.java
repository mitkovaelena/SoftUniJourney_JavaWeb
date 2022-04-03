package org.softuni.residentEvil.repositorities;

import org.softuni.residentEvil.domain.entities.Capital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CapitalRepository extends JpaRepository<Capital, Long>{
    Capital findCapitalByName(String name);
}
