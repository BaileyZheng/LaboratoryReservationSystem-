package edu.just.reservation.test.service;


import java.io.Serializable;

import edu.just.reservation.test.entity.Person;

public interface TestService {

	//���
	public void say();
	
	public void save(Person person);
	
	public Person findPersonById(Serializable id);
}
