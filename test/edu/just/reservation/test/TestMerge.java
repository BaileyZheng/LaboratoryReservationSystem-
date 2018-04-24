package edu.just.reservation.test;


import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.just.reservation.test.entity.Person;
import edu.just.reservation.test.service.TestService;

public class TestMerge {
	ClassPathXmlApplicationContext ctx;

	@Before
	public void load() {
		ctx = new ClassPathXmlApplicationContext("bean.xml");
	}

	@Test
	public void testSpring() {
		TestService testService = (TestService) ctx.getBean("testService");
		testService.say();
	}

	@Test
	public void testHibernate() {
		SessionFactory sessionFactory = (SessionFactory) ctx.getBean("sessionFactory");
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(new Person("¹þ¹þ"));
		tx.commit();
		session.close();
		
	}

	@Test
	public void testServiceAndDao() {
		TestService testService = (TestService) ctx.getBean("testService");
		//testService.save(new Person("ÎûÎû"));
		//System.out.println(testService.findPersonById("ff80808158d46d490158d46d4c250000"));
	}
	
	@Test
	public void testTransactionReadOnly() {
		TestService testService = (TestService) ctx.getBean("testService");
		testService.findPersonById("ff80808158d46d490158d46d4c250000");
	}
	
	@Test
	public void testTransactionRollback() {
		TestService testService = (TestService) ctx.getBean("testService");
		testService.save(new Person("ÎûÎû"));
	}
}
