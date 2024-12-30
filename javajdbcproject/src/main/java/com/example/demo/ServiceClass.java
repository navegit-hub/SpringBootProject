package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public class ServiceClass {

private Repositoryclass repo;

public Repositoryclass getRepo() {
	return repo;
}

public void setRepo(Repositoryclass repo) {
	this.repo = repo;
}

public void test() {
	System.out.println("From service class");
}

}
