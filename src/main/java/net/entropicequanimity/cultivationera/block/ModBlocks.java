package net.entropicequanimity.cultivationera.block;

import net.entropicequanimity.cultivationera.CultivationEraMod;
import net.entropicequanimity.cultivationera.item.ModItemGroup;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.OreBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.Registry;

public class ModBlocks {
    public static void registerModBlocks() {
        CultivationEraMod.LOGGER.debug("Registering blocks for " + CultivationEraMod.MOD_ID);
    }
    private static Block registerBlock(String name, Block block, ItemGroup tab){
        registerBlockItem(name, block, tab);
        return Registry.register(Registry.BLOCK, new Identifier(CultivationEraMod.MOD_ID, name), block);
    }
    private static void registerBlockItem(String name, Block block, ItemGroup tab){
        Registry.register(Registry.ITEM, new Identifier(CultivationEraMod.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(tab)));
    }

    public static final Block SPIRIT_STONE_ORE = registerBlock("spirit_stone_ore",
            new OreBlock(FabricBlockSettings.of(Material.AMETHYST).hardness(6f).resistance(100f).luminance(7).requiresTool(),
                    UniformIntProvider.create(3,5)), ModItemGroup.CULTIVATION
    );
    public static final Block DEEPSLATE_SPIRIT_STONE_ORE = registerBlock("deepslate_spirit_stone_ore",
            new OreBlock(FabricBlockSettings.of(Material.AMETHYST).hardness(8f).resistance(200f).luminance(5).requiresTool(),
        UniformIntProvider.create(5,7)), ModItemGroup.CULTIVATION
    );
}
