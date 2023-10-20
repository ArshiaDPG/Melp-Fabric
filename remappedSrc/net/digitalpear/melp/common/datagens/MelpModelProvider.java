package net.digitalpear.melp.common.datagens;

import net.digitalpear.melp.common.blocks.MelpCropBlock;
import net.digitalpear.melp.common.blocks.MelpNeckBlock;
import net.digitalpear.melp.init.MBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.*;
import net.minecraft.util.Identifier;

public class MelpModelProvider extends FabricModelProvider {


    public MelpModelProvider(FabricDataOutput output) {
        super(output);
    }

    public final void createMelpPlantBlock(BlockStateModelGenerator blockStateModelGenerator, Block block) {
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(block).coordinate(BlockStateVariantMap.create(MelpNeckBlock.FLOWERING).register(aBoolean -> {

            String name = aBoolean ? "_flowering" : "";
            TextureMap textureMapping = TextureMap.of(TextureKey.CROSS, TextureMap.getSubId(block, name));

            Identifier resourceLocation = BlockStateModelGenerator.TintType.NOT_TINTED.getCrossModel().upload(block, name, textureMapping, blockStateModelGenerator.modelCollector);

            return BlockStateVariant.create().put(VariantSettings.MODEL, resourceLocation);
        })));
    }



    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.excludeFromSimpleItemModelGeneration(MBlocks.MELP);
        createMelpPlantBlock(blockStateModelGenerator, MBlocks.MELP);
        blockStateModelGenerator.registerItemModel(MBlocks.MELP);

        createMelpPlantBlock(blockStateModelGenerator, MBlocks.MELP_NECK);

        blockStateModelGenerator.registerFlowerPotPlant(MBlocks.DRIED_MELP, MBlocks.POTTED_DRIED_MELP, BlockStateModelGenerator.TintType.NOT_TINTED);


        blockStateModelGenerator.registerTintableCrossBlockStateWithStages(MBlocks.MELP_CROP, BlockStateModelGenerator.TintType.NOT_TINTED, MelpCropBlock.AGE, 0, 1, 2);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

    }
}
