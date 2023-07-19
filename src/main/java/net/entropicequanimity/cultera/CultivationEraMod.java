package net.entropicequanimity.cultera;

import net.entropicequanimity.cultera.block.ModBlocks;
import net.entropicequanimity.cultera.block.entity.ModBlockEntities;
import net.entropicequanimity.cultera.item.ModItems;
import net.entropicequanimity.cultera.particle.ModParticles;
import net.entropicequanimity.cultera.screen.ModScreenHandlers;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CultivationEraMod implements ModInitializer {
	public static final String MOD_ID = "cultera";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModBlockEntities.registerAllBlockEntities();
		ModScreenHandlers.registerAllScreenHandlers();

		ModParticles.registerModParticles();
	}
}
