package net.digitalpear.melp.common.datagens;

import net.digitalpear.melp.init.MBlocks;
import net.digitalpear.melp.init.MItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.util.Identifier;

import java.util.function.BiConsumer;

public class MelpBlockLootTableProvider extends SimpleFabricLootTableProvider {


    public MelpBlockLootTableProvider(FabricDataOutput output) {
        super(output, LootContextTypes.BLOCK);
    }

    @Override
    public void accept(BiConsumer<Identifier, LootTable.Builder> biConsumer) {
        biConsumer.accept(MBlocks.MELP.getLootTableId(), LootTable.builder().pool(LootPool.builder().with(ItemEntry.builder(MBlocks.MELP)).build()));
        biConsumer.accept(MBlocks.MELP_CROP.getLootTableId(), LootTable.builder().pool(LootPool.builder().with(ItemEntry.builder(MItems.MELP_SEED)).build()));
    }
}
