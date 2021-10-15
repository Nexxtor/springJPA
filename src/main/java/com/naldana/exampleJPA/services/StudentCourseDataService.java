package com.naldana.exampleJPA.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naldana.exampleJPA.models.Course;
import com.naldana.exampleJPA.models.Student;
import com.naldana.exampleJPA.repositories.CourseRepository;
import com.naldana.exampleJPA.repositories.StudentRepository;

@Service
@Transactional
public class StudentCourseDataService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	public void saveStudent(Student student) {
		studentRepository.save(student);
	}
	
	public void saveCourse(Course course) {
		courseRepository.save(course);
	}
	
	public void conectStudentAndCourse(Student student, Course course) {
		student.addCourse(course);
		//course.addStudent(student);
		studentRepository.save(student);
	}
	
}
