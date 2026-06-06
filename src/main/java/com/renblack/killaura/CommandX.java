package com.renblack.killaura;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

public class CommandX extends CommandBase {

    @Override
    public String getCommandName() { return "x"; }

    @Override
    public String getCommandUsage(ICommandSender sender) { return "/x"; }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        KillAuraHandler.killAuraEnabled = !KillAuraHandler.killAuraEnabled;
        sender.addChatMessage(new ChatComponentText(
            "Kill Aura: " + (KillAuraHandler.killAuraEnabled ? "§aATIVADO" : "§cDESATIVADO")
        ));
    }

    @Override
    public int getRequiredPermissionLevel() { return 0; }
}
