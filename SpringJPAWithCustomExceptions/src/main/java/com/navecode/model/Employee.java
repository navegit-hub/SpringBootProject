package com.navecode.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Employees3")
public class Employee {

	String name;
	@Id
	Integer empid;
	String dep_name;
	Integer salary;
	int dep_id;
	Integer manager_id;

//	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "dep_id")
//	private department dep;

}