package net.entropicequanimity.cultivationera;

import net.entropicequanimity.cultivationera.block.ModBlocks;
import net.entropicequanimity.cultivationera.item.ModItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CultivationEraMod implements ModInitializer {
	public static final String MOD_ID = "cultivationera";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
	}
}
