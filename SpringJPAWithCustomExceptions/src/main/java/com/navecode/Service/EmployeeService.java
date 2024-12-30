package com.navecode.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.navecode.DTO.EmployeeDTO;
import com.navecode.Repo.EmployeeRepo;
import com.navecode.customExceptions.NoDataFoundException;
import com.navecode.model.Employee;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepo Repo;

	public List<EmployeeDTO> getEmpWithDep(int id) {
		// TODO Auto-generated method stub
		List<Object[]> li = Repo.getEmpbyId(id);
		List<EmployeeDTO> liDTO = li.stream().map(result -> new EmployeeDTO((Integer) result[0], (String) result[1],
				(Integer) result[2], (String) result[3])).collect(Collectors.toList());
		return liDTO;
	}

	@Cacheable(value = "empIdfetch", key = "#id")
	public Employee getEmpById(int id) {
		// TODO Auto-generated method stub
		Optional<Employee> emli = Repo.findById(id);
		if (emli.isPresent()) {
			return emli.get();
		} else {
			throw new NoDataFoundException(601, "Given employee id is not found");
			// return "no employees found";
		}
	}

	public Employee postnewEmpById(int id, Employee li) {
		// TODO Auto-generated method stub
		return Repo.save(li);
	}

	public String deleteEmpById(int id) {
		// TODO Auto-generated method stub
		Optional<Employee> emp = Repo.findById(id);
		if (emp.isPresent()) {
			Repo.delete(emp.get());
			return ("Employee is deleted");
		} else {
			throw new NoDataFoundException(601, "Given employee id is not found");
			// return "no employees found";
		}

	}

	@CachePut(value = "empIdfetch", key = "#li.empid")
	public Employee updateEmpById(int id, Employee li) {
		// TODO Auto-generated method stub
		Optional<Employee> emp = Repo.findById(id);
		if (emp.isPresent()) {
			return Repo.save(li);
		} else {
			throw new NoDataFoundException(601, "Given employee id is not found");
			// return "no employees found";
		}
	}

	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub

		List<Employee> li = Repo.findAll();
		return li;
	}

	@Async("taskExecutor")
	public CompletableFuture<List<Employee>> getAllEmployeesWithAsync() {
		// TODO Auto-generated method stub

		System.out.println(Thread.currentThread().getName());
		List<Employee> li = Repo.findAll();
		return CompletableFuture.completedFuture(li);
	}

	public List<Employee> postAllEmp(List<Employee> li) {
		// TODO Auto-generated method stub
		return Repo.saveAll(li);
	}

	@CacheEvict(value = "empIdfetch", allEntries = true)
	public void clearcatch() {
		System.out.println("Clearing cache");
	}

	@CacheEvict(value = "empIdfetch", key = "#id")
	public void clearcatchbyid(Integer id) {
		System.out.println("clearing cache by id");
	}

	@Async("taskExecutor")
	public CompletableFuture<Void> AsyncMet() {
		try {
			Thread.sleep(2000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// int i = 1 / 0;
		System.out.println("Async" + Thread.currentThread().getName());
		return null;
	}
}
