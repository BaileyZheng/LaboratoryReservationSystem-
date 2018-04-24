package edu.just.reservation.test.dao.impl;


import java.io.Serializable;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import edu.just.reservation.test.dao.PersonDao;
import edu.just.reservation.test.entity.Person;

public class PersonDaoImpl extends HibernateDaoSupport implements PersonDao {

	public void save(Person person) {
		getHibernateTemplate().save(person);
	}

	public Person findPersonById(Serializable id) {
		return getHibernateTemplate().get(Person.class, id);
	}

}
