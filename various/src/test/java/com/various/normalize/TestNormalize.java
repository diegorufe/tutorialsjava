package com.various.normalize;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class for realize test normalize string
 * 
 * @author diego
 *
 */
public class TestNormalize {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestNormalize.class.getSimpleName());

	@Test
	public void testNormalize() {

		String textToNormalize = "αινσϊ";
		String desireResult = "aeiou";

		String textNormalize = UtilsNormalize.normalize(textToNormalize);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Text normalize " + textNormalize + ", origin " + textToNormalize + " , desire result "
					+ desireResult);
		}

		assertEquals(textNormalize, desireResult);
	}
}
