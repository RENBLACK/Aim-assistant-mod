package com.renblack.killaura;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

public class CommandZ extends CommandBase {

    @Override
    public String getCommandName() { return "z"; }

    @Override
    public String getCommandUsage(ICommandSender sender) { return "/z"; }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        KillAuraMod.handler.toggleCameraLock();
    }

    @Override
    public int getRequiredPermissionLevel() { return 0; }
}