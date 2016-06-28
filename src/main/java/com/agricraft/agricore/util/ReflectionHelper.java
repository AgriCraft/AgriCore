/*
 */
package com.agricraft.agricore.util;

import com.agricraft.agricore.core.AgriCore;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * A class to aid in the execution of reflection magic.
 *
 * @author RlonRyan
 */
public class ReflectionHelper {

	// Dummy Constructor.
	private ReflectionHelper() {
	}

	public static <T> void forEachIn(Object from, Class<T> type, Consumer<T> consumer) {
		final Object target = from instanceof Class ? null : from;
		forEachFieldIn(from, (field) -> {
			try {
				field.setAccessible(true);
				Object obj = field.get(target);
				if (obj != null && type.isAssignableFrom(obj.getClass())) {
					consumer.accept(type.cast(obj));
				}
			} catch (IllegalAccessException e) {
				// Oh well...
				AgriCore.getLogger("AgriCraft").warn(
						"ReflectionHelper.forEachIn() Skipping Field: \"{0}\" in Class: \"{1}\"!",
						field.getName(),
						from.getClass().getCanonicalName()
				);
			}
		});
	}

	public static <T, A extends Annotation> void forEachIn(Object from, Class<T> type, Class<A> annotation, BiConsumer<T, A> consumer) {
		final Object target = from instanceof Class ? null : from;
		ReflectionHelper.forEachFieldIn(from, annotation, (field, anno) -> {
			try {
				field.setAccessible(true);
				Object obj = field.get(target);
				if (obj != null && type.isAssignableFrom(obj.getClass())) {
					consumer.accept(type.cast(obj), anno);
				}
			} catch (IllegalAccessException e) {
				// Oh well...
				AgriCore.getLogger("AgriCraft").warn(
						"ReflectionHelper.forEachIn() Skipping Field: \"{0}\" in Class: \"{1}\"!",
						field.getName(),
						from.getClass().getCanonicalName()
				);
			}
		});
	}

	public static <A extends Annotation> void forEachFieldIn(Object from, Class<A> annotation, BiConsumer<Field, A> consumer) {
		forEachFieldIn(from, (field) -> {
			if (field.isAnnotationPresent(annotation)) {
				consumer.accept(field, field.getAnnotation(annotation));
			}
		});
	}

	public static void forEachFieldIn(Object from, Consumer<Field> consumer) {
		final boolean isInstance = !(from instanceof Class);
		final Class clazz = isInstance ? from.getClass() : (Class) from;
		for (Field f : clazz.getDeclaredFields()) {
			if (isInstance || Modifier.isStatic(f.getModifiers())) {
				consumer.accept(f);
			}
		}
	}

}
