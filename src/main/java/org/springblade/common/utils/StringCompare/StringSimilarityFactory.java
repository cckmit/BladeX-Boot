package org.springblade.common.utils.StringCompare;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;


@Service
public class StringSimilarityFactory  {

	@Autowired
	private Map<String,IStringSimilarityService> serviceMap;

	//根据渠道类型获取对应字符串比较的Service
	public IStringSimilarityService buildService(String conflictMethod){
		return serviceMap.get(conflictMethod);
	}



}
