package com.springapp.valgykla.Repository;

import com.springapp.valgykla.Model.Meniu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeniuRepository extends JpaRepository<Meniu, Integer> {
}
