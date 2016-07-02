/*
 */
package com.agricraft.agricore.util;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * A class to help with using types.
 *
 * @author RlonRyan
 */
public class TypeHelper {
	
	public static <T> T[] asArray(Collection<T> set, Class<T> type) {
		@SuppressWarnings("unchecked")
		final T[] array = (T[])Array.newInstance(type, set.size());
		return set.toArray(array);
	}
	
	public static <T> List<T> asList(T... elements) {
		return Arrays.asList(elements);
	}
	
	public static boolean isAllTypes(Object obj, Class... types) {
		return isAllTypes(obj.getClass(), types);
	}
	
	public static boolean isAllTypes(Class clazz, Class... types) {
		for (Class type : types) {
			if (!isType(clazz, type)) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean isAnyType(Object obj, Class... types) {
		return isAnyType(obj.getClass(), types);
	}
	
	public static boolean isAnyType(Class clazz, Class... types) {
		for (Class type : types) {
			if (isType(clazz, type)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isType(Object obj, Class type) {
		return isType(obj.getClass(), type);
	}
	
	public static boolean isType(Class clazz, Class type) {
		return type.isAssignableFrom(clazz);
	}
	
}
