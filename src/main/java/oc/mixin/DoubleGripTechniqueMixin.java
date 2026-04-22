package oc.mixin;

import oc.item.ModItems;
import oc.item.custom.SchlorpGlorpArmPosing;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;

@Mixin(BipedEntityModel.class)
public class DoubleGripTechniqueMixin {

    @Shadow @Final public ModelPart rightArm;

    @Shadow @Final public ModelPart leftArm;

    @Shadow @Final public ModelPart head;

    @Inject(method = "positionRightArm", at = @At("HEAD"), cancellable = true)
    private void positionCustomRightArm(LivingEntity entity, CallbackInfo ci) {
        if (entity.getMainHandStack().isOf(ModItems.SCHLORP_GLORP)) {
            SchlorpGlorpArmPosing.hold(this.rightArm, this.leftArm, this.head, true);
            ci.cancel();
        }
    }

    @Inject(method = "positionLeftArm", at = @At("HEAD"), cancellable = true)
    private void positionCustomLeftArm(LivingEntity entity, CallbackInfo ci) {
        if (entity.getMainHandStack().isOf(ModItems.SCHLORP_GLORP)) {
            SchlorpGlorpArmPosing.hold(this.rightArm, this.leftArm, this.head, false);
            ci.cancel();
        }
        }
    }
