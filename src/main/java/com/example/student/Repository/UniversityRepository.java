package com.example.student.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.student.model.University;


public interface UniversityRepository extends JpaRepository<University, Long> {
}


