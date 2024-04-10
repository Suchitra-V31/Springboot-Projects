package com.project.Student.Management;

import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.persistence.Table;

@Entity
@Table(name="StudentDetails")

public class Student {
	private Long id;
	private String name;
	private LocalDate dob;
	private String motherName;
	private String fatherName;
	private String groupName;
	private int mark;
	private long rollNo;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name= name;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public String getMotherName() {
		return motherName;
	}
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	@Column(name = "group_name")
	public String getGroup() {
		return groupName;
	}
	public void setGroup(String group) {
		this.groupName = group;
	}
	public int getMark() {
		return mark;
	}
	public void setMark(int mark) {
		this.mark = mark;
	}
	public long getRollNo() {
		return rollNo;
	}
	public void setRollNo(long rollNo) {
		this.rollNo = rollNo;
	}
	@Override
	public String toString() {
		return "Student{"
				+"ID : "+ id +  
				"Roll No :" + rollNo +
				"Name : " + name+
				"DOB : "+dob+
				"Mother Name : "+motherName+
				"Father Name : "+fatherName+
				"GroupName : "+groupName+
				"Mark : "+mark;
	}

	

}
