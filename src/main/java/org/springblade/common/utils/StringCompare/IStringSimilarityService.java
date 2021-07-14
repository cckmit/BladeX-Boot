package org.springblade.common.utils.StringCompare;

public interface IStringSimilarityService {
	//对比两个字符串
	Double stringCompare(String str1,String str2);

	//关键：getConflictMethod()方法，子类实现这个方法用于标识出渠道类型
	String getConflictMethod();
}
