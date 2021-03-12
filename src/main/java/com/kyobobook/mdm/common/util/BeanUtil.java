package com.kyobobook.mdm.common.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.kyobobook.mdm.common.exception.MDMException;

public class BeanUtil {

	public static <T extends Map<String, Object>, C> List<C> mapToBeanList(List<T> list, Class<C> clazz) {

		List<C> beanList = new ArrayList<C>();

		for (T source : list) {
			beanList.add(mapToBean(source, clazz));
		}

		return beanList;
	}

	public static <T extends Map<String, Object>, C> C mapToBean(T map, Class<C> clazz) {
		C bean = null;

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
