package com.various.log;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class for test log dependecies
 * 
 * @author diego
 *
 */
public class TestLog {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestLog.class.getSimpleName());

	@Test
	public void testLog() {
		LOGGER.info("This is a test for show log with log4j2");
	}
}
