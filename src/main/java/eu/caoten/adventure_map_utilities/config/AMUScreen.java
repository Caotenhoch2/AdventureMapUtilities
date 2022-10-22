package eu.caoten.adventure_map_utilities.config;

import eu.caoten.adventure_map_utilities.Main;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;

import java.io.IOException;
import java.util.function.Consumer;


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
        this.addDrawableChild(new ButtonWidget(i, k, 150, 20, key1(), (button) -> {
            Config.KEY1 = !Config.KEY1;
            try {
                Config.Write();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            button.setMessage(key1());
        }));
        this.addDrawableChild(new ButtonWidget(j, k, 150, 20, key2(), (button) -> {
            Config.KEY2 = !Config.KEY2;
            try {
                Config.Write();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            button.setMessage(key2());
        }));
        k += 24;
        this.addDrawableChild(new ButtonWidget(i, k, 150, 20, key3(), (button) -> {
            Config.KEY3 = !Config.KEY3;
            try {
                Config.Write();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            button.setMessage(key3());
        }));
        Text text = Text.translatable("options.adventure_map_utilities.automaticdisabling.tooltip");
        ButtonWidget.TooltipSupplier tooltipSupplier = new ButtonWidget.TooltipSupplier() {
            public void onTooltip(ButtonWidget buttonWidget, MatrixStack matrixStack, int i, int j) {
                AMUScreen.this.renderOrderedTooltip(matrixStack, AMUScreen.this.client.textRenderer.wrapLines(text, Math.max(AMUScreen.this.width / 2 - 43, 170)), i, j);
            }

            public void supply(Consumer<Text> consumer) {
                consumer.accept(text);
            }
        };
        this.addDrawableChild(new ButtonWidget(j, k, 150, 20, automaticdisabling(), (button) -> {
            Config.AUTOMATICDISABLING = !Config.AUTOMATICDISABLING;
            try {
                Config.Write();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            button.setMessage(automaticdisabling());
        }, tooltipSupplier));

        Text text2 = Text.translatable("options.adventure_map_utilities.checkagain.tooltip");
        ButtonWidget.TooltipSupplier tooltipSupplier2 = new ButtonWidget.TooltipSupplier() {
            public void onTooltip(ButtonWidget buttonWidget, MatrixStack matrixStack, int i, int j) {
                AMUScreen.this.renderOrderedTooltip(matrixStack, AMUScreen.this.client.textRenderer.wrapLines(text2, Math.max(AMUScreen.this.width / 2 - 43, 170)), i, j);
            }

            public void supply(Consumer<Text> consumer) {
                consumer.accept(text2);
            }
        };
        if (this.client.player != null) {
            k += 24;
            this.addDrawableChild(new ButtonWidget(i, k, 150, 20, Text.translatable("options.adventure_map_utilities.checkagain"), (button) -> {
                client.player.sendCommand("trigger amu_trigger set 4");
                Main.LOGGER.info("[AMU] Tested for integration!");
                TEST_MANUEL = true;
                client.player.closeScreen();
            }, tooltipSupplier2));
        }
        k += 24;
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, k, 200, 20, ScreenTexts.DONE, (button) -> {
            this.client.setScreen(this.parent);
        }));
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 15, 16777215);
        super.render(matrices, mouseX, mouseY, delta);
    }
}
