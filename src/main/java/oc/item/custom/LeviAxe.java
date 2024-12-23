package oc.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.noise.SimplexNoiseSampler;
import net.minecraft.world.World;
import org.joml.SimplexNoise;

import java.util.List;

public class LeviAxe extends SwordItem {


    public LeviAxe(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        Box box = null;
        if (
                world instanceof ServerWorld serverWorld
        ) {
        }
        if (!((PlayerEntity) user).getItemCooldownManager().isCoolingDown(this)) {
            box = user.getBoundingBox().expand(2.0, 2.0, 2.0);
            List<LivingEntity> list = user.getWorld().getNonSpectatingEntities(LivingEntity.class, box);
            for (LivingEntity livingEntity : list) {
                livingEntity.setVelocity((livingEntity.getX() / 12000), (livingEntity.getY() / 80), (livingEntity.getZ() / 12000));
                ((PlayerEntity) user).getItemCooldownManager().set(this, 100);
                int particleCount = 200;
                double radius = 4.0;
                Vec3d center = user.getPos();

                if (!user.getWorld().isClient) {
                    user.playSound(SoundEvents.ITEM_MACE_SMASH_GROUND_HEAVY, 1f, 1f);

                    var noise = new SimplexNoiseSampler(user.getRandom());

                    for (int i = 0; i < particleCount; i++) {
                        // Calculate the angle in radians
                        double angle = 2 * Math.PI * i / particleCount;

                        // Calculate x and z coordinates for the particle
                        double localX = radius * Math.cos(angle);
                        double localZ = radius * Math.sin(angle);

                        double x = center.x + localX;
                        double z = center.z + localZ;

                        // y can be the height above ground where you want the particles to appear
                        double y = center.y;

                        double freq = 0.4; // CHANGE THIS VALUE
                        double noiseSample = noise.sample(localX * freq, localZ * freq) + 0.5;
                        double risingSpeed = noiseSample * 0.01; // CHANGE THIS VALUE (the 0.3)

                        // Spawn the fire particle at the calculated position
                        ((ServerWorld) user.getWorld()).spawnParticles(ParticleTypes.TRIAL_SPAWNER_DETECTION_OMINOUS, x, y + 0.2, z, 0, 0, risingSpeed, 0, 0.5);
                    }


                    super.use(world, user, hand);
                }

                return super.use(world, user, hand);


            }


            return super.use(world, user, hand);
        }
        return super.use(world,user,hand);
    }
}
