package com.wlw.es.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.wlw.es.entity.Employee;

public interface EmployeeRepository extends  ElasticsearchRepository<Employee, Long>{

	
}
