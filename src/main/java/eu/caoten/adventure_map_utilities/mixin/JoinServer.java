package eu.caoten.adventure_map_utilities.mixin;

import eu.caoten.adventure_map_utilities.Main;
import eu.caoten.adventure_map_utilities.config.AMUScreen;
import eu.caoten.adventure_map_utilities.config.Config;
import eu.caoten.adventure_map_utilities.event.ClientReceiveMessageEvent;
import eu.caoten.adventure_map_utilities.event.KeyInputHandler;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.GameJoinS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Mixin(ClientPlayNetworkHandler.class)
public abstract class JoinServer {

    @Inject(at = @At("TAIL"), method = "onGameJoin")
    public void onGameJoin(GameJoinS2CPacket packet, CallbackInfo ci) {
        Main.ENABLED_KEYBINDINGS = false;
        KeyInputHandler.TEST_1_TESTED = false;
        KeyInputHandler.TEST_1 = true;
        KeyInputHandler.TEST_2_TESTED = false;
        KeyInputHandler.TEST_2 = true;
        KeyInputHandler.TEST_3_TESTED = false;
        KeyInputHandler.TEST_3 = true;
        AMUScreen.TEST_MANUEL = false;
        ClientReceiveMessageEvent.JOIN_CHECK = false;
        if (Config.SEND_CHECK_ON_JOIN) {
            sendJoinMessage();
        }
    }

    @Unique
    private void sendJoinMessage() {
        int delay = 1000;
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleWithFixedDelay(() -> {
            ClientReceiveMessageEvent.JOIN_CHECK = true;
            MinecraftClient.getInstance().player.networkHandler.sendCommand("trigger amu_trigger set 4");
            executorService.shutdown();
        }, delay, delay, TimeUnit.MILLISECONDS);
    }
}
