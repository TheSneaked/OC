package oc.enchantment.effect;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.EnchantmentLevelBasedValue;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import oc.world.dimension.ModDimensions;

import java.util.Set;

public record ReturnalEnchantmentEffect(EnchantmentLevelBasedValue amount) implements EnchantmentEntityEffect {
    public static final MapCodec<ReturnalEnchantmentEffect> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    EnchantmentLevelBasedValue.CODEC.fieldOf("amount").forGetter(ReturnalEnchantmentEffect::amount)
            ).apply(instance, ReturnalEnchantmentEffect::new)
    );


    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world instanceof ServerWorld serverWorld) {
            var infinity = serverWorld.getServer().getWorld(ModDimensions.SPACE_LEVEL_KEY);
            for (ServerPlayerEntity player : infinity.getPlayers()) {
                player.teleport(serverWorld,user.getX(), user.getY() , user.getZ(), Set.of(),user.getYaw(), user.getPitch());
            }
        }


        return TypedActionResult.success(user.getStackInHand(hand));
    }

    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity user, Vec3d pos) {

    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> getCodec() {
        return null;
    }
}


 //  player.requestTeleport(player.getX(), player.getY() - 4, player.getZ());