package eu.caoten.adventure_map_utilities.event;

import eu.caoten.adventure_map_utilities.Main;
import eu.caoten.adventure_map_utilities.config.Config;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;

public class KeyInputHandler {
    public static final String KEY_CATEGORY_AMU = "key.category.adventure_map_utilities.amu";
    public static final String KEY_1 = "key.adventure_map_utilities.1";
    public static final String KEY_2 = "key.adventure_map_utilities.2";
    public static final String KEY_3 = "key.adventure_map_utilities.3";

    public static boolean TEST_1 = true;
    public static boolean TEST_1_TESTED = false;
    public static boolean TEST_2 = true;
    public static boolean TEST_2_TESTED = false;
    public static boolean TEST_3 = true;
    public static boolean TEST_3_TESTED = false;

    public static KeyBinding key1;
    public static KeyBinding key2;
    public static KeyBinding key3;

    public static void registerKeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if(key1.wasPressed() && Config.KEY1) {
                testFurther(client, 1);
            }
            if(key2.wasPressed() && Config.KEY2) {
                testFurther(client, 2);
            }
            if(key3.wasPressed() && Config.KEY3) {
                testFurther(client, 3);
            }
        });
    }

    public static void register() {
        key1 = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_1,
                InputUtil.Type.KEYSYM,
                82,
                KEY_CATEGORY_AMU
        ));
        key2 = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_2,
                InputUtil.Type.KEYSYM,
                -1,
                KEY_CATEGORY_AMU
        ));
        key3 = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_3,
                InputUtil.Type.KEYSYM,
                -1,
                KEY_CATEGORY_AMU
        ));

        registerKeyInputs();
    }

    public static void testFurther(MinecraftClient client, int keybind) {
        if (keybind == 1) {
            if (Config.AUTOMATICDISABLING) {
                if (Main.ENABLED_KEYBINDINGS) {
                    client.player.sendCommand("trigger amu_trigger set 1");
                }
                else {
                    if (TEST_1) {
                        client.player.sendCommand("trigger amu_trigger set 4");
                        Main.LOGGER.info("[AMU] Tested for integration!");
                        TEST_1_TESTED = true;
                        TEST_1 = false;
                        TEST_2 = false;
                        TEST_3 = false;
                    }
                    else {
                        client.player.sendMessage(Text.translatable("message.adventure_map_utilities.nointegration"));
                    }
                }
            }
            else {
                client.player.sendCommand("trigger amu_trigger set 1");
            }
        }
        if (keybind == 2) {
            if (Config.AUTOMATICDISABLING) {
                if (Main.ENABLED_KEYBINDINGS) {
                    client.player.sendCommand("trigger amu_trigger set 2");
                }
                else {
                    if (TEST_2) {
                        client.player.sendCommand("trigger amu_trigger set 4");
                        Main.LOGGER.info("[AMU] Tested for integration!");
                        TEST_2_TESTED = true;
                        TEST_1 = false;
                        TEST_2 = false;
                        TEST_3 = false;
                    }
                    else {
                        client.player.sendMessage(Text.translatable("message.adventure_map_utilities.nointegration"));
                    }
                }
            }
            else {
                client.player.sendCommand("trigger amu_trigger set 2");
            }
        }
        if (keybind == 3) {
            if (Config.AUTOMATICDISABLING) {
                if (Main.ENABLED_KEYBINDINGS) {
                    client.player.sendCommand("trigger amu_trigger set 3");
                }
                else {
                    if (TEST_3) {
                        client.player.sendCommand("trigger amu_trigger set 4");
                        Main.LOGGER.info("[AMU] Tested for integration!");
                        TEST_3_TESTED = true;
                        TEST_1 = false;
                        TEST_2 = false;
                        TEST_3 = false;
                    }
                    else {
                        client.player.sendMessage(Text.translatable("message.adventure_map_utilities.nointegration"));
                    }
                }
            }
            else {
                client.player.sendCommand("trigger amu_trigger set 3");
            }
        }
    }
}
