package oc;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;

public class OCClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        ClientSetup.clientSetup();
        ModelLoadingPlugin.register(new CustomModelLoadingPlugin());
        ClientSetup.registerExtraBakedModels(CustomModelLoadingPlugin.MODELS::add);

    }
}
