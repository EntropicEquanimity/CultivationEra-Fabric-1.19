package net.entropicequanimity.cultera;

import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;

public class Utils {
    public static List<HostileEntity> getNearbyHostiles(World world, PlayerEntity user, float boxSize){
        if(world instanceof ServerWorld){
            return world.getEntitiesByClass(HostileEntity.class, new Box(user.getBlockPos()).expand(boxSize), entity -> entity != null && entity.isAlive());
        }
        return null;
    }
    public static List<PlayerEntity> getNearbyPlayers(World world, PlayerEntity user, float boxSize){
        if(world instanceof ServerWorld){
            return world.getEntitiesByClass(PlayerEntity.class, new Box(user.getBlockPos()).expand(boxSize), entity -> entity != null && entity.isAlive());
        }
        return null;
    }
}
