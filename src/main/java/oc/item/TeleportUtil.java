package oc.item;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;



import java.util.Collections;

public class TeleportUtil {

    // This is how we teleport to places with the falling and everything
    public static void teleportUser(LivingEntity user, ServerWorld world, Vec3d position) {
        // Teleport the user
        var flags = Collections.EMPTY_SET;
        user.teleport(world, position.getX(), 4, position.getZ(), flags, user.getYaw(), user.getPitch());
    }
    public static void teleportUser(LivingEntity user, ServerWorld world) {
        teleportUser(user, world, user.getPos());
    }
}
