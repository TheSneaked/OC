package oc.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import oc.item.TeleportUtil;
import oc.world.dimension.ModDimensions;

public class ModEvents {



    private static void onPlayerRespawn(ServerPlayerEntity oldPlayer, ServerPlayerEntity player) {
        MinecraftServer server = player.getServer();
        if (server == null) return;
        ServerWorld targetWorld = server.getWorld(ModDimensions.SPACE_LEVEL_KEY);
        if (targetWorld == null) return;

        if (oldPlayer.getWorld().getRegistryKey() == ModDimensions.SPACE_LEVEL_KEY) {
            Vec3d deathPos = oldPlayer.getLastDeathPos().isPresent() ?
                    oldPlayer.getLastDeathPos().get().pos().toCenterPos() : oldPlayer.getPos();
            TeleportUtil.teleportUser(player, targetWorld, deathPos);
        }


    }





    public static void registerModEvents() {
        ServerPlayerEvents.AFTER_RESPAWN.register((oldPlayer, newPlayer, respawn) -> onPlayerRespawn(oldPlayer, newPlayer));

    }


}