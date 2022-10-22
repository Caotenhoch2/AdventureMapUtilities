package eu.caoten.adventure_map_utilities.mixin;

import eu.caoten.adventure_map_utilities.Main;
import eu.caoten.adventure_map_utilities.config.AMUScreen;
import eu.caoten.adventure_map_utilities.event.KeyInputHandler;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.message.MessageHandler;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MessageHandler.class)
public class TestMessage {

    @Shadow @Final private MinecraftClient client;

    @Inject(at = @At("HEAD"), method = "onGameMessage")
    public void onGameMessage(Text message, boolean overlay, CallbackInfo ci) {
        if (message.contains(Text.literal("[AMU] Integration found!"))) {
            if (KeyInputHandler.TEST_1_TESTED) {
                KeyInputHandler.TEST_1_TESTED = false;
                client.player.sendCommand("trigger amu_trigger set 1");
                Main.ENABLED_KEYBINDINGS = true;
            }
            else if (KeyInputHandler.TEST_2_TESTED) {
                KeyInputHandler.TEST_2_TESTED = false;
                client.player.sendCommand("trigger amu_trigger set 2");
                Main.ENABLED_KEYBINDINGS = true;
            }
            else if (KeyInputHandler.TEST_3_TESTED) {
                KeyInputHandler.TEST_3_TESTED = false;
                client.player.sendCommand("trigger amu_trigger set 3");
                Main.ENABLED_KEYBINDINGS = true;
            }
            else if (AMUScreen.TEST_MANUEL) {
                Main.ENABLED_KEYBINDINGS = true;
                AMUScreen.TEST_MANUEL = false;
            }
            else {
                Main.LOGGER.error("Received integration message but does not know what to do!");
            }
        }
    }
}
