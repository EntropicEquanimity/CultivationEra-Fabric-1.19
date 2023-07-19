package net.entropicequanimity.cultera.particle;

import net.entropicequanimity.cultera.CultivationEraMod;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModParticles {
    public static final DefaultParticleType SMOKE_BOMB_SMOKE = FabricParticleTypes.simple();

    public static void registerModParticles(){
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(CultivationEraMod.MOD_ID, "smoke_bomb_smoke"), SMOKE_BOMB_SMOKE);
    }
}
