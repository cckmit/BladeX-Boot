package org.springblade.common.utils;

import com.alibaba.druid.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EncryptedIDUtil {

	/**
	 * 用户电话号码的打码隐藏加星号加*
	 *
	 * @return 处理完成的身份证
	 */
	public static String phoneMask(String phone) {
		String res = "";
		if (!StringUtils.isEmpty(phone)) {
			StringBuilder stringBuilder = new StringBuilder(phone);
			res = stringBuilder.replace(3, 7, "****").toString();
		}
		return res;
	}

	/**
	 * 用户身份证号码的打码隐藏加星号加*
	 *
	 * @return 处理完成的身份证
	 */
	public static String idCardMask(String idCardNum) {
		String res = "";
		if (!StringUtils.isEmpty(idCardNum)) {
			StringBuilder stringBuilder = new StringBuilder(idCardNum);
			res = stringBuilder.replace(6, 14, "********").toString();
		}
		return res;
	}



	//身份证效验
	public static boolean isIDNumber(String IDNumber) {
		if (IDNumber == null || "".equals(IDNumber)) {
			return false;
		}
		// 定义判别用户身份证号的正则表达式（15位或者18位，最后一位可以为字母）
		String regularExpression = "(^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|" +
			"(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)";

		boolean matches = IDNumber.matches(regularExpression);

		//判断第18位校验值
		if (matches) {

			if (IDNumber.length() == 18) {
				try {
					char[] charArray = IDNumber.toCharArray();
					//前十七位加权因子
					int[] idCardWi = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
					//这是除以11后，可能产生的11位余数对应的验证码
					String[] idCardY = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};
					int sum = 0;
					for (int i = 0; i < idCardWi.length; i++) {
						int current = Integer.parseInt(String.valueOf(charArray[i]));
						int count = current * idCardWi[i];
						sum += count;
					}
					char idCardLast = charArray[17];
					int idCardMod = sum % 11;
					if (idCardY[idCardMod].toUpperCase().equals(String.valueOf(idCardLast).toUpperCase())) {
						return true;
					} else {
						return false;
					}

				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			}

		}
		return matches;
	}


	//手机号码最新效验
	public static boolean CheckMobilePhoneNum(String phoneNum) {
		String regex = "^(1[3-9]\\d{9}$)";
		if (phoneNum.length() == 11) {
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(phoneNum);
			if (m.matches()) {
				return true;
			}
		}
		return false;
	}

	/**
	 *
	 * 判断是否是正整数以及效验手机号码正则表达式
	 *
	 */
	public static boolean isNumeric(String string){
		Pattern pattern = Pattern.compile("[0-9]+");
		return pattern.matcher(string).matches();
	}


	/**
	 * 如用户身份证号码的打码隐藏加星号加*
	 *  参数异常直接返回null
	 *
	 * 需要隐藏的字符串
	 *  front     需要显示前几位
	 *  end       需要显示末几位
	 * @return 处理完成的身份证
	 */
//	//身份证显示添加****号
//	public static String idMask(String idCardNum) {
//		int front =5;
//		int end = 3;
//		//身份证不能为空
//		if (StringUtils.isEmpty(idCardNum)) {
//			return null;
//		}
//		//需要截取的长度不能大于身份证号长度
//		if ((front + end) > idCardNum.length()) {
//			return null;
//		}
//		//需要截取的不能小于0
//		if (front < 0 || end < 0) {
//			return null;
//		}
//		//计算*的数量
//		int asteriskCount = idCardNum.length() - (front + end);
//		StringBuffer asteriskStr = new StringBuffer();
//		for (int i = 0; i < asteriskCount; i++) {
//			asteriskStr.append("*");
//		}
//		String regex = "(\\w{" + String.valueOf(front) + "})(\\w+)(\\w{" + String.valueOf(end) + "})";
//		return idCardNum.replaceAll(regex, "$1" + asteriskStr + "$3");
//	}
}
