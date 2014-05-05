package com.bjculk.automod;

import java.io.File;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

import com.bjculk.automod.annotations.AutoConfigure;
import com.bjculk.automod.annotations.Autoscan;
import com.bjculk.automod.config.Configurator;

import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid="com.bjculk.automod", version="1", name="Autoconfig mod",
dependencies="after:*")
public class AutoMod {
	private List<ModContainer> modList;
	private File autoCfgDir;
	public static Logger log;
	@EventHandler
	public void prerun(FMLPreInitializationEvent piev) {
		log = piev.getModLog();
		// Set default dir for autoconfig'd mods
		autoCfgDir = piev.getModConfigurationDirectory();
		// Do scan for tagged mods
		modList = Loader.instance().getModList();
		List<ModContainer> taggedMods = checkMods(modList);
		// Process mods
		for (ModContainer mc : taggedMods) {
			processMod(mc);
		}
	}

	private List<ModContainer> checkMods(List<ModContainer> modList2) {
		List<ModContainer> tagged = new LinkedList<ModContainer>();
		for (ModContainer mod : modList2) {
			if(mod.getMod().getClass().isAnnotationPresent(Autoscan.class)) {
				tagged.add(mod);
			}
		}
		return tagged;
	}

	private void processMod(ModContainer mod) {
		List<String> canAutoConfigure = canAutoConfigure(mod.getMod());
		if(canAutoConfigure.isEmpty()) {
			for (String fld : canAutoConfigure) {
				autoConfigure(mod, fld);
			}
		}
	}

	private void autoConfigure(ModContainer mod, String fld) {
		Configuration cfg = new Configuration(new File(autoCfgDir, mod.getModId()));
		Object cfgStorage;
		try {
			cfgStorage = mod.getMod().getClass().getDeclaredField(fld);
			Configurator.doConfigure(cfg, cfgStorage);
		} catch (NoSuchFieldException e) {
			log.debug("Couldn't find field " + fld +" that was detected in mod " + mod.getModId()
					+". Something has gone wrong");
			log.catching(Level.DEBUG, e);
		} catch (SecurityException e) {
			log.fatal("Got SecurityException while reflecting on field " + fld + " for mod " + mod.getModId()
					+ ". Bailing out now");
			log.catching(Level.FATAL, e);
			return;
		}
	}

	private List<String> canAutoConfigure(Object mod) {
		List<String> flds = new LinkedList<String>();
		for (Field fld : mod.getClass().getDeclaredFields()) {
			if(fld.isAnnotationPresent(AutoConfigure.class)) {
				flds.add(fld.getName());
			}
		}
		return flds;
	}
	
	public static String getClassifiedFieldName(Object obj, Field fld) {
		return obj.getClass().getName() + "#" + fld.getName();
	}
}
