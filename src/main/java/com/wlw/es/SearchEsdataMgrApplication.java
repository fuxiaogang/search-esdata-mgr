package com.wlw.es;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.wlw.es.entity.Employee;
import com.wlw.es.service.EmployeeService;

@SpringBootApplication 
public class SearchEsdataMgrApplication {
  
	static Logger logger = LoggerFactory.getLogger(SearchEsdataMgrApplication.class);  
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SearchEsdataMgrApplication.class, args);
		EmployeeService employeeService = context.getBean("employeeServiceImpl",EmployeeService.class);
	
		employeeService.useTemplate();
		
//		Employee employee = new Employee();
//		employee.setEmployeeId(1);
//        employee.setFirstName("fuxg");
//        employee.setLastName("xiaogang111");
//		
//        employeeService.insert(employee); 
        
//        Employee e1 = employeeService.findById(1l);
//        System.out.println(e1);
	}
}
