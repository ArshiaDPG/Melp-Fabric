package net.digitalpear.melp.init;

import net.digitalpear.melp.Melp;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class MItems {

    public static Item createItem(String blockID, Item item){
        return Registry.register(Registries.ITEM, new Identifier(Melp.MOD_ID, blockID), item);
    }

    public static final Item MELP_SEED = createItem("melp_seed", new AliasedBlockItem(MBlocks.MELP_CROP, new Item.Settings()));


    public static void init(){
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {
            entries.addAfter(Items.PITCHER_POD, MELP_SEED);
            entries.addAfter(Items.PITCHER_PLANT, MBlocks.MELP);
        });

        CompostingChanceRegistry.INSTANCE.add(MBlocks.MELP, 0.6f);
        CompostingChanceRegistry.INSTANCE.add(MItems.MELP_SEED, 0.3f);
    }
}
