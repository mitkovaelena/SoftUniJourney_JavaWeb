package org.softuni.residentEvil.repositorities;

import org.softuni.residentEvil.domain.entities.Virus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface VirusRepository extends JpaRepository<Virus, Long>{
}
