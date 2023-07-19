package net.entropicequanimity.cultera.item.consumables;

import net.entropicequanimity.cultera.Utils;
import net.entropicequanimity.cultera.particle.ModParticles;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
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
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

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
        //world.playSound(x, y, z, SoundEvents.ENTITY_ENDER_PEARL_THROW, SoundCategory.NEUTRAL, 0.3f, 0.4f / (world.getRandom().nextFloat() * 0.4f + 0.8f), false);
        world.playSound(x, y, z, SoundEvents.ENTITY_CHICKEN_EGG, SoundCategory.PLAYERS, 0.5f, (0.01f), true);
        world.playSound(x, y, z, SoundEvents.ENTITY_ALLAY_ITEM_THROWN, SoundCategory.PLAYERS, 0.5f, (0.01f), true);
        world.playSound(x, y, z, SoundEvents.ENTITY_ARMOR_STAND_FALL, SoundCategory.PLAYERS, 1.0f, (1.0f), true);
        user.getItemCooldownManager().set(this, 100);
        if (!user.getAbilities().creativeMode) {
            itemStack.decrement(1);
        }
        if(!world.isClient){
            spawnSmoke(world, user, x, y, z);
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 80, 4));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, 80));

            List<HostileEntity> enemies = Utils.getNearbyHostiles(world, user, 4f);
            for (HostileEntity enemy : enemies ) {
                enemy.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 60));
                enemy.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 60));
            }
            List<PlayerEntity> nearbyPlayers = Utils.getNearbyPlayers(world, user, 6f);
            for (PlayerEntity player : nearbyPlayers){
                if(player != user){
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 60));
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 60));
                }
            }
        }
        return super.use(world, user, hand);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if(Screen.hasShiftDown()){
            tooltip.add(Text.translatable("item.cultera.smoke_bomb_tooltip_line1").formatted((Formatting.AQUA)));
            tooltip.add(Text.translatable("item.cultera.smoke_bomb_tooltip_line2").formatted((Formatting.AQUA)));
        }
        else{
            tooltip.add(Text.literal("Hold Shift").formatted(Formatting.YELLOW));
        }
    }

    private void spawnSmoke(World world, PlayerEntity user, double x, double y, double z){
        if (world instanceof ServerWorld) {
            ((ServerWorld)world).spawnParticles(ModParticles.SMOKE_BOMB_SMOKE, x, y, z, 500, 3, 3, 3, 1D);
            ((ServerWorld)world).spawnParticles(ParticleTypes.EXPLOSION, x, y, z, 50, 2, 2, 2, 0.0D);
        }
    }
}
