package oc.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class BuoyantEffect extends StatusEffect {
    public BuoyantEffect() {
        super(StatusEffectCategory.HARMFUL, 1182351);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        entity.setOnFire(false);

        if (entity.isTouchingWater()){
            entity.setVelocity(0,1,0);
            entity.velocityModified = true;

            entity.setSwimming(true);
        }
        return super.applyUpdateEffect(entity, amplifier);
    }
}
//Me when pizza with onions and peppers go yummers :yum:
// im bouta
//im bouta crhas out raghhhh