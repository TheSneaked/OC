package oc.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import oc.item.ModItems;
import org.jetbrains.annotations.Nullable;

public class LeviathanSwordProjectileEntity extends PersistentProjectileEntity {
    boolean returns = false;


    @Override
    public void tick() {
        if(returns) {
            if (this.getOwner() != null) {
                Vec3d vec3d = this.getOwner().getEyePos().subtract(this.getPos()).normalize();
                this.setVelocity(vec3d);
            }
        }
        else {
            if (this.getOwner() != null && this.distanceTo(this.getOwner()) >= 15) {
                returns = true;
            }
        }
        super.tick();
    }

    public LeviathanSwordProjectileEntity(double x, double y, double z, World world, ItemStack stack, @Nullable ItemStack weapon) {
        super(ModEntityTypes.leviathanSwordProjectileEntityEntityType, x, y, z, world, stack, weapon);
    }

    public LeviathanSwordProjectileEntity( LivingEntity owner, World world, ItemStack stack, @Nullable ItemStack shotFrom) {
        super(ModEntityTypes.leviathanSwordProjectileEntityEntityType, owner, world, stack, shotFrom);
    }

    public LeviathanSwordProjectileEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public ItemStack getDefaultItemStack() {
        return ModItems.LEVI_SWORD.getDefaultStack();
    }

}


