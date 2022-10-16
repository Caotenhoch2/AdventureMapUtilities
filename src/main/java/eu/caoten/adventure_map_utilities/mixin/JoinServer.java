package eu.caoten.adventure_map_utilities.mixin;

import eu.caoten.adventure_map_utilities.Main;
import eu.caoten.adventure_map_utilities.config.AMUScreen;
import eu.caoten.adventure_map_utilities.event.KeyInputHandler;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.GameJoinS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class JoinServer {
    @Inject(at = @At("RETURN"), method = "onGameJoin")
    public void onGameJoin(GameJoinS2CPacket packet, CallbackInfo ci) {
        Main.ENABLED_KEYBINDINGS = false;
        KeyInputHandler.TEST_1_TESTED = false;
        KeyInputHandler.TEST_1 = true;
        KeyInputHandler.TEST_2_TESTED = false;
        KeyInputHandler.TEST_2 = true;
        KeyInputHandler.TEST_3_TESTED = false;
        KeyInputHandler.TEST_3 = true;
        AMUScreen.TEST_MANUEL = false;
    }
}
