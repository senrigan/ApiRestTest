package com.example;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

import com.example.entity.Employee;
import com.example.service.EmployeeServices;
import com.example.service.IEmployeService;
@ServletComponentScan
@SpringBootApplication
public class DemoRestApplication {
	private static final Logger log =org.slf4j.LoggerFactory.getLogger(DemoRestApplication.class);
	
	@Autowired
	private IEmployeService employeeService;
	public static void main(String[] args) {
		SpringApplication.run(DemoRestApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo() {
		return (args)->{
			List<Employee> employeeList =employeeService.createFakeList();
			for (Employee em : employeeList) {
				employeeService.saveEmployee(em);
			}
			for(Employee employe:employeeService.getListEmployee()) {
				log.info("The Employeee is :"+employe.toString());
			}
		};
	}


}
