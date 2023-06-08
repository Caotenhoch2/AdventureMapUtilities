package eu.caoten.adventure_map_utilities.mixin;

import eu.caoten.adventure_map_utilities.config.AMUScreen;
import eu.caoten.adventure_map_utilities.config.Config;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(OptionsScreen.class)
public class OptionButton extends Screen {
    protected OptionButton(Text title) {
        super(title);
    }

    @Inject(at = @At("HEAD"), method = "init")
    public void init(CallbackInfo ci) {
        if (Config.SHOW_AMUBUTTON) {
            this.addDrawableChild(ButtonWidget.builder(Text.translatable("options.adventure_map_utilities.button"), button -> {
                this.client.setScreen(new AMUScreen(this));
            }).dimensions(this.width / 2 + 164, this.height / 6 - 12, 50, 20).build());
        }
    }
}
