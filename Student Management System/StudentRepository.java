package com.project.Student.Management;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long>{
	List<Student> findStudentByName(String name);
	//Spring Data JPA handles the implementation of this method automatically based on the method name, 
	//so you don't need to provide the query implementation explicitly.

}
