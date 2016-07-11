/**
 * 
 */
package com.czw.util;

import java.net.URLDecoder;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author Huaishao Luo
 * @create 2015-1-14下午5:57:08
 */

public class DealStr {
	
	/**
	 *  用于处理字符串，用于通用字符处理，过滤一般符号
	 *  不转码，用于Struts2等自动转码的框架
	 * @param s
	 * @return
	 */
	public String codeStringNoEncode(String s) {
		String str = s;
		try {
			str = str.replace("'", "");
			str = str.replace("\"", "“");
			str = str.replace("<", "＜");
			str = str.replace(">", "＞");
			str = str.replace("--", "，");
			str = str.replace(";", "；");
			str = str.replace("/*", "");
			str = str.replace("*/", "");
			str = str.replace("xp_", "");
			str = str.trim();
			return str;
		} catch (Exception e) {
			return str;
		}
	}
	// 用于处理字符串，用于通用字符处理，过滤一般符号
	public String codeString(String s) {
		String str = s;
		try {
		 	str = new String(str.getBytes("ISO-8859-1"), "UTF-8");
			str = str.replace("'", "");
			str = str.replace("\"", "“");
			str = str.replace("<", "＜");
			str = str.replace(">", "＞");
			str = str.replace("--", "，");
			str = str.replace(";", "；");
			str = str.replace("/*", "");
			str = str.replace("*/", "");
			str = str.replace("xp_", "");
			str = str.trim();
			return str;
		} catch (Exception e) {
			return str;
		}
	}
	// 判断字符串是否是数字
	public boolean isNumeric(String str){ 
		   Pattern pattern = Pattern.compile("-?[0-9]+.?[0-9]+"); 
		   Matcher isNum = pattern.matcher(str);
		   if( !isNum.matches() ){
		       return false; 
		   } 
		   return true; 
		}
	// 用于处理字符串，转码 
	public String codeStringEncode(String s) {
			String str = s;
			try {
			 	str = URLDecoder.decode(s,"utf-8");
				str = str.replace("'", "");
				str = str.replace("\"", "“");
				str = str.replace("<", "＜");
				str = str.replace(">", "＞");
				str = str.replace("--", "，");
				str = str.replace(";", "；");
				str = str.replace("/*", "");
				str = str.replace("*/", "");
				str = str.replace("xp_", "");
				str = str.trim();
				return str;
			} catch (Exception e) {
				return str;
			}
	}
	
	// 只过滤单引号
	public String codeStringNoCode(String s) {
		String str = s;
		try {
			str = new String(str.getBytes("ISO-8859-1"), "UTF-8");
			str = str.replace("'", "");
			str = str.replace("/*", "");
			str = str.replace("*/", "");
			str = str.replace("xp_", "");
			str = str.trim();
			return str;
		} catch (Exception e) {
			return str;
		}
	}

	// 用于处理字符数组
	public String[] codeStringList(String str[]) {
		String[] outText;
		outText = new String[str.length];
		try {
			for (int i = 0; i < str.length; i++) {
				outText[i] = new String(str[i].getBytes("ISO-8859-1"), "UTF-8");
				outText[i] = outText[i].replace("'", "");
				outText[i] = outText[i].replace("/*", "");
				outText[i] = outText[i].replace("*/", "");
				outText[i] = outText[i].replace("xp_", "");
				outText[i] = outText[i].trim();
			}
			return outText;

		} catch (Exception e) {
			return outText;
		}
	}

	// 用于处理登录时的非法字符
	public String formatAllString(String s) {
		String str = s;
		try {
			str = new String(str.getBytes("ISO-8859-1"), "UTF-8");
			str = str.replace("'", "‘");
			str = str.replace(",", "，");
			str = str.replace("+", "");
			str = str.replace("-", "");
			str = str.replace("*", "");
			str = str.replace("/", "");
			str = str.replace("<", "");
			str = str.replace(">", "");
			str = str.replace(".", "");
			str = str.replace("!", "");
			str = str.replace("@", "");
			str = str.replace("#", "");
			str = str.replace("$", "");
			str = str.replace("%", "");
			str = str.replace("^", "");
			str = str.replace("&", "");
			str = str.replace("(", "");
			str = str.replace(")", "");
			str = str.replace("|", "");
			str = str.replace("?", "");
			str = str.replace("/*", "");
			str = str.replace("*/", "");
			str = str.replace("xp_", "");
			str = str.replace("--", "");
			str = str.trim();
			return str;
		} catch (Exception e) {
			return str;
		}
	}

	// 用于处理表单中的符号为HTML代码
	public String codeToHTML(String s) {
		String str = s;
		try {
			str = new String(str.getBytes("ISO-8859-1"), "UTF-8");
			str = str.replace("'", "‘");
			str = str.replace(",", "，");
			str = str.replace("<", "&lt;");
			str = str.replace(">", "&gt;");
			str = str.replace(" ", "&nbsp;");
			str = str.replace("\n", "<br>");
			str = str.replace("/*", "");
			str = str.replace("*/", "");
			str = str.replace("xp_", "");
			str = str.trim();
			return str;
		} catch (Exception e) {
			return str;
		}
	}

