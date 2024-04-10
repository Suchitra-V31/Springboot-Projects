package com.project.Student.Management;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.Student.Management.StudentController.NewStudent;

@Service
public class StudentService {
	@Autowired
	private StudentRepository studentRepository;
	
//	public  StudentService(StudentRepository studentRepository) {
//		this.studentRepository = studentRepository;
//	}
	public ResponseEntity<Object> getStudents(){
		try {
			List<Student> students = studentRepository.findAll();
			return JsonObject.generateResponse("Student details fetched Successfully!!!", HttpStatus.OK, students);
		}
		catch(Exception e) {
			return JsonObject.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}

	}
	public ResponseEntity<Object> getStudentByName(String name){
		try {
			List<Student> student = studentRepository.findStudentByName(name);
			return JsonObject.generateResponse("Student details fetched Successfully!!!", HttpStatus.OK, student);
		}
		catch(Exception e) {
			return JsonObject.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
		
	}
	public ResponseEntity<Object> addStudent(NewStudent request) {
		try {
			Student student = new Student();
			student.setName(request.name());
			student.setDob(request.date());
			student.setFatherName(request.fatherName());
			student.setMotherName(request.motherName());
			student.setGroup(request.groupName());
			student.setMark(request.mark());
			student.setRollNo(request.rollNo());
			studentRepository.save(student);
			return JsonObject.generateResponse("Employee added Successfully!!!",HttpStatus.OK,studentRepository.findAll());
		}
		catch(Exception e) {
			return JsonObject.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
	}
	public ResponseEntity<Object> deleteStudent(Long id) {
		try {
			studentRepository.deleteById(id);
			return JsonObject.generateResponse("Employee deleted Successfully!!!",HttpStatus.OK,studentRepository.findAll());
		}
		catch(Exception e) {
			return JsonObject.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
	}
		
	public ResponseEntity<Object> updateStudent(Long id,NewStudent updatestudent) { 
		try {
			Optional<Student> student = studentRepository.findById(id);
			if(student.isPresent()) {
				Student s = student.get();
				s.setName(updatestudent.name());
				s.setDob(updatestudent.date());
				s.setFatherName(updatestudent.fatherName());
				s.setMotherName(updatestudent.motherName());
				s.setGroup(updatestudent.groupName());
				s.setMark(updatestudent.mark());
				s.setRollNo(updatestudent.rollNo());
				studentRepository.save(s);
				return JsonObject.generateResponse("Employee Updated Successfully!!!",HttpStatus.OK,studentRepository.findAll());
			}
			return JsonObject.generateResponse("Employee Not Found!!!",HttpStatus.NOT_FOUND,null);
		}
		catch(Exception e) {
			return JsonObject.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
	}
}

