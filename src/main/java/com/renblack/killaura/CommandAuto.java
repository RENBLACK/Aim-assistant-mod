package com.renblack.killaura;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

public class CommandAuto extends CommandBase {

    @Override
    public String getCommandName() { return "auto"; }

    @Override
    public String getCommandUsage(ICommandSender sender) { return "/auto"; }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        KillAuraMod.handler.toggleAutoClick();
    }

    @Override
    public int getRequiredPermissionLevel() { return 0; }
}