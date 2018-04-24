package edu.just.reservation.test.service.impl;


import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import edu.just.reservation.test.dao.PersonDao;
import edu.just.reservation.test.entity.Person;
import edu.just.reservation.test.service.TestService;

@Service("testService")
public class TestServiceImpl implements TestService{
	@Resource
	PersonDao personDao;
	public void say() {
		System.out.println("say hello");
	}

	public void save(Person person) {
		personDao.save(person);
	}

	public Person findPersonById(Serializable id) {
		return personDao.findPersonById(id);
	}

}
