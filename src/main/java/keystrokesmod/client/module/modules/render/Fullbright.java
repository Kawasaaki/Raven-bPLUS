package keystrokesmod.client.module.modules.render;

import keystrokesmod.client.module.Module;
import keystrokesmod.client.module.DescriptionSetting;
import keystrokesmod.client.utils.Utils;
import keystrokesmod.client.lib.fr.jmraich.rax.event.FMLEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class Fullbright extends Module {
    private float defaultGamma;
    private final float clientGamma;

    public Fullbright() {
        super("Fullbright", category.render, 0);

        DescriptionSetting description;
        this.registerSetting(description = new DescriptionSetting("No more darkness!"));
        this.clientGamma = 10000;
    }

    @Override
    public void onEnable() {
        this.defaultGamma = mc.gameSettings.gammaSetting;
        super.onEnable();
    }

    @Override
    public void onDisable(){
        super.onEnable();
        mc.gameSettings.gammaSetting = this.defaultGamma;
    }

    @FMLEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent e) {
        if (!Utils.Player.isPlayerInGame()) {
            onDisable();
            return;
        }

        if (mc.gameSettings.gammaSetting != clientGamma)
            mc.gameSettings.gammaSetting = clientGamma;
    }
}