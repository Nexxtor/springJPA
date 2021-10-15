package com.naldana.exampleJPA.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.naldana.exampleJPA.models.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
