package oc.item.custom;


import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.MathHelper;
import oc.component.ModDataComponentTypes;
import oc.effect.ModEffects;
import org.jetbrains.annotations.Nullable;

public class TrenchBlade extends SwordItem {
    public TrenchBlade(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }    private static final int ITEM_BAR_COLOR = MathHelper.packRgb(0.2F, 0.2F, 1.0F);



    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.damage(1, attacker, EquipmentSlot.MAINHAND);

        if (attacker == target.getAttacker()) {
        setPressureCount(stack, (int) (getPressureCount(stack) + 2.6f));
        System.out.println(ModDataComponentTypes.PRESSURE_COUNT);
        if (getPressureCount(stack) >= 13) {
            target.setVelocity(0,-100,0);
            target.velocityModified = true;
           setPressureCount(stack, 0);
        }
        }



            return super.postHit(stack, target, attacker);
        }
    public int getPressureCount(ItemStack stack) { return stack.get(ModDataComponentTypes.PRESSURE_COUNT);}
    public void setPressureCount(ItemStack stack, int value) { stack.set(ModDataComponentTypes.PRESSURE_COUNT, Math.clamp(value, 0,13));}
    public boolean isItemBarVisible(ItemStack stack) {
        return getPressureCount(stack) > -1;
    }
    public int getItemBarStep(ItemStack stack) {

        return getPressureCount(stack);

    }
    public int getItemBarColor(ItemStack stack) {
        return ITEM_BAR_COLOR;
    }

}

