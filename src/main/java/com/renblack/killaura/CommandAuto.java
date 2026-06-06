package com.renblack.killaura;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

public class CommandAuto extends CommandBase {

    @Override
    public String getCommandName() { return "auto"; }

    @Override
    public String getCommandUsage(ICommandSender sender) { return "/auto"; }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        KillAuraHandler.autoClickEnabled = !KillAuraHandler.autoClickEnabled;
        sender.addChatMessage(new ChatComponentText(
            "Auto Click: " + (KillAuraHandler.autoClickEnabled ? "§aATIVADO" : "§cDESATIVADO")
        ));
    }

    @Override
    public int getRequiredPermissionLevel() { return 0; }
}
