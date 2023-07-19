package net.entropicequanimity.cultera.item;

import net.entropicequanimity.cultera.CultivationEraMod;
import net.entropicequanimity.cultera.item.consumables.SmokeBombItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {
    public static void registerModItems(){
        CultivationEraMod.LOGGER.debug("Registering items for " + CultivationEraMod.MOD_ID);
    }
    public static Item registerItem(String name, Item item){
        return Registry.register(Registry.ITEM, new Identifier(CultivationEraMod.MOD_ID, name), item);
    }

    //Cultivation Items
    public static final Item EMPTY_SPIRIT_STONE = registerItem("empty_spirit_stone", new Item(new FabricItemSettings().group(ModItemGroup.CULTIVATION)));
    public static final Item SPIRIT_STONE_RANK_1 = registerItem("spirit_stone_rank_1", new Item(new FabricItemSettings().group(ModItemGroup.CULTIVATION)));

    //Trinkets and Tools
    public static final Item SPATIAL_BAG = registerItem("spatial_bag_small", new Item(new FabricItemSettings().group(ModItemGroup.CULTIVATION).maxCount(1)));

    ///Miscellaneous Items and Tools
    public static final Item SMOKE_BOMB = registerItem("smoke_bomb", new SmokeBombItem(new FabricItemSettings().group(ModItemGroup.CULTIVATION).maxCount(16)));
}
