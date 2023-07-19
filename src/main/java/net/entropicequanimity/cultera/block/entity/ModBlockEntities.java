package net.entropicequanimity.cultera.block.entity;

import net.entropicequanimity.cultera.CultivationEraMod;
import net.entropicequanimity.cultera.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlockEntities {
    public static BlockEntityType<SpiritCollectorBasicBlockEntity> SPIRIT_COLLECTOR_ENTITY_BASIC;

    public static void registerAllBlockEntities(){
        SPIRIT_COLLECTOR_ENTITY_BASIC = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(CultivationEraMod.MOD_ID, "spirit_collector_basic"),
                FabricBlockEntityTypeBuilder.create(SpiritCollectorBasicBlockEntity::new, ModBlocks.SPIRIT_COLLECTOR_BASIC).build(null));
    }
}
