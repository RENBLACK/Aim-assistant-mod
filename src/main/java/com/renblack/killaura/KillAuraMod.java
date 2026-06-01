package com.renblack.killaura;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = KillAuraMod.MODID, version = KillAuraMod.VERSION, name = KillAuraMod.NAME)
public class KillAuraMod {

    public static final String MODID = "killaura";
    public static final String VERSION = "1.0";
    public static final String NAME = "Kill Aura Mobs";

    @EventHandler
    public void init(FMLInitializationEvent event) {
        KillAuraHandler handler = new KillAuraHandler();
        MinecraftForge.EVENT_BUS.register(handler);

        KeyBindingHandler keyHandler = new KeyBindingHandler(handler);
        MinecraftForge.EVENT_BUS.register(keyHandler);
    }
}