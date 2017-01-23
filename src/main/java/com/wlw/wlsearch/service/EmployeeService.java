package com.wlw.wlsearch.service;

import com.wlw.wlsearch.entity.Employee;

public interface EmployeeService {

	Employee findById(Long id);
	
	void insert(Employee employee);
	
	void useTemplate();
	
}
