package com.navecode.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.navecode.model.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

	// @Query(value = "select e.empid as id , e.name as name, d.dep_id as dep_id,
	// d.dep_address as dep_address from Employees3 e join department5 d on
	// e.dep_id=d.dep_id where d.dep_id=:id", nativeQuery = true)

	@Query("select e.empid as empid, e.name as name, e.dep_id as dep_id, d.dep_address as dep_address from Employee e join department d on e.dep_id = d.dep_id where d.dep_id = :id")
	public List<Object[]> getEmpbyId(@Param("id") int id);

}
