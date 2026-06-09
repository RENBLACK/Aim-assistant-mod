package com.renblack.renaimassistant;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = RenAimAssistantMod.MODID, version = RenAimAssistantMod.VERSION, name = RenAimAssistantMod.NAME)
public class RenAimAssistantMod {

    public static final String MODID = "renaimassistant";
    public static final String VERSION = "1.0";
    public static final String NAME = "RenAim Assistant";

    public static AimHandler handler;

    @EventHandler
    public void init(FMLInitializationEvent event) {
        handler = new AimHandler();
        MinecraftForge.EVENT_BUS.register(handler);
        FMLCommonHandler.instance().bus().register(handler);

        KeyHandler keyHandler = new KeyHandler(handler);
        MinecraftForge.EVENT_BUS.register(keyHandler);
        FMLCommonHandler.instance().bus().register(keyHandler);
    }
}
