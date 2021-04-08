package com.example.web;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.entity.Employee;
import com.example.repository.EmployeeRepository;
import com.example.service.IEmployeService;

@RestController
@RequestMapping("/v1/employes")
public class EmployeeController {
	@Autowired
	private EmployeeRepository repository;
	
	private IEmployeService employeService;
	
	
	public EmployeeController(EmployeeRepository repository) {
		this.repository=repository;
	}
	
	@Autowired
	public void setEmployeeServices(IEmployeService employeServices) {
		this.employeService=employeServices;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeByID(@PathVariable Long id) {
		Optional<Employee> idEmploye = employeService.findById(id);
		if(idEmploye.isPresent()) {
			return new  ResponseEntity<Employee>(idEmploye.get(),HttpStatus.OK);
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Employe id Not Found");
		}
	}
	
	@GetMapping("")
	public ResponseEntity<List<Employee>> getEmployeByID() {
		List<Employee> allEmployeeActive = employeService.getListEmployee();
		if(!allEmployeeActive.isEmpty()) {
			List<Employee> collect = allEmployeeActive.stream().collect(Collectors.toList());
			return new  ResponseEntity<List<Employee>>(collect,HttpStatus.OK);
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"the Employee List is empty");
		}
	}
	
	@PostMapping("")
	public ResponseEntity<?> setNewEmployee(@RequestBody Employee newEmployee) {
		Optional<Employee> findById = employeService.findById(newEmployee.getId());
		if(findById.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"the employe id Exist");
		}else {
			employeService.saveEmployee(newEmployee);
			return  new  ResponseEntity<Employee>(newEmployee,HttpStatus.ACCEPTED);
		}
	}
	@PutMapping("")
	public ResponseEntity<?> updateEmployee(@RequestBody Employee employee) {
		Optional<Employee> findById = employeService.findById(employee.getId());
		if(findById.isPresent()) {
			employeService.saveEmployee(employee);
			return  new  ResponseEntity<Employee>(employee,HttpStatus.ACCEPTED);
		}else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"the employe data is not correct");
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?>  updateEmployee(@PathVariable Long id) {
		int desactivedEmployee = repository.desactivedEmployee(id);
		if(desactivedEmployee==1) {
			return  new  ResponseEntity<>(null,HttpStatus.ACCEPTED);
		}else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"cannot eliminate the employee with id "+id);
		}
		 
	}
	
	/*
	@PutMapping("")
	public ResponseEntity<List<Employee>> updateEmployee(@RequestBody Employee employee) {
		Collection<Employee> updateEmployee = repository.updateEmployee(employee.getId(),
				employee.getFirstName(), employee.getMiddleInitial(),
				employee.getLastName(), employee.getDateOfBirth(),
				employee.getDateOfEmployment(), employee.isStatus());
		List<Employee> collect = updateEmployee.stream().collect(Collectors.toList());
		return new  ResponseEntity<List<Employee>>(collect,HttpStatus.OK);
		 
	}
	
	
	*/
	
}
