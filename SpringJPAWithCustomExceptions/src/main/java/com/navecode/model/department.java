package com.navecode.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "department5")
public class department {
	@Id
	int dep_id;
	String dep_address;

//	@OneToMany(mappedBy = "dep")
//	@JsonIgnore
//	private List<modelclass> emp;
}
