package oc.item.custom;


import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import oc.effect.ModEffects;

public class TrenchBlade extends SwordItem {
    public TrenchBlade(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }


    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {



        stack.damage(1, attacker, EquipmentSlot.MAINHAND);

        if (!target.isBlocking()) {

            if (!((PlayerEntity) attacker).getItemCooldownManager().isCoolingDown(this)) {

                    target.addStatusEffect(new StatusEffectInstance(ModEffects.SUBMERGED_EFFECT, 120, 1));


                    ((PlayerEntity) attacker).getItemCooldownManager().set(this, 200);
                }
            }




            return super.postHit(stack, target, attacker);
//swag messiah

        }




    }

