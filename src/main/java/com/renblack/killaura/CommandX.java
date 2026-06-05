package com.renblack.killaura;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.client.Minecraft;

public class CommandX extends CommandBase {

    @Override
    public String getCommandName() { return "x"; }

    @Override
    public String getCommandUsage(ICommandSender sender) { return "/x"; }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        Minecraft.getMinecraft().addScheduledTask(new Runnable() {
            public void run() { KillAuraMod.handler.toggleKillAura(); }
        });
    }

    @Override
    public int getRequiredPermissionLevel() { return 0; }
}