	// 用于转换HTML代码为明码
	public String codeFromHTML(String s) {
		String str = s;
		try {
			// str=new String(str.getBytes("UTF-8"),"ISO-8859-1");

			str = str.replace("&lt;", "<");
			str = str.replace("&gt;", ">");
			str = str.replace("&nbsp;", " ");
			str = str.replace("<br>", "\n");
			str = str.trim();
			return str;
		} catch (Exception e) {
			return str;
		}
	}

	// 用于过滤一些非法字符
	public String codeFormatHTML(String s) {
		String str = s;
		try {
			str = new String(str.getBytes("ISO-8859-1"), "UTF-8");

			str = str.replace("'", "");
			str = str.replace(",", "");
			str = str.replace("<", "");
			str = str.replace(">", "");
			str = str.replace("/*", "");
			str = str.replace("*/", "");
			str = str.replace("xp_", "");
			str = str.trim();
			return str;
		} catch (Exception e) {
			return str;
		}
	}

	// 用于格式化数据中的null值，如null20020413格式化为20020413
	public String formatNullData(String str) {
		String lastData = "";
		if (str == null) {
			lastData = "&nbsp;";
		} else {
			str = str.trim();
			str = str.replace(" ", "");
			lastData = str.replace("null", "");
			if (lastData.equals("") || lastData.equals(" ")) {
				lastData = "&nbsp;";
			}
		}
		return lastData;
	}

	public String formatNullDataForm(String str) {
		String lastData = "";
		if (str == null) {
			lastData = "";
		} else {
			lastData = str.replaceAll("null", "");
			lastData = str.replaceAll("\n", "");
			lastData = str.replaceAll("<br>", "");
			lastData = str.replaceAll("\t", "");
			lastData = str.replaceAll("\r", "");
			lastData = str.replaceAll("\r\n", "");
			lastData = str.replaceAll(" ", "");
		}
		return lastData;
	}

	public String formatNullDataXML(String str) {
		String lastData = "";
		if (str == null) {
			lastData = "null";
		} else {
			lastData = str;
			lastData = lastData.trim();
			lastData = lastData.replace("&nbsp;", " ");
			lastData = lastData.replace("(", "（");
			lastData = lastData.replace(")", "）");
			lastData = lastData.replace("&", "and");
			lastData = lastData.replace("<", "&lt;");
			lastData = lastData.replace(">", "&gt;");
			if (lastData.equals("")) {
				lastData = "null";
			}
		}
		return lastData;
	}

	// 用于格式化显示扶点数
	public String formatFloatData(float str) {
		String lastData = "";
		String float_str = String.valueOf(str);
		if (float_str == null) {
			lastData = "0";
		} else {
			lastData = float_str.replace(".0", "");
		}
		return lastData;
	}

	// 用于生成一个随机字符串，StrLength为定义字符串的位数
	public String getRanString(int StrLength) {
		Random ran = new Random();
		String str = "";
		int i = 0;
		do {
			int num = (int) (ran.nextDouble() * 122);
			if (num > 47) {
				if (!(((num > 57) & (num < 65)) | ((num > 90) & (num < 97)))) {
					str = str + (char) num;
					i++;
				}
			}
		} while (i < StrLength);
		return str;
	}

	// 用于生成指定位数的全大写字母的随机树
	public String getRanStringWord(int StrLength) {
		Random ran = new Random();
		String str = "";
		int i = 0;
		do {
			int num = (int) (ran.nextDouble() * 122);
			if (num > 64 && num < 91) {
				str = str + (char) num;
				i++;
			}
		} while (i < StrLength);
		return str;
	}

	// 用于生成一个指定长度的大写字母与数字的随机数
	public String getRanStringWordAndNum(int StrLength) {
		Random ran = new Random();
		String str = "";
		int i = 0;
		do {
			int num = (int) (ran.nextDouble() * 122);
			if (i % 2 == 0) {
				if (num > 64 && num < 91) {
					str = str + (char) num;
					i++;
				}
			} else {
				if (num > 47 && num < 58) {
					str = str + (char) num;
					i++;
				}
			}
		} while (i < StrLength);
		return str;
	}

	public String HTMLEncode(String text) {
		if (text == null)
			return "";
		StringBuffer results = null;
		char[] orig = null;
		int beg = 0, len = text.length();
		for (int i = 0; i < len; ++i) {
			char c = text.charAt(i);
			switch (c) {
			case 0:
			case '&':
			case '<':
			case '>':
			case '"':
				if (results == null) {
					orig = text.toCharArray();
					results = new StringBuffer(len + 10);
				}
				if (i > beg)
					results.append(orig, beg, i - beg);
				beg = i + 1;
				switch (c) {
				default: // case 0:
					continue;
				case '&':
					results.append("&");
					break;
				case '<':
					results.append("<");
					break;
				case '>':
					results.append(">");
					break;
				case '"':
					results.append("\"");
					break;
				}
				break;
			}
		}
		if (results == null)
			return text;
		results.append(orig, beg, len - beg);
		return results.toString();
	}
}