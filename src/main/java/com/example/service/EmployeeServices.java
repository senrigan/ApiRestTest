package com.example.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Employee;
import com.example.repository.EmployeeRepository;

@Service
public class EmployeeServices  implements IEmployeService{

		@Autowired
		private EmployeeRepository repository;

		public List<Employee> getListEmployee() {
			Collection<Employee> allEmployeeActive = repository.finAllEmployeeActive();
			List<Employee> collect = allEmployeeActive.stream().collect(Collectors.toList());
			return collect;
			
		}

		@Override
		public void saveEmployee(Employee employee) {
			repository.save(employee);
		}

		@Override
		public List<Employee> createFakeList() {
			Calendar cal = Calendar.getInstance();
			cal.set(1992, 04, 11);
			Date dB = cal.getTime();
			ArrayList<Employee> employes=new ArrayList<Employee>();
			cal.set(2019, 07, 11);
			Date dE=cal.getTime();
			employes.add(new Employee(1L,"gilberto","C","cordero",dB,dE,true));
			employes.add(new Employee(2L,"sury","m","herrera",dB,dE,true));
			employes.add(new Employee(3L,"miguel","j","contreras",dB,dE,true));
			employes.add(new Employee(4L,"alberto","k","jhonson",dB,dE,true));
			employes.add(new Employee(5L,"edgar","z","villanueva",dB,dE,true));
			employes.add(new Employee(6L,"diego","G","gonzales",dB,dE,true));
			employes.add(new Employee(7L,"jose","l","chaves",dB,dE,true));
			employes.add(new Employee(8L,"alejandara","R","cervantes",dB,dE,true));
			employes.add(new Employee(9L,"blanca","I","manchado",dB,dE,true));
			employes.add(new Employee(10L,"Rogruido","H","gonzales",dB,dE,true));
			employes.add(new Employee(11L,"voldermot","H","gonzales",dB,dE,false));
			employes.add(new Employee(12L,"wazon","H","gonzales",dB,dE,false));


			return employes;
				
			
		}

		@Override
		public Optional<Employee> findById(long id) {
			Optional<Employee> employee = repository.findByIdActive(id);
			return employee;
			
		}
		
		
		public void deleteEmployee(long idEmploye) {
			Optional<Employee> employe = findById(idEmploye);
			if(employe.isPresent()) {
				Employee employee = employe.get();
				employee.setStatus(false);
				repository.save(employee);
			}
		}
		
}
