package com.renblack.killaura;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = KillAuraMod.MODID, version = KillAuraMod.VERSION, name = KillAuraMod.NAME)
public class KillAuraMod {

    public static final String MODID = "killaura";
    public static final String VERSION = "1.0";
    public static final String NAME = "Kill Aura Mobs";

    public static KillAuraHandler handler;

    @EventHandler
    public void init(FMLInitializationEvent event) {
        handler = new KillAuraHandler();
        MinecraftForge.EVENT_BUS.register(handler);
        FMLCommonHandler.instance().bus().register(handler);

        KeyBindingHandler keyHandler = new KeyBindingHandler(handler);
        MinecraftForge.EVENT_BUS.register(keyHandler);
        FMLCommonHandler.instance().bus().register(keyHandler);
    }

    @EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandX());
        event.registerServerCommand(new CommandAuto());
        event.registerServerCommand(new CommandZ());
        event.registerServerCommand(new CommandRange());
    }
}
