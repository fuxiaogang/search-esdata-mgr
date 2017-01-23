package com.wlw.wlsearch.repository;

import com.wlw.wlsearch.entity.Employee;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EmployeeRepository extends  ElasticsearchRepository<Employee, Long>{

	
}
