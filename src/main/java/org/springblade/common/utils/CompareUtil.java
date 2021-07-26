package org.springblade.common.utils;

import io.swagger.annotations.ApiModelProperty;
import org.springblade.core.tool.support.Kv;


import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.*;

public class CompareUtil {

	/**
	 * 实体差异比较器
	 *
	 * @param source  源版本实体
	 * @param current 当前版本实体
	 * @return
	 */
	public static List<Kv> compareEntityFields(Object source, Object current) {
		return compareEntityFields(source, current, null);
	}


	/**
	 * 实体差异比较器
	 *
	 * @param source    源版本实体
	 * @param current   当前版本实体
	 * @param ignoreArr 指定字段不比较
	 * @return
	 */
	public static  List<Kv> compareEntityFields(Object source, Object current, String[] ignoreArr) {
		try {
			List<Kv> list =new ArrayList<>();
			List<String> ignoreList = null;
			if (ignoreArr != null && ignoreArr.length > 0) {
				// array转化为list
				ignoreList = Arrays.asList(ignoreArr);
			}
			if (source.getClass() == current.getClass()) {// 只有两个对象都是同一类型的才有可比性
				Class clazz = source.getClass();
				// 获取object的属性描述
				Field[] fieldList = source.getClass().getDeclaredFields();
				for (Field f : fieldList) {
					f.setAccessible(true);
					Object v1 = f.get(source);
					Object v2 = f.get(current);

					if (v1 instanceof Timestamp) {
						v1 = new Date(((Timestamp) v1).getTime());
					}

					if (v2 instanceof Timestamp) {
						v2 = new Date(((Timestamp) v2).getTime());
					}

					if (v1 == null && v2 == null) continue;

					if (!v1.equals(v2)) {// 比较这两个值是否相等,不等就可以放入map了
						ApiModelProperty property = f.getAnnotation(ApiModelProperty.class);
						String temp = "";
						if (property != null) temp = property.value();

						Kv kv=Kv.create();
						kv.set("colIndex",f.getName());
						kv.set("oldVal", v1 == null ? "" : v1);
						kv.set("changeVal", v2 == null ? "" : v2);
						kv.set("colName", temp);
						list.add(kv);
					}
				}
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
