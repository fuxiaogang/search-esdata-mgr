package com.wlw.wlsearch.service.impl;

import org.elasticsearch.index.query.TermQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import com.wlw.wlsearch.entity.Employee;
import com.wlw.wlsearch.repository.EmployeeRepository;
import com.wlw.wlsearch.service.EmployeeService;

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

		SearchQuery searchQuery = new NativeSearchQuery(new TermQueryBuilder("",""));


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
