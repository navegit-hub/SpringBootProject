package com.navecode.configDbCache;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.navecode.Service.EmployeeService;
import com.navecode.model.Employee;

import jakarta.annotation.PostConstruct;

@Configuration
@EnableCaching
@EnableScheduling
public class DbCache {

	@Autowired
	CacheManager cacheManager;

	@Autowired
	EmployeeService emp;

	@PostConstruct
	public void run() {
		Cache cache = cacheManager.getCache("empIdfetch");

		List<Employee> li = emp.getAllEmployees();

		for (Employee e : li) {
			cache.put(e.getEmpid(), e);
		}

		System.out.println("Cache initializing");
	}

	@Scheduled(fixedRate = 50000, initialDelay = 30000)
	public void clearcache() {

		System.out.println("Cache cleard");
		cacheManager.getCacheNames().parallelStream().forEach(i -> cacheManager.getCache(i).clear());
	}

}
