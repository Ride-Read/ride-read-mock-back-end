/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/
package qi.yue.utils;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.UUID;

public class CommonUtil {
	public static final String EMPTY_STRING = "";
	public static final String DEFAULT_DELIMITER = ",";
	public static final String DEFAULT_DATE_PATTERN = "dd/MM/yyyy";
	public static final String DEFAULT_NUMBER_PATTERN = "#,##0.00";
	public static final String DEFAULT_LOCALE = "en_HK";
	public static final String LIKE_SYMBOL = "%";
	public static final double UNIT = 1024.0D;
	private static final String LIKE_PATTERN = "%s%s%s";

	public static String convertEmptyToNull(String s) {
		return isNullOrEmpty(s) ? null : s;
	}

	public static String convertNullToEmpty(Object o) {
		return o == null ? "" : o.toString();
	}

	public static String convertNullToEmpty(String s) {
		return isNullOrEmpty(s) ? "" : s;
	}

	public static boolean contains(int key, int[] itemList) {
		if (itemList == null) {
			return false;
		} else {
			int[] arg1 = itemList;
			int arg2 = itemList.length;

			for (int arg3 = 0; arg3 < arg2; ++arg3) {
				int i = arg1[arg3];
				if (key == i) {
					return true;
				}
			}

			return false;
		}
	}

	public static boolean isNull(Short s) {
		return s == null;
	}

	public static boolean isNullOrEmpty(String s) {
		return s == null || s.trim().isEmpty();
	}

	public static boolean isNullOrEmpty(Object o) {
		return o instanceof String ? isNullOrEmpty((String) o)
				: (o instanceof Collection ? isNullOrEmpty((Collection) o)
						: (o instanceof Map ? isNullOrEmpty((Map) o) : isNull(o)));
	}

	public static boolean isNull(Object o) {
		return o == null;
	}

	public static <T> boolean isNullOrEmpty(Collection<T> c) {
		return c == null || c.size() == 0;
	}

	public static <K, V> boolean isNullOrEmpty(Map<K, V> m) {
		return m == null || m.isEmpty();
	}

	public static boolean toBoolean(String value) {
		return isNullOrEmpty(value) ? false
				: value.equalsIgnoreCase("1") || value.equalsIgnoreCase("Yes") || value.equalsIgnoreCase("Y")
						|| value.equalsIgnoreCase("true");
	}

	public static String generateID() {
		return UUID.randomUUID().toString();
	}

	public static String toXMLString(Object obj) {
		if (isNull(obj)) {
			return "";
		} else {
			ByteArrayOutputStream baos = null;
			XMLEncoder encoder = null;
			String retStr = null;

			try {
				baos = new ByteArrayOutputStream();
				encoder = new XMLEncoder(baos);
				encoder.writeObject(obj);
				encoder.close();
				retStr = baos.toString("UTF-8");
			} catch (UnsupportedEncodingException arg12) {
				arg12.printStackTrace();
			} finally {
				try {
					if (baos != null) {
						baos.close();
						baos = null;
					}
				} catch (IOException arg11) {
					;
				}

			}

			return retStr;
		}
	}

	public static Object toXMLObject(String xmlStr) {
		if (isNull((Object) xmlStr)) {
			return null;
		} else {
			ByteArrayInputStream bais = null;
			XMLDecoder decoder = null;
			Object obj = null;

			try {
				bais = new ByteArrayInputStream(xmlStr.getBytes("UTF-8"));
				decoder = new XMLDecoder(bais);
				obj = decoder.readObject();
				decoder.close();
			} catch (UnsupportedEncodingException arg12) {
				arg12.printStackTrace();
			} finally {
				try {
					if (bais != null) {
						bais.close();
						bais = null;
					}
				} catch (IOException arg11) {
					;
				}

			}

			return obj;
		}
	}

	public static String trim(String s) {
		return trimLeft(trimRight(s));
	}

	public static String trimRight(String s) {
		if (s == null) {
			return "";
		} else if (!hasLength(s.trim())) {
			return "";
		} else {
			StringBuffer buf = new StringBuffer(s);
			int length = buf.length();

			while (Character.isWhitespace(buf.charAt(length - 1))) {
				--length;
				buf.setLength(length);
			}

			return buf.toString();
		}
	}

	public static String trimLeft(String source) {
		if (isNullOrEmpty(source)) {
			return "";
		} else if (!hasLength(source.trim())) {
			return source;
		} else if (source.trim().length() == 0) {
			return "";
		} else {
			int index = 0;

			for (int i = 0; i < source.length() && Character.isWhitespace(source.charAt(i)); ++i) {
				index = i + 1;
			}

			return index != 0 ? source.substring(index) : source;
		}
	}

	public static boolean hasLength(String str) {
		return str != null && str.length() > 0;
	}

	public static boolean isBypassRule(List<String> bypassList, String ruleCode) {
		Iterator arg1 = bypassList.iterator();

		String bypass;
		do {
			if (!arg1.hasNext()) {
				return false;
			}

			bypass = (String) arg1.next();
		} while (!trim(bypass).equals(trim(ruleCode)));

		return true;
	}

	public static List<String> split(String input, String delimiter) {
		if (!isNullOrEmpty(input) && !isNullOrEmpty(delimiter)) {
			ArrayList mList = new ArrayList();
			StringTokenizer st = new StringTokenizer(input, delimiter);

			while (st.hasMoreTokens()) {
				String str = st.nextToken();
				mList.add(trim(str));
			}

			return mList;
		} else {
			return null;
		}
	}

	public static List<String> splitWithEmptyToken(String input, String delimiter) {
		if (!isNullOrEmpty(input) && delimiter != null && delimiter.length() > 0) {
			String[] tokens = input.split(delimiter, -1);
			return Arrays.asList(tokens);
		} else {
			return null;
		}
	}

	public static String toLikeString(String str) {
		return isNullOrEmpty(str) ? null : String.format("%s%s%s", new Object[] { "%", str, "%" });
	}

	public static int count(String originalString, String findString) {
		int lastIndex = 0;
		int count = 0;

		while (lastIndex != -1) {
			lastIndex = originalString.indexOf(findString, lastIndex);
			if (lastIndex != -1) {
				++count;
				lastIndex += findString.length();
			}
		}

		return count;
	}

	public static String getDisplayFileSize(long bytes) {
		if ((double) bytes < 1024.0D) {
			return bytes + " B";
		} else {
			int exp = (int) (Math.log((double) bytes) / Math.log(1024.0D));
			char pre = "KMGTPE".charAt(exp - 1);
			return String.format("%.1f %sB", new Object[] {
					Double.valueOf((double) bytes / Math.pow(1024.0D, (double) exp)), Character.valueOf(pre) });
		}
	}
}