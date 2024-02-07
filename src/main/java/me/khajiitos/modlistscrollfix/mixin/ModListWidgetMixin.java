package me.khajiitos.modlistscrollfix.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.ObjectSelectionList;
import net.minecraftforge.client.gui.widget.ModListWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ModListWidget.class)
public abstract class ModListWidgetMixin extends ObjectSelectionList<ModListWidget.ModEntry> {

    public ModListWidgetMixin(Minecraft minecraft, int a, int b, int c, int d, int e) {
        super(minecraft, a, b, c, d, e);
    }

    @Inject(at = @At("TAIL"), method = "refreshList", remap = false)
    public void onReloadMods(CallbackInfo ci) {
        // setScrollAmount clamps its input between 0 and max scroll
        // giving us a nice one liner :)
        this.setScrollAmount(this.getScrollAmount());
    }
}
