/**
 * 
 */
package com.wlw.wlsearch.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

//import org.springframework.data.mongodb.core.mapping.Document;
/**
 * @author Karuppu
 * @created on Nov 17, 2016
 * SpringDataElastic
 */
//@Entity
//@Table(name = "employee")
@Document(indexName = "employee_sample", type = "employee")
public class Employee {

	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	@Column(name = "employee_id")
	private int employeeId;

//	@Column(name = "firstName")
	private String firstName;

//	@Column(name = "lastName")
	private String lastName;

//	@Column(name = "language")
	private String language;

//	@Column(name = "department")
	private String department;
	 
	  
	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", language=" + language + ", department=" + department + "]";
	}
	
}

