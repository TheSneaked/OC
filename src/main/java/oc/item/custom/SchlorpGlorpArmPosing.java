package oc.item.custom;

import net.minecraft.client.model.ModelPart;

public class SchlorpGlorpArmPosing {
    public static void hold(ModelPart holdingArm, ModelPart otherArm, ModelPart head, boolean rightArmed) {
        ModelPart modelPart = rightArmed ? holdingArm : otherArm;
        ModelPart modelPart2 = rightArmed ? otherArm : holdingArm;

        modelPart.yaw = (float) Math.toRadians(-40f);
        modelPart2.yaw = (float) Math.toRadians(40f);
        modelPart.roll  = (float) Math.toRadians(-20f);
        modelPart2.roll = (float) Math.toRadians(0f);

        modelPart.pitch = (float) Math.toRadians(-55f);
        modelPart2.pitch = (float) Math.toRadians(-55f);
    }
}
