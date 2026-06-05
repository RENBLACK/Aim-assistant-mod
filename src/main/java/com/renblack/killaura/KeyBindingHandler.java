package com.renblack.killaura;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

public class KeyBindingHandler {

    public static final KeyBinding killAuraKey =
        new KeyBinding("Toggle Kill Aura", Keyboard.KEY_K, "Kill Aura");

    public static final KeyBinding cameraLockKey =
        new KeyBinding("Toggle Camera Lock", Keyboard.KEY_L, "Kill Aura");

    public static final KeyBinding autoClickKey =
        new KeyBinding("Toggle Auto Click", Keyboard.KEY_J, "Kill Aura");

    private final KillAuraHandler handler;

    public KeyBindingHandler(KillAuraHandler handler) {
        this.handler = handler;
        ClientRegistry.registerKeyBinding(killAuraKey);
        ClientRegistry.registerKeyBinding(cameraLockKey);
        ClientRegistry.registerKeyBinding(autoClickKey);
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (Keyboard.getEventKeyState()) {
            int keyCode = Keyboard.getEventKey();
            if (keyCode == killAuraKey.getKeyCode()) handler.toggleKillAura();
            else if (keyCode == cameraLockKey.getKeyCode()) handler.toggleCameraLock();
            else if (keyCode == autoClickKey.getKeyCode()) handler.toggleAutoClick();
        }
    }
}
