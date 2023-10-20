package net.digitalpear.melp;

import net.digitalpear.melp.common.datagens.MelpBlockLootTableProvider;
import net.digitalpear.melp.common.datagens.MelpLanguageProvider;
import net.digitalpear.melp.common.datagens.MelpModelProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class MelpDatagen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(MelpModelProvider::new);
        pack.addProvider(MelpBlockLootTableProvider::new);
        pack.addProvider(MelpLanguageProvider::new);
    }
}
