package com.renblack.renaimassistant;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

public class CommandAim extends CommandBase {

    @Override
    public String getCommandName() { return "aim"; }

    @Override
    public String getCommandUsage(ICommandSender sender) { return "/aim <kill|lock|auto>"; }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.addChatMessage(new ChatComponentText("§eUso: /aim <kill|lock|auto>"));
            return;
        }
        switch (args[0].toLowerCase()) {
            case "kill":
                AimHandler.killAuraEnabled = !AimHandler.killAuraEnabled;
                sender.addChatMessage(new ChatComponentText(
                    "Kill Aura: " + (AimHandler.killAuraEnabled ? "§aATIVADO" : "§cDESATIVADO")));
                break;
            case "lock":
                AimHandler.cameraLockEnabled = !AimHandler.cameraLockEnabled;
                sender.addChatMessage(new ChatComponentText(
                    "Camera Lock: " + (AimHandler.cameraLockEnabled ? "§aATIVADO" : "§cDESATIVADO")));
                break;
            case "auto":
                AimHandler.autoClickEnabled = !AimHandler.autoClickEnabled;
                sender.addChatMessage(new ChatComponentText(
                    "Auto Click: " + (AimHandler.autoClickEnabled ? "§aATIVADO" : "§cDESATIVADO")));
                break;
            default:
                sender.addChatMessage(new ChatComponentText("§cUso: /aim <kill|lock|auto>"));
        }
    }

    @Override
    public int getRequiredPermissionLevel() { return 0; }
}
