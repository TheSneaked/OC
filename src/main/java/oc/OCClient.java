package oc;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.EntityRenderer;
import oc.entity.ModEntityTypes;
import oc.renderer.LeviathanSwordProjectileRenderer;

public class OCClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        ClientSetup.clientSetup();
        ModelLoadingPlugin.register(new CustomModelLoadingPlugin());
        ClientSetup.registerExtraBakedModels(CustomModelLoadingPlugin.MODELS::add);
        EntityRendererRegistry.register(ModEntityTypes.leviathanSwordProjectileEntityEntityType, LeviathanSwordProjectileRenderer::new);


    }
}
