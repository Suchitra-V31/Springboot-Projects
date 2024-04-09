package com.example.SpringbootMysql;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class EmployeeController   {
	
	private final EmployeeRepository employeeRepository;
	
	public EmployeeController(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	@GetMapping("/Employee")
	public List<Employee> getEmployee(){
		return employeeRepository.findAll();
	}
// to print output 
//    public void run(String... args) {
//        System.out.println("Employees:");
//        employeeRepository.findAll().forEach(System.out::println);
//    }
    record NewEmployeeRequest(String firstName,String lastName,String email) {
    	
    }
    @PostMapping("/addEmployee")
    public ResponseEntity<String> addEmployee(@RequestBody NewEmployeeRequest request) {
    	Employee employee=new Employee();
    	employee.setFirstName(request.firstName());
    	employee.setLastName(request.lastName());
    	employee.setEmail(request.email());
    	employeeRepository.save(employee);
    	return new ResponseEntity<>("Employee Added Successfully",HttpStatus.OK);
    }
    
//  @DeleteMapping("/deleteEmployeeByID/{id}")
//  public void deleteEmployeeByID(@PathVariable("ID")long id) {
//  	//Employee empToDel = employeeRepository.findById(id);
//  	employeeRepository.deleteById(id);
//  	
//  }
    
    @DeleteMapping("/deleteEmployee/{FirstName}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("FirstName") String firstName){
    	List<Employee> employee = employeeRepository.findByFirstName(firstName);
    	if(employee.isEmpty()) {
    		return new ResponseEntity<>("Employee Not Found!!!",HttpStatus.NOT_FOUND);
    	}
        for (Employee emp : employee) {
            employeeRepository.delete(emp);
        }
    	return new ResponseEntity<>("Employee Deleted Successfully!!!",HttpStatus.OK);	
    }
    
    @PutMapping("/updateEmployee/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable("id") long id,@RequestBody NewEmployeeRequest updatedEmployee){
    	Optional<Employee> employee = employeeRepository.findById(id);
    	if(employee.isEmpty()) {
    		return new ResponseEntity<>("Employee Not Found!!!",HttpStatus.NOT_FOUND);
    	}
        Employee employee1 = employee.get();
        employee1.setFirstName(updatedEmployee.firstName());
        employee1.setLastName(updatedEmployee.lastName());
        employee1.setEmail(updatedEmployee.email());
        employeeRepository.save(employee1);
        return new ResponseEntity<>("Employee Updated Successfully!!!",HttpStatus.OK);	
    	
    }
}
