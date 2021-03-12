package com.kyobobook.mdm.common.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.kyobobook.mdm.common.exception.MDMException;

public class BeanUtil {

	public static <C> List<C> mapToBeanList(Object obj, Class<C> clazz) {

		List<C> beanList = new ArrayList<C>();
		List<Map<String, Object>> list = (List) obj;

		for (Map<String, Object> source : list) {
			beanList.add(mapToBean(source, clazz));
		}

		return beanList;
	}

	public static <C> C mapToBean(Object obj, Class<C> clazz) {
		C bean = null;
		Map<String, Object> map = (Map) obj;
		
		try {
			Constructor<C> constructor = clazz.getConstructor();
			bean = (C) constructor.newInstance();

			BeanUtils.populate(bean, map);

		} catch (IllegalAccessException | InvocationTargetException | InstantiationException | NoSuchMethodException
				| SecurityException e) {
			e.printStackTrace();
			throw new MDMException("ERR_MAPPING");
		}

		return bean;
	}
}
