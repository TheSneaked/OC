package oc.item.custom;


import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.noise.SimplexNoiseSampler;
import net.minecraft.world.World;

import java.util.List;


public class AbyssalMoor extends SwordItem {


    public AbyssalMoor(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {

        Box box = target.getBoundingBox().expand(2.0, 2.0, 2.0);
        List<LivingEntity> list = target.getWorld().getNonSpectatingEntities(LivingEntity.class, box);
        for (LivingEntity livingEntity : list) {
            livingEntity.setVelocity((livingEntity.getX() / 12000), (livingEntity.getY() / 80), (livingEntity.getZ() / 12000));
            int particleCount = 200;
            double radius = 4.0;
            Vec3d center = attacker.getPos();

            if (!attacker.getWorld().isClient) {
                attacker.playSound(SoundEvents.ITEM_MACE_SMASH_GROUND_HEAVY, 1f, 1f);

                var noise = new SimplexNoiseSampler(attacker.getRandom());

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
                    ((ServerWorld) attacker.getWorld()).spawnParticles(ParticleTypes.POOF, x, y + 0.2, z, 0, 0, risingSpeed, 0, 0.5);
                }



                return super.postHit(stack, target, attacker);
            }


            return postHit(stack, target, attacker);
        }
        return postHit(stack, target, attacker);
    }
}







