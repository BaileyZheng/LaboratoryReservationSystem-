package edu.just.reservation.test.dao;


import java.io.Serializable;

import edu.just.reservation.test.entity.Person;

public interface PersonDao {
	//�����û�
	public void save(Person person);
	
	//�����û�id��ȡ�û�
	public Person findPersonById(Serializable id);
}
