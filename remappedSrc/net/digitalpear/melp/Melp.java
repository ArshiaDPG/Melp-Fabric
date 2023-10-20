package net.digitalpear.melp;

import net.digitalpear.melp.init.MBlocks;
import net.digitalpear.melp.init.MItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModification;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.VegetationPatchFeature;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;

public class Melp implements ModInitializer {

    public static final String MOD_ID = "melp";
    @Override
    public void onInitialize() {
        MBlocks.init();
        MItems.init();

        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (source.isBuiltin()){
                if (id == LootTables.SNIFFER_DIGGING_GAMEPLAY){
                    tableBuilder.modifyPools(builder -> builder.with(ItemEntry.builder(MItems.MELP_SEED)));
                }
            }
        });
    }
}
