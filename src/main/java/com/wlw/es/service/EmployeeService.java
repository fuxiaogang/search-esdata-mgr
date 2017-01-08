package com.wlw.es.service;

import com.wlw.es.entity.Employee;

public interface EmployeeService {

	Employee findById(Long id);
	
	void insert(Employee employee);
	
	void useTemplate();
	
}
