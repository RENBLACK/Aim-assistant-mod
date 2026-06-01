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

    private static final double RANGE = 5.0;

    private boolean killAuraEnabled = false;
    private boolean cameraLockEnabled = false;
    private boolean autoClickEnabled = false;

    private int tickCooldown = 0;

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Minecraft mc = Minecraft.getMinecraft();
        if (mc.thePlayer == null || mc.theWorld == null) return;

        EntityPlayer player = mc.thePlayer;
        EntityLivingBase nearestMob = getNearestMob(player);

        if (cameraLockEnabled && nearestMob != null) {
            lockCameraOnEntity(player, nearestMob);
        }

        if (tickCooldown > 0) {
            tickCooldown--;
            return;
        }

        if (killAuraEnabled && nearestMob != null) {
            player.attackTargetEntityWithCurrentItem(nearestMob);
            tickCooldown = 10;
        }

        if (autoClickEnabled) {
            if (mc.objectMouseOver != null
                && mc.objectMouseOver.entityHit instanceof EntityLivingBase) {
                EntityLivingBase target = (EntityLivingBase) mc.objectMouseOver.entityHit;
                if (!(target instanceof EntityPlayer)) {
                    player.attackTargetEntityWithCurrentItem(target);
                    tickCooldown = 10;
                }
            }
        }
    }

    private EntityLivingBase getNearestMob(EntityPlayer player) {
        Minecraft mc = Minecraft.getMinecraft();
        List<EntityLivingBase> entities = mc.theWorld.getEntitiesWithinAABB(
            EntityLivingBase.class,
            player.boundingBox.expand(RANGE, RANGE, RANGE)
        );

        EntityLivingBase nearest = null;
        double nearestDist = Double.MAX_VALUE;

        for (EntityLivingBase entity : entities) {
            if (entity == player) continue;
            if (entity instanceof EntityPlayer) continue;
            if (entity instanceof EntityAnimal) continue;

            double dist = player.getDistanceToEntity(entity);
            if (dist < nearestDist && dist <= RANGE) {
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

    private void sendMessage(String msg) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(msg));
    }
}