package com.example.SpringbootMysql;

import jakarta.persistence.*;
//import lombok.Data;

//JPA annotations
//This is a Lombok annotation that automatically generates boilerplate code for the class, 
//including getters, setters, toString, equals, and hashCode methods.
//@Data
//This JPA annotation marks the class as an entity, representing a table in the database. 
//Each instance of this class represents a row in the database table.
@Entity
@Table(name="employees")

public class Employee {
	
	@Id //Primary key
	// to generate sequence values
	@SequenceGenerator(
			name="employee_id_seq",
			sequenceName = "employee_id_seq",
			allocationSize=1)
	@GeneratedValue(
			strategy=GenerationType.SEQUENCE,
			generator="employee_id_seq") 	
		
	private long ID;	
	private String firstName;
	private String lastName;
	private String email;
	
	public  long getID() {
		return ID;
	}
	public void setID(long ID) {
		this.ID = ID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName=firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName=lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email=email;
	}
    @Override
    public String toString() {
        return "Employee{" +
                "id=" + ID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
