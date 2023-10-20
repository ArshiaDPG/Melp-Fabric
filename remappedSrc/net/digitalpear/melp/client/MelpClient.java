package net.digitalpear.melp.client;

import net.digitalpear.melp.init.MBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class MelpClient implements ClientModInitializer {
    /**
     * Runs the mod initializer on the client environment.
     */
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(),
                MBlocks.MELP, MBlocks.MELP_NECK, MBlocks.MELP_CROP, MBlocks.DRIED_MELP, MBlocks.POTTED_DRIED_MELP);
    }
}
