package com.navecode.DTO;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

	Integer empid;
	String name;
	Integer dep_id;
	String dep_address;
}
