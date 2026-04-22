package oc.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class SubmergedEffect extends StatusEffect {
    public SubmergedEffect() {
        super(StatusEffectCategory.HARMFUL, 1182351);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        entity.setOnFire(false);

            entity.setVelocity(0,-1,0);
            entity.setVelocity(0,-1,0);
            entity.velocityModified = true;

        return super.applyUpdateEffect(entity, amplifier);
    }
}
