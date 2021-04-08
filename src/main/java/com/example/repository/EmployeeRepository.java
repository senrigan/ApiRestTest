package com.example.repository;

import java.util.Collection;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.entity.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long>{

	@Query(value="SELECT * FROM Employee e WHERE e.Status = 1", nativeQuery= true)
	Collection<Employee> finAllEmployeeActive();
	@Query(value="SELECT * FROM Employee e WHERE e.Status = 1 and e.id= :id",nativeQuery=true)
	Optional<Employee> findByIdActive(@Param(value="id") long id);
	
	@Modifying
	@Query(value="UPDATE Employee e set e.Status = 0 where e.id = :id and e.status = 1",nativeQuery = true)
	@Transactional
	int desactivedEmployee(@Param("id")long id);

}
