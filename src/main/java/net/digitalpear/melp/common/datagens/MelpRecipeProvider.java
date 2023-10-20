package net.digitalpear.melp.common.datagens;

import net.digitalpear.melp.init.MBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeProvider;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;

import java.util.List;

public class MelpRecipeProvider extends FabricRecipeProvider {
    public MelpRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(RecipeExporter exporter) {

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.BONE_MEAL)
                .input(MBlocks.DRIED_MELP)
                .criterion(hasItem(MBlocks.DRIED_MELP), conditionsFromItem(MBlocks.DRIED_MELP))
                .offerTo(exporter, convertBetween(Items.BONE_MEAL, MBlocks.DRIED_MELP));
    }
}
