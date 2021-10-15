package com.naldana.exampleJPA.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.naldana.exampleJPA.models.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

}
