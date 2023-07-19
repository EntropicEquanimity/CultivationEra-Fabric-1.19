package net.entropicequanimity.cultera.screen;

import net.minecraft.screen.ScreenHandlerType;

public class ModScreenHandlers {
    public static ScreenHandlerType<SpiritCollectorScreenHandler> SPIRIT_COLLECTOR_BASIC_HANDLER;

    public static void registerAllScreenHandlers(){
        SPIRIT_COLLECTOR_BASIC_HANDLER = new ScreenHandlerType<>(SpiritCollectorScreenHandler::new);
    }
}
