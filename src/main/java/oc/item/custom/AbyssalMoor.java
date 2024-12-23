package oc.item.custom;


import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;


    public class AbyssalMoor extends SwordItem {
        public AbyssalMoor(ToolMaterial toolMaterial, Settings settings) {
            super(toolMaterial, settings);
        }


        @Override
        public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
            target.setVelocity((target.getX()/12000),(  target.getY()/80), ( target.getZ()/12000));
            if (target instanceof PlayerEntity player) {
                player.velocityModified = true;
            }
            return super.postHit(stack, target, attacker);
        }


        }







