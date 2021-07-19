package org.springblade.common.utils;


/**
 * 字符串工具类
 *
 * @author lizhanpeng
 */
public class StringUtil {

	/**
	 * 转半角的函数(SBC case)
	 * @param input
	 * @return
	 */
	public static String ToDBC(String input)
	{
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++)
		{
			if (c[i] == 12288)
			{
				c[i] = (char)32;
				continue;
			}
			if (c[i] > 65280 && c[i] < 65375)
				c[i] = (char)(c[i] - 65248);
		}
		return new String(c);
	}

	/**
	 * 正则表达式替换字符串中所有符号字符
	 * @param hexData
	 * @return
	 */
	public static String removeSpecialCharacter(String hexData)
	{
		if(hexData==null)
		{
			return "";
		}
		return hexData.replaceAll("[ \\s\\{\\}\\[ \\] \\^ \\-_*×――(^)$%~!@#$…&%￥—+=<>《》!！??？:：•`·、。，；,.;\"‘’“”-]", "");
	}

	/**
	 * 替换字符串
	 * @param sourceStr 原字符
	 * @param needReplaceStr 需要替换的字符，如果有多个字符需要替换的，以“,”相隔开
	 * @return
	 */
	public static String replaceString(String sourceStr, String needReplaceStr)
	{
		if (sourceStr==null || sourceStr.equals(""))
		{
			return "";
		}

		String[] array = needReplaceStr.split(",");
		for (String item : array)
		{
			sourceStr = sourceStr.replace(item,"");
		}
		return sourceStr;
	}
}


