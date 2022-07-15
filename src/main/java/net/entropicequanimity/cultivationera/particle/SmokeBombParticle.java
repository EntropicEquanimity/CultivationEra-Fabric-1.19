package net.entropicequanimity.cultivationera.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.math.random.Random;
import org.jetbrains.annotations.Nullable;

public class SmokeBombParticle extends SpriteBillboardParticle {

    protected SmokeBombParticle(ClientWorld clientWorld, double x, double y, double z, SpriteProvider spriteSet, double xd, double yd, double zd) {
        super(clientWorld, x, y, z, xd, yd, zd);
        this.velocityMultiplier = 0.2f;
        this.x = xd;
        this.y = yd;
        this.z = zd;
        this.maxAge = clientWorld.random.nextBetween(120, 180);
        this.setSprite(spriteSet.getSprite(clientWorld.random));
        this.collidesWithWorld = false;
        this.scale = clientWorld.random.nextFloat() + 3f;
        this.age = clientWorld.random.nextBetween(0, 20);
    }

    @Override
    public void tick(){
        super.tick();
        fadeOut();
        scaleDown();
    }
    private void fadeOut() {
        this.alpha = (-(0.5f/(float)maxAge) * age + 1);
    }
    private void scaleDown(){
        this.scale *= 0.995f;
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<DefaultParticleType>{
        private final SpriteProvider sprites;
        public Factory(SpriteProvider spriteSet){
            this.sprites = spriteSet;
        }
        public Particle createParticle(DefaultParticleType particleType, ClientWorld clientWorld, double x, double y, double z,
                                       double xd, double yd, double zd){
            return new SmokeBombParticle(clientWorld, x, y, z, this.sprites, xd, yd, zd);
        }
    }
}
