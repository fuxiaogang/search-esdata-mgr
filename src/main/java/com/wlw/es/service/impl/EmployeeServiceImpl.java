package com.wlw.es.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Service;

import com.wlw.es.entity.Employee;
import com.wlw.es.repository.EmployeeRepository;
import com.wlw.es.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	ElasticsearchTemplate template;

	@Override
	public Employee findById(Long id) {
		return employeeRepository.findOne(id);
	}

	@Override
	public void insert(Employee employee) { 
		employeeRepository.save(employee);   
	}

	@Override
	public void useTemplate() {
		Employee employee = new Employee();
		employee.setEmployeeId(2);
		employee.setLanguage("cn");
		
		IndexQuery indexQuery = new IndexQueryBuilder()
		                .withId(employee.getEmployeeId()+"")
		                .withIndexName("employee_sample").withObject(employee)
		                .withType("employee").build();
		
		template.deleteIndex(Employee.class);
		 
		template.index(indexQuery);
		 

	}
	 
}
