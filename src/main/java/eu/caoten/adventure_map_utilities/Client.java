package eu.caoten.adventure_map_utilities;

import eu.caoten.adventure_map_utilities.event.ClientReceiveMessageEvent;
import eu.caoten.adventure_map_utilities.event.KeyInputHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;

public class Client implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        KeyInputHandler.register();
        ClientReceiveMessageEvents.ALLOW_GAME.register(new ClientReceiveMessageEvent());
    }
}
