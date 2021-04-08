package com.example.service;

import java.util.List;
import java.util.Optional;

import com.example.entity.Employee;

public interface IEmployeService {
	public List<Employee> getListEmployee();
	
	public void saveEmployee(Employee employee);
	
	public List<Employee> createFakeList();
	
	public Optional<Employee> findById(long id);
}
