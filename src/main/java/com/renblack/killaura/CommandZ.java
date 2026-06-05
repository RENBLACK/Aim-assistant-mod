package com.renblack.killaura;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.client.Minecraft;

public class CommandZ extends CommandBase {

    @Override
    public String getCommandName() { return "z"; }

    @Override
    public String getCommandUsage(ICommandSender sender) { return "/z"; }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        Minecraft.getMinecraft().addScheduledTask(new Runnable() {
            public void run() { KillAuraMod.handler.toggleCameraLock(); }
        });
    }

    @Override
    public int getRequiredPermissionLevel() { return 0; }
}
