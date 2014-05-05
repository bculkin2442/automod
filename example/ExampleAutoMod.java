package com.bjculk.automod.example;

import org.apache.logging.log4j.Logger;

import com.bjculk.automod.annotations.AutoConfigure;
import com.bjculk.automod.annotations.Autoscan;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid="com.bjculk.automod.example", version="1", name="AutoMod Example mod")
@Autoscan
public class ExampleAutoMod {

	@AutoConfigure
	public ExampleCfg cfg = new ExampleCfg();
	private Logger log;
	
	@EventHandler
	public void afterInit(FMLPostInitializationEvent piev) {
		log.error("CFG Field is " + cfg.tst);
	}
	
	@EventHandler
	public void beforeInit(FMLPreInitializationEvent piev) {
		log = piev.getModLog();
	}
}
