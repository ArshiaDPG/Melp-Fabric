package net.digitalpear.melp.common.datagens;

import net.digitalpear.melp.common.blocks.MelpNeckBlock;
import net.digitalpear.melp.init.MBlocks;
import net.digitalpear.melp.init.MItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.util.Identifier;

import java.util.function.BiConsumer;

public class MelpBlockLootTableProvider extends FabricBlockLootTableProvider {


    public MelpBlockLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {

        addDrop(MBlocks.MELP);

        addDrop(MBlocks.MELP_NECK, LootTable.builder().pool(LootPool.builder().with(ItemEntry.builder(MItems.MELP_SEED))
                .conditionally(BlockStatePropertyLootCondition.builder(MBlocks.MELP_NECK)
                        .properties(StatePredicate.Builder.create().exactMatch(MelpNeckBlock.FLOWERING, true))).build()));

        addDrop(MBlocks.MELP_CROP, MItems.MELP_SEED);

        addDrop(MBlocks.DRIED_MELP);

        addDrop(MBlocks.POTTED_DRIED_MELP, pottedPlantDrops(MBlocks.DRIED_MELP));
    }
}
