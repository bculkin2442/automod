package com.bjculk.automod.config;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.apache.logging.log4j.Level;

import com.bjculk.automod.AutoMod;
import com.bjculk.automod.annotations.config.ConfigField;
import com.bjculk.automod.annotations.config.ConfigFlags;
import com.bjculk.automod.annotations.config.ConfigFlags.Flag;
import com.bjculk.automod.annotations.config.ConfigFlags.Flags;
import com.bjculk.automod.annotations.config.ConfigFlags.Float;
import com.bjculk.automod.annotations.config.ConfigFlags.Floats;
import com.bjculk.automod.annotations.config.ConfigFlags.Integer;
import com.bjculk.automod.annotations.config.ConfigFlags.Integers;
import com.bjculk.automod.annotations.config.ConfigFlags.Strings;

import net.minecraftforge.common.config.Configuration;

public class Configurator {

	public static void doConfigure(Configuration cfg, Object cfgStorage) {
		for (Field fld : cfgStorage.getClass().getDeclaredFields()) {
			processField(cfg, fld, cfgStorage);
		}
	}

	private static void processField(Configuration cfg, Field fld,
			Object cfgStorage) {
		if (fld.isAnnotationPresent(ConfigField.class)) {
			Annotation[] fldAnos = fld.getAnnotations();
			if (fldAnos.length == 1) {
				doStringField(cfg, fld, fld.getAnnotation(ConfigField.class),
						cfgStorage);
			} else if (fldAnos.length == 2) {
				processSpecializedField(cfg, fld, fldAnos, cfgStorage);
			} else {
				AutoMod.log.warn("Field "
						+ AutoMod.getClassifiedFieldName(cfgStorage, fld)
						+ " has more than one"
						+ " @ConfigFlags annotation. It will not be processed");
				AutoMod.log.warn("Specified Annotations: ");
				for (Annotation annotation : fldAnos) {
					AutoMod.log.warn("\t"
							+ annotation.annotationType().getName());
				}
			}
		}
	}

	private static void processSpecializedField(Configuration cfg, Field fld,
			Annotation[] fldAnos, Object cfgStorage) {
		ConfigField cfgFld = null;
		Annotation an = null;
		if (fldAnos[0] instanceof ConfigField) {
			cfgFld = (ConfigField) fldAnos[0];
			an = fldAnos[1];
		} else {
			cfgFld = (ConfigField) fldAnos[1];
			an = fldAnos[0];
		}
		if (an instanceof ConfigFlags.Flag) {
			doFlagField(cfg, fld, cfgFld, (ConfigFlags.Flag) an, cfgStorage);
		} else if (an instanceof ConfigFlags.Flags) {
			doFlagsField(cfg, fld, cfgFld, (ConfigFlags.Flags) an, cfgStorage);
		} else if (an instanceof ConfigFlags.Float) {
			doFloatField(cfg, fld, cfgFld, (ConfigFlags.Float) an, cfgStorage);
		} else if (an instanceof ConfigFlags.Floats) {
			doFloatsField(cfg, fld, cfgFld, (ConfigFlags.Floats) an, cfgStorage);
		} else if (an instanceof ConfigFlags.Integer) {
			doIntField(cfg, fld, cfgFld, (ConfigFlags.Integer) an, cfgStorage);
		} else if (an instanceof ConfigFlags.Integers) {
			doIntsField(cfg, fld, cfgFld, (ConfigFlags.Integers) an, cfgStorage);
		} else if (an instanceof ConfigFlags.Strings) {
			doStringsField(cfg, fld, cfgFld, (ConfigFlags.Strings) an,
					cfgStorage);
		} else {
			AutoMod.log.warn("Found unknown second annotation "
					+ an.annotationType().getName() + " on field"
					+ AutoMod.getClassifiedFieldName(cfgStorage, fld) + ". It will be ignored");
		}
	}

	private static void doStringsField(Configuration cfg, Field fld,
			ConfigField ann, Strings an, Object cfgStorage) {
		fld.setAccessible(true);
		String classifiedFieldName = AutoMod.getClassifiedFieldName(cfgStorage,
				fld);
		try {
			fld.set(cfgStorage, cfg.get(ann.category(), ann.value(),
					an.value(), ann.comment()));
		} catch (IllegalArgumentException e) {
			AutoMod.log
					.warn("Caught an IllegalArgumentException while setting field "
							+ classifiedFieldName);
			AutoMod.log
					.warn("Check to make sure you have the correct @ConfigFlags annotation on "
							+ classifiedFieldName);
			AutoMod.log.catching(Level.WARN, e);
		} catch (IllegalAccessException e) {
			AutoMod.log
					.warn("Caught an IllegalAccessException while setting field "
							+ classifiedFieldName);
			AutoMod.log.catching(Level.WARN, e);
		}		

	}

	private static void doIntsField(Configuration cfg, Field fld,
			ConfigField ann, Integers an, Object cfgStorage) {
		fld.setAccessible(true);
		String classifiedFieldName = AutoMod.getClassifiedFieldName(cfgStorage,
				fld);
		try {
			fld.set(cfgStorage, cfg.get(ann.category(), ann.value(),
					an.value(), ann.comment()));
		} catch (IllegalArgumentException e) {
			AutoMod.log
					.warn("Caught an IllegalArgumentException while setting field "
							+ classifiedFieldName);
			AutoMod.log
					.warn("Check to make sure you have the correct @ConfigFlags annotation on "
							+ classifiedFieldName);
			AutoMod.log.catching(Level.WARN, e);
		} catch (IllegalAccessException e) {
			AutoMod.log
					.warn("Caught an IllegalAccessException while setting field "
							+ classifiedFieldName);
			AutoMod.log.catching(Level.WARN, e);
		}		

	}

