package eu.caoten.adventure_map_utilities.config;

import eu.caoten.adventure_map_utilities.Main;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;


public class AMUScreen extends Screen {

    private final Screen parent;
    public static boolean TEST_MANUEL;

    public AMUScreen(Screen parent) {
        super(Text.translatable("options.adventure_map_utilities"));
        this.parent = parent;
    }

    Text key1() {
        if(Config.KEY1)
            return Text.translatable("options.adventure_map_utilities.key1.on");
        else
            return Text.translatable("options.adventure_map_utilities.key1.off");
    }

    Text key2() {
        if(Config.KEY2)
            return Text.translatable("options.adventure_map_utilities.key2.on");
        else
            return Text.translatable("options.adventure_map_utilities.key2.off");
    }

    Text key3() {
        if(Config.KEY3)
            return Text.translatable("options.adventure_map_utilities.key3.on");
        else
            return Text.translatable("options.adventure_map_utilities.key3.off");
    }

    Text automaticdisabling() {
        if(Config.AUTOMATICDISABLING)
            return Text.translatable("options.adventure_map_utilities.automaticdisabling.on");
        else
            return Text.translatable("options.adventure_map_utilities.automaticdisabling.off");
    }

    protected void init() {
        int i = this.width / 2 - 155;
        int j = i + 160;
        int k = this.height / 6 - 12;
        this.addDrawableChild(ButtonWidget.builder(key1(), button -> {
            Config.KEY1 = !Config.KEY1;
            Config.Write();
            button.setMessage(key1());
        }).dimensions(i, k, 150, 20).build());
        this.addDrawableChild(ButtonWidget.builder(key2(), button -> {
            Config.KEY2 = !Config.KEY2;
            Config.Write();
            button.setMessage(key2());
        }).dimensions(j, k, 150, 20).build());
        k += 24;
        this.addDrawableChild(ButtonWidget.builder(key3(), button -> {
            Config.KEY3 = !Config.KEY3;
            Config.Write();
            button.setMessage(key3());
        }).dimensions(i, k, 150, 20).build());

        Text text = Text.translatable("options.adventure_map_utilities.automaticdisabling.tooltip");
        this.addDrawableChild(ButtonWidget.builder(Text.translatable("options.adventure_map_utilities.checkagain"), button -> {
            Config.AUTOMATICDISABLING = !Config.AUTOMATICDISABLING;
            Config.Write();
            button.setMessage(automaticdisabling());
        }).dimensions(j, k, 150, 20).tooltip(Tooltip.of(text)).build());

        Text text2 = Text.translatable("options.adventure_map_utilities.checkagain.tooltip");
        if (this.client.player != null) {
            k += 24;
            this.addDrawableChild(ButtonWidget.builder(automaticdisabling(), button -> {
                client.getNetworkHandler().sendCommand("trigger amu_trigger set 4");
                Main.LOGGER.info("[AMU] Tested for integration!");
                TEST_MANUEL = true;
                client.player.closeScreen();
            }).dimensions(i, k, 150, 20).tooltip(Tooltip.of(text2)).build());
        }
        k += 24;
        this.addDrawableChild(ButtonWidget.builder(ScreenTexts.DONE, button -> {
            this.client.setScreen(this.parent);
        }).dimensions(this.width / 2 - 100, k, 200, 20).build());
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        drawCenteredTextWithShadow(matrices, this.textRenderer, this.title, this.width / 2, 15, 16777215);
        super.render(matrices, mouseX, mouseY, delta);
    }
}
