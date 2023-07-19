package net.entropicequanimity.cultera.networking;

import net.entropicequanimity.cultera.CultivationEraMod;
import net.entropicequanimity.cultera.networking.packet.SmokeBombC2SPacket;
import net.entropicequanimity.cultera.networking.packet.SpiritCollectorSyncS2CPacket;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;

public class ModMessages {
    public static final Identifier SMOKEBOMB_ID = new Identifier(CultivationEraMod.MOD_ID, "smoke_bomb");

    public static final Identifier SPIRIT_COLLECTOR_SYNC_ID = new Identifier(CultivationEraMod.MOD_ID, "spirit_collector_sync");

    public static void registerC2SPackets(){
        ServerPlayNetworking.registerGlobalReceiver(SMOKEBOMB_ID, SmokeBombC2SPacket::receive);
    }
    public static void registerS2CPackets(){
        ClientPlayNetworking.registerGlobalReceiver(SPIRIT_COLLECTOR_SYNC_ID, SpiritCollectorSyncS2CPacket::receive);
    }
}