	private static void doIntField(Configuration cfg, Field fld,
			ConfigField ann, Integer an, Object cfgStorage) {
		fld.setAccessible(true);
		String classifiedFieldName = AutoMod.getClassifiedFieldName(cfgStorage,
				fld);
		try {
			fld.set(cfgStorage, cfg.get(ann.category(), ann.value(),
					an.value(), ann.comment()));
		} catch (IllegalArgumentException e) {
			AutoMod.log
					.warn("Caught an IllegalArgumentException while setting field "
							+ classifiedFieldName);
			AutoMod.log
					.warn("Check to make sure you have the correct @ConfigFlags annotation on "
							+ classifiedFieldName);
			AutoMod.log.catching(Level.WARN, e);
		} catch (IllegalAccessException e) {
			AutoMod.log
					.warn("Caught an IllegalAccessException while setting field "
							+ classifiedFieldName);
			AutoMod.log.catching(Level.WARN, e);
		}		

	}

	private static void doFloatsField(Configuration cfg, Field fld,
			ConfigField ann, Floats an, Object cfgStorage) {
		fld.setAccessible(true);
		String classifiedFieldName = AutoMod.getClassifiedFieldName(cfgStorage,
				fld);
		try {
			fld.set(cfgStorage, cfg.get(ann.category(), ann.value(),
					an.value(), ann.comment()));
		} catch (IllegalArgumentException e) {
			AutoMod.log
					.warn("Caught an IllegalArgumentException while setting field "
							+ classifiedFieldName);
			AutoMod.log
					.warn("Check to make sure you have the correct @ConfigFlags annotation on "
							+ classifiedFieldName);
			AutoMod.log.catching(Level.WARN, e);
		} catch (IllegalAccessException e) {
			AutoMod.log
					.warn("Caught an IllegalAccessException while setting field "
							+ classifiedFieldName);
			AutoMod.log.catching(Level.WARN, e);
		}		

	}

	private static void doFloatField(Configuration cfg, Field fld,
			ConfigField ann, Float an, Object cfgStorage) {
		fld.setAccessible(true);
		String classifiedFieldName = AutoMod.getClassifiedFieldName(cfgStorage,
				fld);
		try {
			fld.set(cfgStorage, cfg.get(ann.category(), ann.value(),
					an.value(), ann.comment()));
		} catch (IllegalArgumentException e) {
			AutoMod.log
					.warn("Caught an IllegalArgumentException while setting field "
							+ classifiedFieldName);
			AutoMod.log
					.warn("Check to make sure you have the correct @ConfigFlags annotation on "
							+ classifiedFieldName);
			AutoMod.log.catching(Level.WARN, e);
		} catch (IllegalAccessException e) {
			AutoMod.log
					.warn("Caught an IllegalAccessException while setting field "
							+ classifiedFieldName);
			AutoMod.log.catching(Level.WARN, e);
		}		

	}

	private static void doFlagsField(Configuration cfg, Field fld,
			ConfigField ann, Flags an, Object cfgStorage) {
		fld.setAccessible(true);
		String classifiedFieldName = AutoMod.getClassifiedFieldName(cfgStorage,
				fld);
		try {
			fld.set(cfgStorage, cfg.get(ann.category(), ann.value(),
					an.value(), ann.comment()));
		} catch (IllegalArgumentException e) {
			AutoMod.log
					.warn("Caught an IllegalArgumentException while setting field "
							+ classifiedFieldName);
			AutoMod.log
					.warn("Check to make sure you have the correct @ConfigFlags annotation on "
							+ classifiedFieldName);
			AutoMod.log.catching(Level.WARN, e);
		} catch (IllegalAccessException e) {
			AutoMod.log
					.warn("Caught an IllegalAccessException while setting field "
							+ classifiedFieldName);
			AutoMod.log.catching(Level.WARN, e);
		}		
		
	}

	private static void doFlagField(Configuration cfg, Field fld,
			ConfigField ann, Flag an, Object cfgStorage) {
		fld.setAccessible(true);
		String classifiedFieldName = AutoMod.getClassifiedFieldName(cfgStorage,
				fld);
		try {
			fld.set(cfgStorage, cfg.get(ann.category(), ann.value(),
					an.value(), ann.comment()));
		} catch (IllegalArgumentException e) {
			AutoMod.log
					.warn("Caught an IllegalArgumentException while setting field "
							+ classifiedFieldName);
			AutoMod.log
					.warn("Check to make sure you have the correct @ConfigFlags annotation on "
							+ classifiedFieldName);
			AutoMod.log.catching(Level.WARN, e);
		} catch (IllegalAccessException e) {
			AutoMod.log
					.warn("Caught an IllegalAccessException while setting field "
							+ classifiedFieldName);
			AutoMod.log.catching(Level.WARN, e);
		}		
	}

	private static void doStringField(Configuration cfg, Field fld,
			ConfigField ann, Object cfgStorage) {
		fld.setAccessible(true);
		String classifiedFieldName = AutoMod.getClassifiedFieldName(cfgStorage,
				fld);
		try {
			fld.set(cfgStorage, cfg.get(ann.category(), ann.value(),
					ann.defaultValue(), ann.comment()));
		} catch (IllegalArgumentException e) {
			AutoMod.log
					.warn("Caught an IllegalArgumentException while setting field "
							+ classifiedFieldName);
			AutoMod.log
					.warn("Check to make sure you have the correct @ConfigFlags annotation on "
							+ classifiedFieldName);
			AutoMod.log.catching(Level.WARN, e);
		} catch (IllegalAccessException e) {
			AutoMod.log
					.warn("Caught an IllegalAccessException while setting field "
							+ classifiedFieldName);
			AutoMod.log.catching(Level.WARN, e);
		}
	}

}
