package com.renblack.killaura;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

public class CommandX extends CommandBase {

    @Override
    public String getCommandName() { return "x"; }

    @Override
    public String getCommandUsage(ICommandSender sender) { return "/x"; }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        KillAuraMod.handler.toggleKillAura();
    }

    @Override
    public int getRequiredPermissionLevel() { return 0; }
}