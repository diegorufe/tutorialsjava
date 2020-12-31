package com.various.normalize;

import java.text.Normalizer;

/**
 * Class with utilities for normalize string
 * 
 * @author diego
 *
 */
public final class UtilsNormalize {

	/**
	 * Empty string constant
	 */
	private static final String EMPTY = "";

	/**
	 * String to use replace regex normalize. This expression represents the UTF-8
	 * characters between U+0300 and U+036F
	 */
	private static final String REGEX_REPLACE_NORMALIZE = "[\\p{InCombiningDiacriticalMarks}]";

	private UtilsNormalize() {

	}

	/**
	 * Method for normalize string. Only normalize if text is not null else return null
	 * @param text to normalize
	 * @return text normalized or null if text param is null
	 */
	public static final String normalize(String text) {
		String textNormalized = null;
		if (text != null) {
			textNormalized = Normalizer.normalize(text, Normalizer.Form.NFD);
			textNormalized = textNormalized.replaceAll(REGEX_REPLACE_NORMALIZE, EMPTY);
		}
		return textNormalized;
	}
}
