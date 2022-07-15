package net.entropicequanimity.cultivationera.item.consumables;

import net.entropicequanimity.cultivationera.particle.ModParticles;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;

public class SmokeBombItem extends Item {
    public SmokeBombItem(Settings settings){
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        double x = user.getX();
        double y = user.getY();
        double z = user.getZ();

        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound(x, y, z, SoundEvents.ENTITY_ENDER_PEARL_THROW, SoundCategory.NEUTRAL, 0.3f, 0.4f / (world.getRandom().nextFloat() * 0.4f + 0.8f), false);
        world.playSound(x, y, z, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 1.0f, (1.0f + (world.random.nextFloat() - world.random.nextFloat()) * 0.2f) * 0.7f, false);
        user.getItemCooldownManager().set(this, 100);
        if (!user.getAbilities().creativeMode) {
            itemStack.decrement(1);
        }
        if(!world.isClient){
            spawnSmoke(world, user, x, y, z);
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 40, 4));

            List<HostileEntity> enemies = getNearbyHostiles(world, user, 3f);
            for (HostileEntity enemy : enemies ) {
                enemy.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 60));
                enemy.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 60));
            }
        }
        return super.use(world, user, hand);
    }

    private void spawnSmoke(World world, PlayerEntity user, double x, double y, double z){
        if (world instanceof ServerWorld) {
            ((ServerWorld)world).spawnParticles(ModParticles.SMOKE_BOMB_SMOKE, x, y, z, 500, 2, 2, 2, 1D);
            ((ServerWorld)world).spawnParticles(ParticleTypes.EXPLOSION, x, y, z, 50, 2, 2, 2, 0.0D);
        }
    }
    private List<HostileEntity> getNearbyHostiles(World world, PlayerEntity user, float boxSize){
        if(world instanceof ServerWorld){
            return world.getEntitiesByClass(HostileEntity.class, new Box(user.getBlockPos()).expand(boxSize), entity -> entity != null && entity.isAlive());
        }
        return null;
    }
}
