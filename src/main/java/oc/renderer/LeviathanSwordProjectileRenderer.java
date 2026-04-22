package oc.renderer;

import net.minecraft.client.gui.screen.Overlay;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import oc.entity.LeviathanSwordProjectileEntity;

public class LeviathanSwordProjectileRenderer extends EntityRenderer<LeviathanSwordProjectileEntity> {
    private ItemRenderer itemRenderer;
    public LeviathanSwordProjectileRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.itemRenderer = context.getItemRenderer();
    }
//fortnite skibidi balls

    @Override
    public void render(LeviathanSwordProjectileEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
        matrices.push();

        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees( - 90));
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(entity.age*8));

        //sonion im crine
        matrices.scale(2,2,2);
        itemRenderer.renderItem(entity.getItemStack(), ModelTransformationMode.NONE,light, OverlayTexture.DEFAULT_UV,  matrices, vertexConsumers, entity.getWorld(), 0);
        matrices.pop();
    }

    @Override
    public Identifier getTexture(LeviathanSwordProjectileEntity entity) {
        return null;
    }


}
