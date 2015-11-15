package net.reederhome.colin.epicmod.api;

import java.lang.reflect.Method;

public class EpicApi {

	public static final String REGISTRY = "net.reederhome.colin.epicmod.EpicRegistry";
	
	private static Object getRegistry() {
		try {
			return Class.forName(REGISTRY).getMethod("get").invoke(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static Object callOnRegistry(String name, Object... params) {
		Object reg = getRegistry();
		Class cl = reg.getClass();
		Class[] paramTypes = new Class[params.length];
		for(int i = 0; i < params.length; i++) {
			paramTypes[i] = params[i].getClass();
		}
		try {
			Method m = cl.getMethod(name, paramTypes);
			return m.invoke(reg, params);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void registerPower(Class<? extends IEpicPower> powerClass, String baseName) {
		callOnRegistry("registerPower", powerClass, baseName);
	}
	
	public static void registerPower(Class<? extends IEpicPower> powerClass) {
		try {
			registerPower(powerClass, powerClass.newInstance().getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void registerWeakness(Class<? extends IEpicWeakness> weaknessClass, String baseName) {
		callOnRegistry("registerWeakness", weaknessClass, baseName);
	}
	
	public static void registerWeaknessEffect(Class<? extends IEpicWeaknessEffect> weaknessClass, String baseName) {
		callOnRegistry("registerWeaknessEffect", weaknessClass, baseName);
	}
	
	public static void registerWeaknessEffect(Class<? extends IEpicWeaknessEffect> weaknessEffectClass) {
		try {
			registerWeaknessEffect(weaknessEffectClass, weaknessEffectClass.newInstance().getName());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}