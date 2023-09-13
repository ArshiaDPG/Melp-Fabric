package net.digitalpear.melp.init;

import net.digitalpear.melp.Melp;
import net.digitalpear.melp.common.blocks.MelpBlock;
import net.digitalpear.melp.common.blocks.MelpCropBlock;
import net.digitalpear.melp.common.blocks.MelpNeckBlock;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

public class MBlocks {

    public static BlockItem createBlockItem(String blockID, Block block){
        return Registry.register(Registries.ITEM, new Identifier(Melp.MOD_ID, blockID), new BlockItem(block, new Item.Settings()));
    }

    private static Block createBlockWithItem(String blockID, Block block){
        createBlockItem(blockID, block);
        return Registry.register(Registries.BLOCK, new Identifier(Melp.MOD_ID, blockID), block);
    }

    private static Block createBlockWithoutItem(String blockID, Block block){
        return Registry.register(Registries.BLOCK, new Identifier(Melp.MOD_ID, blockID), block);
    }


    public static final Block MELP = createBlockWithItem("melp", new MelpBlock(AbstractBlock.Settings.copy(Blocks.KELP)
            .mapColor(DyeColor.ORANGE).sounds(BlockSoundGroup.SCULK_VEIN)));
    public static final Block MELP_NECK = createBlockWithoutItem("melp_neck", new MelpNeckBlock(AbstractBlock.Settings.copy(Blocks.KELP_PLANT)
            .mapColor(DyeColor.ORANGE).sounds(BlockSoundGroup.SCULK_VEIN).dropsLike(MELP)));
    public static final Block MELP_CROP = createBlockWithoutItem("melp_crop", new MelpCropBlock(
            AbstractBlock.Settings.copy(Blocks.TORCHFLOWER_CROP)
            .mapColor(state -> state.get(MelpCropBlock.AGE) < MelpCropBlock.MAX_AGE ? MapColor.GREEN : MapColor.ORANGE)
            .sounds(BlockSoundGroup.SCULK_VEIN)
    ));

    public static void init(){

    }

}
