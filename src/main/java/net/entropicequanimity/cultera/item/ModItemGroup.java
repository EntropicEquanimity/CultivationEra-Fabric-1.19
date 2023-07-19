package net.entropicequanimity.cultera.item;

import net.entropicequanimity.cultera.CultivationEraMod;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ModItemGroup {
    public static final ItemGroup CULTIVATION = FabricItemGroupBuilder.build(new Identifier(CultivationEraMod.MOD_ID,"cultivation_itemgroup"),
            ()-> new ItemStack(ModItems.SPIRIT_STONE_RANK_1));
}
