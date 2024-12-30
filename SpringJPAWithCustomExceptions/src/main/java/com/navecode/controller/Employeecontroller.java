package com.navecode.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.navecode.DTO.EmployeeDTO;
import com.navecode.Service.EmployeeService;
import com.navecode.model.Employee;

@RestController
public class Employeecontroller {

	@Autowired
	EmployeeService empservice;

	@GetMapping("getEmployee/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {
		Employee emp = empservice.getEmpById(id);
		return new ResponseEntity<>(emp, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<List<EmployeeDTO>> getEmployeeWithDep(@PathVariable int id) {
		List<EmployeeDTO> li = empservice.getEmpWithDep(id);
		return new ResponseEntity<>(li, HttpStatus.OK);
	}

	@PostMapping("AddEmployee/{id}")
	public ResponseEntity<?> postEmployeeById(@PathVariable int id, @RequestBody Employee li) {
		Employee emp = empservice.postnewEmpById(id, li);
		return new ResponseEntity<>(emp, HttpStatus.CREATED);
	}

	@GetMapping("getAllEmployees")
	public ResponseEntity<?> getEmployees() {
		System.out.println(Thread.currentThread().getName());
		empservice.AsyncMet();
		List<Employee> emp = empservice.getAllEmployees();
		return new ResponseEntity<>(emp, HttpStatus.OK);
	}

	@PostMapping("AddEmployees/{id}")
	public ResponseEntity<?> postEmployees(@RequestBody List<Employee> li) {

		List<Employee> emp = empservice.postAllEmp(li);
		return new ResponseEntity<>(emp, HttpStatus.CREATED);

	}

	@DeleteMapping("DeleteEmployee/{id}")
	public ResponseEntity<?> deleteEmployeeById(@PathVariable int id) {
		String s = empservice.deleteEmpById(id);
		return new ResponseEntity<>(s, HttpStatus.OK);
	}

	@PutMapping("UpdateEmployee/{id}")
	public ResponseEntity<?> updateEmployeeById(@PathVariable int id, @RequestBody Employee li) {
		Employee emp = empservice.updateEmpById(id, li);
		return new ResponseEntity<>(emp, HttpStatus.OK);
	}

	@GetMapping("getAllEmployeesAsyn")
	public CompletableFuture<ResponseEntity<List<Employee>>> getEmployeeswithAysnc() {

		CompletableFuture<List<Employee>> emp1 = empservice.getAllEmployeesWithAsync();
		CompletableFuture<List<Employee>> emp3 = empservice.getAllEmployeesWithAsync();
		CompletableFuture<List<Employee>> emp4 = empservice.getAllEmployeesWithAsync();
		return CompletableFuture.allOf(emp1, emp3, emp4).thenApply(voidResult -> {
			try {
				List<Employee> allEmployees = new ArrayList<>();
				allEmployees.addAll(emp1.get());
				allEmployees.addAll(emp3.get());
				allEmployees.addAll(emp4.get());
				return new ResponseEntity<>(allEmployees, HttpStatus.OK);
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		});

	}

	@GetMapping("getAllEmployeesSingleThread")
	public CompletableFuture<ResponseEntity<List<Employee>>> getEmp() {
		return empservice.getAllEmployeesWithAsync()
				.thenApply(employees -> new ResponseEntity<>(employees, HttpStatus.OK));
	}

	@GetMapping("getRequestParam")
	public List<String> requestParamMethod(@RequestParam(name = "values") List<String> list) {
		return list;
	}
}
