package eu.caoten.adventure_map_utilities;

import eu.caoten.adventure_map_utilities.event.KeyInputHandler;
import net.fabricmc.api.ClientModInitializer;

public class Client implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        KeyInputHandler.register();
    }
}
