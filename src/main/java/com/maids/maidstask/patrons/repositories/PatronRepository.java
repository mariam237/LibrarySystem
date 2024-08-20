package com.maids.maidstask.patrons.repositories;

import com.maids.maidstask.patrons.models.Patron;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatronRepository extends JpaRepository<Patron, Integer> {
    boolean existsById(Integer id);
    boolean existsByUsername(String name);

    Optional<Patron> findByUsername(String username);


//    Optional<Patron> findByUsername(String username);

}
