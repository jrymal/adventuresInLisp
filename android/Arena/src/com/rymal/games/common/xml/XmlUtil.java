package com.rymal.games.common.xml;

public final class XmlUtil {

	private XmlUtil() {}
	
	public static int parseXmlValue(String value, int defaultIfNull) {
		return value == null ? defaultIfNull : Integer.valueOf(value);
	}
}
