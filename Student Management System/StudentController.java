package com.project.Student.Management;


import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class StudentController {
	
	record NewStudent(String name,
			  LocalDate date,
			  String motherName,
			  String fatherName,
			  String groupName,
			  int mark,
			  long rollNo) {

}
	@Autowired
	private StudentService studentService;
	
	
	// returns the list of students
	@GetMapping("/getStudents")
	public ResponseEntity<Object> getStudents(){
		return studentService.getStudents();
	}
	
	// return all the details of the particular student
	@GetMapping("/getStudentByName")
	public ResponseEntity<Object> getStudentByName(@RequestParam(name="name") String name){
		return studentService.getStudentByName(name);
	}
	// to add new Student
	@PostMapping("/addStudent")
	public ResponseEntity<Object> addStudent(@RequestBody NewStudent request ) {
		return studentService.addStudent(request);	 	
	}
	
	// to delete student
	@DeleteMapping("/deleteStudent/{id}")
	public ResponseEntity<Object> deleteStudent(@PathVariable("id") Long id) {
		return studentService.deleteStudent(id);
		
	}
	// to update a student
	@PutMapping("/updateStudent/{id}")
	public ResponseEntity<Object> updateStudent(@PathVariable("id") Long id , @RequestBody NewStudent updatestudent) {
		return studentService.updateStudent(id,updatestudent);
	}

}
