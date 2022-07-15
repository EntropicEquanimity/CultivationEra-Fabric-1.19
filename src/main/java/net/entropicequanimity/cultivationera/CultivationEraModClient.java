package net.entropicequanimity.cultivationera;

import net.entropicequanimity.cultivationera.particle.ModParticles;
import net.entropicequanimity.cultivationera.particle.SmokeBombParticle;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;

public class CultivationEraModClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ParticleFactoryRegistry.getInstance().register(ModParticles.SMOKE_BOMB_SMOKE, SmokeBombParticle.Factory::new);
    }
}
