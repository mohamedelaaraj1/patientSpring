package com.example.hospitalenset.repository;

import com.example.hospitalenset.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PatientRepository extends JpaRepository <Patient, Long> {
    //Methode 1
    Page<Patient> findByNomContaining(String keyword, Pageable pageable);
    //Methode 2
    @Query("select p from Patient p where p.nom like :x")
    Page<Patient> searchByNomContaining(@Param("x") String keyword, Pageable pageable);


}
