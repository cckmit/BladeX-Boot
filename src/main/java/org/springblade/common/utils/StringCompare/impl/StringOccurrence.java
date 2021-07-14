package org.springblade.common.utils.StringCompare.impl;

import org.springblade.common.utils.StringCompare.IStringSimilarityService;
import org.springblade.common.utils.StringUtil;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


/**
 * 计算两个字符串中，每个字出现在对方的比例
 */

@Service
public class StringOccurrence implements IStringSimilarityService {

	@Override
	public Double stringCompare(String str1, String str2) {
		int exists1 = 0;
		int exists2 = 0;
		str1 = StringUtil.ToDBC(StringUtil.removeSpecialCharacter(str1));
		str2 = StringUtil.ToDBC(StringUtil.removeSpecialCharacter(str2));


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

	@Override
	public String getConflictMethod() {
		return "CXL";
	}
}
