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

	//region   Levenshtein算法 计算两个字符相似度

	/**
	 * 获取两字符串的相似度
	 * Levenshtein算法
	 */
	public static double getSimilarityRatio(String str, String target) {
		str=ToDBC(str);
		target=ToDBC(target);

		int max = Math.max(str.length(), target.length());
		return 1 - (float) compare(str, target) / max;
	}


	/**
	 * 比较两个字符串的相识度(Levenshtein算法)
	 * 核心算法：用一个二维数组记录每个字符串是否相同，如果相同记为0，不相同记为1，每行每列相同个数累加
	 * 则数组最后一个数为不相同的总数，从而判断这两个字符的相识度
	 *
	 * @param str
	 * @param target
	 * @return
	 */
	private static int compare(String str, String target) {
		int d[][];              // 矩阵
		int n = str.length();
		int m = target.length();
		int i;                  // 遍历str的
		int j;                  // 遍历target的
		char ch1;               // str的
		char ch2;               // target的
		int temp;               // 记录相同字符,在某个矩阵位置值的增量,不是0就是1
		if (n == 0) {
			return m;
		}
		if (m == 0) {
			return n;
		}
		d = new int[n + 1][m + 1];
		// 初始化第一列
		for (i = 0; i <= n; i++) {
			d[i][0] = i;
		}
		// 初始化第一行
		for (j = 0; j <= m; j++) {
			d[0][j] = j;
		}
		for (i = 1; i <= n; i++) {
			// 遍历str
			ch1 = str.charAt(i - 1);
			// 去匹配target
			for (j = 1; j <= m; j++) {
				ch2 = target.charAt(j - 1);
				if (ch1 == ch2 || ch1 == ch2 + 32 || ch1 + 32 == ch2) {
					temp = 0;
				} else {
					temp = 1;
				}
				// 左边+1,上边+1, 左上角+temp取最小
				d[i][j] = min(d[i - 1][j] + 1, d[i][j - 1] + 1, d[i - 1][j - 1] + temp);
			}
		}
		return d[n][m];
	}


	/**
	 * 获取最小的值
	 */
	private static int min(int one, int two, int three) {
		return (one = one < two ? one : two) < three ? one : three;
	}

	//endregion

	//region	计算两个字符串中，每个字出现在对方的比例

	/**
	 * 计算两个字符串中，每个字出现在对方的比例【取最大的比例】
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static double stringOccurrenceRate(String str1, String str2)
	{
		int exists1 = 0;
		int exists2 = 0;
		str1 = ToDBC(removeSpecialCharacter(str1));
		str2 = ToDBC(removeSpecialCharacter(str2));


		char[] array1 = str1.toCharArray();
		char[] array2 = str2.toCharArray();

		for (char item: array1)
		{
			if (str2.contains(Character.toString(item)))
			{
				exists1++;
			}
		}

		for (char item :array2)
		{
			if (str1.contains(Character.toString(item)))
			{
				exists2++;
			}
		}

		double rate1 = exists1*1.0 / str1.length();
		double rate2 = exists2 * 1.0 / str2.length();

		return rate1 > rate2 ? rate1 : rate2;
	}
	//endregion




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


