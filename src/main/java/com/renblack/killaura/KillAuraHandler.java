package com.renblack.killaura;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MathHelper;

import java.util.List;

public class KillAuraHandler {

    public static double range = 5.0;
    public static boolean killAuraEnabled = false;
    public static boolean cameraLockEnabled = false;
    public static boolean autoClickEnabled = false;

    private int tickCooldown = 0;

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Minecraft mc = Minecraft.getMinecraft();
        if (mc.thePlayer == null || mc.theWorld == null) return;

        EntityPlayer player = mc.thePlayer;
        EntityLivingBase nearestPlayer = getNearestPlayer(Mob);

        if (cameraLockEnabled && nearestPlayer != null) {
            lockCameraOnEntity(player, nearestMob);
        }

        if (tickCooldown > 0) {
            tickCooldown--;
            return;
        }

        if (killAuraEnabled && nearestPlayer != null) {
            mc.playerController.attackEntity(player, nearestPlayer);
            player.swingItem();
            tickCooldown = 10;
        }

        if (autoClickEnabled) {
            if (mc.objectMouseOver != null
                && mc.objectMouseOver.entityHit instanceof EntityLivingBase) {
                EntityLivingBase target = (EntityLivingBase) mc.objectMouseOver.entityHit;
                if (!(target instanceof EntityPlayer)) {
                    mc.playerController.attackEntity(player, target);
                    player.swingItem();
                    tickCooldown = 10;
                }
            }
        }
    }

    private EntityLivingBase getNearestPlayer(EntityPlayer player) {
        Minecraft mc = Minecraft.getMinecraft();
        List<EntityLivingBase> entities = mc.theWorld.getEntitiesWithinAABB(
            EntityLivingBase.class,
            player.boundingBox.expand(range, range, range)
        );

        EntityLivingBase nearest = null;
        double nearestDist = Double.MAX_VALUE;

        for (EntityLivingBase entity : entities) {
            if (entity == player) continue;
            if (entity instanceof EntityPlayer) continue;
            if (entity instanceof EntityAnimal) continue;

            double dist = player.getDistanceToEntity(entity);
            if (dist < nearestDist && dist <= range) {
                nearest = entity;
                nearestDist = dist;
            }
        }
        return nearest;
    }

    private void lockCameraOnEntity(EntityPlayer player, EntityLivingBase target) {
        double dx = target.posX - player.posX;
        double dy = (target.posY + target.height / 2.0) - (player.posY + player.getEyeHeight());
        double dz = target.posZ - player.posZ;
        double dist = MathHelper.sqrt_double(dx * dx + dz * dz);

        float yaw = (float)(Math.toDegrees(Math.atan2(-dx, dz)));
        float pitch = (float)(Math.toDegrees(-Math.atan2(dy, dist)));

        player.rotationYaw = yaw;
        player.rotationPitch = pitch;
    }

    public void toggleKillAura() {
        killAuraEnabled = !killAuraEnabled;
        sendMessage("Kill Aura: " + (killAuraEnabled ? "§aATIVADO" : "§cDESATIVADO"));
    }

    public void toggleCameraLock() {
        cameraLockEnabled = !cameraLockEnabled;
        sendMessage("Camera Lock: " + (cameraLockEnabled ? "§aATIVADO" : "§cDESATIVADO"));
    }

    public void toggleAutoClick() {
        autoClickEnabled = !autoClickEnabled;
        sendMessage("Auto Click: " + (autoClickEnabled ? "§aATIVADO" : "§cDESATIVADO"));
    }

    public void setRange(double newRange) {
        range = newRange;
        sendMessage("§eAlcance definido para: §f" + newRange + " blocos");
    }

    public void sendMessage(String msg) {
        if (Minecraft.getMinecraft().thePlayer != null) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(msg));
        }
    }
}
