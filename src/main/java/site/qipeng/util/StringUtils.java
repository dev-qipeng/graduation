package site.qipeng.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StringUtils {

	public static boolean isEmpty(String str) {
		if (str == null || str.isEmpty()) {
			return true;
		}
		str = str.trim();
		if (str.isEmpty()) {
			return true;
		}
		if (org.apache.commons.lang.StringUtils.isBlank(str) 
				|| "null".equalsIgnoreCase(str)
				|| "(null)".equalsIgnoreCase(str)) {
			return true;
		}
		if ("undefined".equals(str)) {
			return true;
		}
		return false;
	}

	// 混淆前面的字符串
	public static String blurStartString(String str, int keepLen) {
		if (str == null || str.isEmpty()) {
			return str;
		}
		if (str.length() <= keepLen) {
			return str;
		}
		String tail = str.substring(str.length() - keepLen);
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < str.length() - keepLen; i++) {
			b.append("*");
		}
		b.append(tail);

		return b.toString();
	}

	public static String truncate(String str, int keepLen) {
		if (str == null || str.isEmpty()) {
			return str;
		}
		if (str.length() <= keepLen) {
			return str;
		}
		return str.substring(0, keepLen) + "...";
	}
	
	public static String truncateWithoutDot(String str, int keepLen) {
		if (str == null || str.isEmpty()) {
			return str;
		}
		if (str.length() <= keepLen) {
			return str;
		}
		return str.substring(0, keepLen);
	}

	public static boolean isEqual(String a, String b) {
		if (a == null && b == null) {
			return true;
		} else if (a == null || b == null) {
			return false;
		} else {
			return a.equals(b);
		}

	}

	public static String getFirstLine(String str) {
		if (isEmpty(str)) {
			return str;
		}
		int sep = str.indexOf("\n");
		if (sep > 0) {
			return str.substring(0, sep);
		}
		return str;
	}

	public static boolean toBoolean(String str, boolean defaultValue){
		if(StringUtils.isEmpty(str)){
			return defaultValue;
		}
		if(str.equals("1")||str.equalsIgnoreCase("true")){
			return true;
		}
		return false;
	}
	
	public static String lastPart(String url) {
		if (StringUtils.isEmpty(url)) {
			return url;
		}
		int index = url.lastIndexOf("/");
		if (index >= 0 && url.length() > (index + 1)) {
			return url.substring(index + 1);
		}
		return url;
	}

	public static String deNull(String str) {
		if (str == null||str.equals("null")) {
			return "";
		} else {
			return str;
		}
	}

	public static boolean isNumber(String str) {
		try {
			Long.parseLong(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static List<Integer> strList2IntList(List<String> strList) {
		if (strList == null || strList.size() == 0) {
			return new ArrayList<Integer>(0);
		}
		List<Integer> intList = new ArrayList<Integer>(strList.size());
		for (String str : strList) {
			intList.add(Integer.parseInt(str));
		}
		return intList;
	}

	public static String concatBy(Collection<String> list, String sep) {
		StringBuilder builder = new StringBuilder();
		if (list == null || list.isEmpty()) {
			return "";
		}
		for (String item : list) {
			builder.append(item + sep);
		}
		return builder.substring(0, builder.length() - 1);
	}

	public static String concatByComma(Collection<String> list) {
		return concatBy(list, ",");
	}

	public static String concatByDollar(List<String> list) {
		return concatBy(list, "$");
	}

	public static List<String> splitByComma(String str) {
		return splitBy(str, ",");
	}

	public static String[] splitBySize(String str, int maxSize) {
		if (str.length() <= maxSize) {
			return new String[] { str };
		}
		int arrSize = str.length() / maxSize;
		if (str.length() % maxSize != 0) {
			arrSize++;
		}
		String[] arr = new String[arrSize];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = str.substring(i * maxSize, Math.min(str.length(), i * maxSize + maxSize));
		}
		return arr;
	}

	public static List<Integer> split2IntList(String str,String sep) {
		List<Integer> list = new ArrayList<Integer>();
		if (StringUtils.isEmpty(str)) {
			return list;
		}
		String[] arr = str.split(sep);
		for (String s : arr) {
			s = s.trim();
			if (!StringUtils.isEmpty(s)) {
				list.add(new Integer(s));
			}
		}
		return list;
	}
	
	public static String contact(String[] strs) {
		StringBuilder sb = new StringBuilder();
		for (String str : strs) {
			sb.append(str);
		}
		return sb.toString();
	}

	public static Set<String> splitUniqueByComma(String str) {
		if (str == null || str.isEmpty()) {
			return null;
		}
		List<String> list = splitBy(str, ",");
		Set<String> set = new HashSet<String>(list);
		return set;
	}

	public static List<String> splitBy(String str, String sep) {
		List<String> list = new ArrayList<String>();
		if (StringUtils.isEmpty(str)) {
			return list;
		}
		String[] arr = str.split(sep);

		for (String s : arr) {
			s = s.trim();
			if (!StringUtils.isEmpty(s)) {
				list.add(s);
			}
		}
		return list;
	}

	public static String listToString(List<String> strList, String separator) {
		String str = "";
		boolean flag = false;
		for (String s : strList) {
			if (flag) {
				str += separator;
			} else {
				flag = true;
			}
			str += s;
		}
		return str;
	}

}
