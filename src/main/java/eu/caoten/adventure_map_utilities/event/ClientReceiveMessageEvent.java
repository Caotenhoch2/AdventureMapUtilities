package eu.caoten.adventure_map_utilities.event;

import eu.caoten.adventure_map_utilities.Main;
import eu.caoten.adventure_map_utilities.config.AMUScreen;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class ClientReceiveMessageEvent implements ClientReceiveMessageEvents.AllowGame{

    @Override
    public boolean allowReceiveGameMessage(Text message, boolean overlay) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (message.contains(Text.literal("[AMU] Integration found!"))) {
            if (KeyInputHandler.TEST_1_TESTED) {
                KeyInputHandler.TEST_1_TESTED = false;
                client.getNetworkHandler().sendCommand("trigger amu_trigger set 1");
                Main.ENABLED_KEYBINDINGS = true;
            }
            else if (KeyInputHandler.TEST_2_TESTED) {
                KeyInputHandler.TEST_2_TESTED = false;
                client.getNetworkHandler().sendCommand("trigger amu_trigger set 2");
                Main.ENABLED_KEYBINDINGS = true;
            }
            else if (KeyInputHandler.TEST_3_TESTED) {
                KeyInputHandler.TEST_3_TESTED = false;
                client.getNetworkHandler().sendCommand("trigger amu_trigger set 3");
                Main.ENABLED_KEYBINDINGS = true;
            }
            else if (AMUScreen.TEST_MANUEL) {
                Main.ENABLED_KEYBINDINGS = true;
                AMUScreen.TEST_MANUEL = false;
            }
            else {
                Main.LOGGER.warn("[AMU] Received integration message but does not know what to do!");
            }
            return false;
        }
        if (message.contains(Text.translatable("arguments.objective.notFound", "amu_trigger"))) {
            Main.ENABLED_KEYBINDINGS = false;
            client.player.sendMessage(Text.translatable("message.adventure_map_utilities.nointegrationfound"));
            return false;
        }
        return true;
    }
}
