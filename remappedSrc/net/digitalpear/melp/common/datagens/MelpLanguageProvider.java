package net.digitalpear.melp.common.datagens;

import net.digitalpear.melp.init.MBlocks;
import net.digitalpear.melp.init.MItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

public class MelpLanguageProvider extends FabricLanguageProvider {
    public MelpLanguageProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        translationBuilder.add(MItems.MELP_SEED, "Melp Seed");
        translationBuilder.add(MBlocks.MELP_CROP, "Melp Crop");
        translationBuilder.add(MBlocks.MELP, "Melp");
        translationBuilder.add(MBlocks.MELP_NECK, "Melp");
        translationBuilder.add(MBlocks.DRIED_MELP, "Dried Melp");
    }
}
