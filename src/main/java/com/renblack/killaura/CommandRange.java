package com.renblack.killaura;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.client.Minecraft;

public class CommandRange extends CommandBase {

    @Override
    public String getCommandName() { return "range"; }

    @Override
    public String getCommandUsage(ICommandSender sender) { return "/range <numero>"; }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length == 0) {
            KillAuraMod.handler.sendMessage("§eAlcance atual: §f" + KillAuraMod.handler.range + " blocos");
            return;
        }
        try {
            final double newRange = Double.parseDouble(args[0]);
            Minecraft.getMinecraft().addScheduledTask(new Runnable() {
                public void run() { KillAuraMod.handler.setRange(newRange); }
            });
        } catch (NumberFormatException e) {
            KillAuraMod.handler.sendMessage("§cUso: /range <numero> | Exemplo: /range 10");
        }
    }

    @Override
    public int getRequiredPermissionLevel() { return 0; }
}
