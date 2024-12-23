package oc.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class LeviSword extends SwordItem {
    public LeviSword(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.setVelocity((attacker.getX() - target.getX())/4,( attacker.getY() - target.getY())/4, (attacker.getZ() - target.getZ())/4);
        if (target instanceof PlayerEntity player) {
            player.velocityModified = true;
        }
        return super.postHit(stack, target, attacker);
    }




    }
//kill me