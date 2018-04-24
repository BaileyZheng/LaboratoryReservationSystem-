package edu.just.reservation.test.dao;


import java.io.Serializable;

import edu.just.reservation.test.entity.Person;

public interface PersonDao {
	//保存用户
	public void save(Person person);
	
	//根据用户id获取用户
	public Person findPersonById(Serializable id);
}
