package com.renblack.killaura;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

public class CommandZ extends CommandBase {

    @Override
    public String getCommandName() { return "z"; }

    @Override
    public String getCommandUsage(ICommandSender sender) { return "/z"; }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        KillAuraHandler.cameraLockEnabled = !KillAuraHandler.cameraLockEnabled;
        sender.addChatMessage(new ChatComponentText(
            "Camera Lock: " + (KillAuraHandler.cameraLockEnabled ? "§aATIVADO" : "§cDESATIVADO")
        ));
    }

    @Override
    public int getRequiredPermissionLevel() { return 0; }
}
