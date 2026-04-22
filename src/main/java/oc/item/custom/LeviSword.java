package oc.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import oc.entity.LeviathanSwordProjectileEntity;
import oc.entity.ModEntityTypes;

public class LeviSword extends SwordItem {
    public LeviSword(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (!world.isClient) {
            LeviathanSwordProjectileEntity leviathanSwordProjectileEntity = new LeviathanSwordProjectileEntity(user, world, stack, null);
            leviathanSwordProjectileEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 0.0f);
            world.spawnEntity(leviathanSwordProjectileEntity);
            stack.decrement(1);
        }
        return super.use(world, user, hand);
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