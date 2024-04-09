package com.example.SpringbootMysql;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

//This interface provides methods for performing CRUD (Create, Read, Update, Delete) 
//operations on the Employee entity.

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	List<Employee> findByFirstName(String firstName);

}
