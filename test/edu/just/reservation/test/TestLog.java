package edu.just.reservation.test;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;



public class TestLog {

	@Test
	public void testLog(){
		Log log = LogFactory.getLog(getClass());
		log.debug("debug");
		log.info("info");
		log.warn("warn");
		log.error("error");
		log.fatal("fatal");
	}
	
}
