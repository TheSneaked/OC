package oc.enchantment.effect;

import com.ibm.icu.text.Normalizer2;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.EnchantmentLevelBasedValue;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.Entity;

import net.minecraft.entity.LivingEntity;

import net.minecraft.entity.effect.StatusEffectInstance;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;

import net.minecraft.util.math.Vec3d;
import oc.effect.ModEffects;

public record BuyoyantEnchantmentEffect(EnchantmentLevelBasedValue amount) implements EnchantmentEntityEffect {
    public static final MapCodec<BuyoyantEnchantmentEffect> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    EnchantmentLevelBasedValue.CODEC.fieldOf("amount").forGetter(BuyoyantEnchantmentEffect::amount)
            ).apply(instance, BuyoyantEnchantmentEffect::new)
    );




    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity target, Vec3d pos) {

        if (target instanceof LivingEntity victim) {


            ((LivingEntity) target).addStatusEffect(new StatusEffectInstance(ModEffects.BUOYANT_EFFECT, 120, 1));
            ((LivingEntity) target).removeStatusEffect(ModEffects.SUBMERGED_EFFECT);

        }

    }


    @Override
    public MapCodec<? extends EnchantmentEntityEffect> getCodec() {
        return CODEC;
    }
}