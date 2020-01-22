package cn.glycol.tutils.natives;

/**
 * This class is a collection of parsers. All the methods won't throw exception.
 * Instead, it returns the default value or null.
 * 
 * @author Taskeren
 * @since 1.0
 */
public class TParser {
	private TParser() {}
	
	public static Byte parseByte(String s) {
		return parseByte(s, null);
	}

	public static Byte parseByte(String s, Byte _default) {
		try {
			return Byte.parseByte(s);
		} catch (Exception ex) {
			return _default;
		}
	}

	public static Short parseShort(String s) {
		return parseShort(s, null);
	}

	public static Short parseShort(String s, Short _default) {
		try {
			return Short.parseShort(s);
		} catch (Exception ex) {
			return _default;
		}
	}

	public static Integer parseInteger(String s) {
		return parseInteger(s, null);
	}

	public static Integer parseInteger(String s, Integer _default) {
		try {
			return Integer.parseInt(s);
		} catch (Exception ex) {
			return _default;
		}
	}

	public static Long parseLong(String s) {
		return parseLong(s, null);
	}

	public static Long parseLong(String s, Long _default) {
		try {
			return Long.parseLong(s);
		} catch (Exception ex) {
			return _default;
		}
	}

	public static Float parseFloat(String s) {
		return parseFloat(s, null);
	}

	public static Float parseFloat(String s, Float _default) {
		try {
			return Float.parseFloat(s);
		} catch (Exception ex) {
			return _default;
		}
	}

	public static Double parseDouble(String s) {
		return parseDouble(s, null);
	}

	public static Double parseDouble(String s, Double _default) {
		try {
			return Double.parseDouble(s);
		} catch (Exception ex) {
			return _default;
		}
	}

	public static Boolean parseBoolean(String s) {
		return parseBoolean(s, null);
	}

	public static Boolean parseBoolean(String s, Boolean _default) {
		if (s.toLowerCase().contentEquals("true"))
			return Boolean.TRUE;
		if (s.toLowerCase().contentEquals("false"))
			return Boolean.FALSE;
		return _default;
	}

}
