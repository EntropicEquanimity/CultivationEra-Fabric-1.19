package net.entropicequanimity.cultera;

import net.entropicequanimity.cultera.particle.ModParticles;
import net.entropicequanimity.cultera.particle.SmokeBombParticle;
import net.entropicequanimity.cultera.screen.ModScreenHandlers;
import net.entropicequanimity.cultera.screen.SpiritCollectorScreen;
import net.entropicequanimity.cultera.screen.SpiritCollectorScreenHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class CultivationEraModClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ParticleFactoryRegistry.getInstance().register(ModParticles.SMOKE_BOMB_SMOKE, SmokeBombParticle.Factory::new);

        HandledScreens.register(ModScreenHandlers.SPIRIT_COLLECTOR_BASIC_HANDLER, SpiritCollectorScreen::new);
    }
}
