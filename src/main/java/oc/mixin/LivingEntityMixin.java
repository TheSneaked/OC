package oc.mixin;


import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import oc.item.custom.LeviAxe;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Shadow
    @NotNull
    public abstract ItemStack getWeaponStack();

    @Inject(method = "disablesShield", at = @At("HEAD"), cancellable = true)
    public void disablesShield(CallbackInfoReturnable<Boolean> cir) {
        if (this.getWeaponStack().getItem() instanceof LeviAxe) {
            cir.setReturnValue(true);
        }
    }
}
