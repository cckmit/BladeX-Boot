package org.springblade.common.utils.StringCompare;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;


@Component
public class StringSimilarityFactory {

	private final Map<String,IStringSimilarityService> serviceMap;

	public StringSimilarityFactory(Map<String, IStringSimilarityService> serviceMap) {
		this.serviceMap = serviceMap;
	}


	//根据渠道类型获取对应字符串比较的Service
	public IStringSimilarityService buildService(String conflictMethod){
		return serviceMap.get(conflictMethod);
	}
}
