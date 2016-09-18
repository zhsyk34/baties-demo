package com.cat.crg.kit;

public class DaoKit {

	public static String currentPath(Class clazz) {
		return targetPath(Thread.currentThread().getStackTrace(), clazz, 0, 0);
	}

	public static String targetPath(StackTraceElement[] elements, Class clazz, int classForward, int methodForward) {
		return targetPath(elements, clazz, true, classForward, methodForward);
	}

	public static String targetPath(StackTraceElement[] elements, Class clazz, boolean face, int classForward, int methodForward) {
		int index = findStackIndex(elements, clazz);
		if (index == -1) {
			return null;
		}

		int classLevel = index + classForward, methodLevel = index + methodForward;
		if (classLevel > -1 && classLevel < elements.length && methodLevel > -1 && methodLevel < elements.length) {
			String className = elements[classLevel].getClassName();
			if (face) {
				try {
					String interfaceName = Class.forName(className).getInterfaces()[0].getName();
					return interfaceName + "." + elements[methodLevel].getMethodName();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			} else {
				return className + "." + elements[methodLevel].getMethodName();
			}

		}
		return null;
	}

	public static String targetMethod(StackTraceElement[] elements, Class clazz, int forward) {
		int index = findStackIndex(elements, clazz);
		if (index == -1) {
			return null;
		}
		int level = index + forward;
		return level > -1 && level < elements.length ? elements[level].getMethodName() : null;
	}

	public static String targetClass(StackTraceElement[] elements, Class clazz, int forward) {
		int index = findStackIndex(elements, clazz);
		if (index == -1) {
			return null;
		}
		int level = index + forward;
		return level > -1 && level < elements.length ? elements[level].getClassName() : null;

	}

	private static int findStackIndex(StackTraceElement[] elements, Class clazz) {
		for (int i = elements.length - 1; i > -1; i--) {
			if (elements[i].getClassName().equals(clazz.getName())) {
				return i;
			}
		}
		return -1;
	}

	public static boolean hasPersistent(Object value) {
		if (value == null) {
			return false;
		}

		Class clazz = value.getClass();
		if (clazz == String.class) {
			return ((String) value).trim().length() > 0;
		}
		if (clazz == int.class || clazz == Integer.class) {
			return (Integer) value > 0;
		}
		return (clazz == long.class || clazz == Long.class) && (Long) value > 0;
	}
}
