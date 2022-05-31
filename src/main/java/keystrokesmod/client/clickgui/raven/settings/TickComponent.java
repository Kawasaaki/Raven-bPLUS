package keystrokesmod.client.clickgui.raven.settings;

import keystrokesmod.client.clickgui.raven.Component;
import keystrokesmod.client.clickgui.raven.ModuleComponent;
import keystrokesmod.client.clickgui.theme.Theme;
import keystrokesmod.client.main.Raven;
import keystrokesmod.client.module.Module;
import keystrokesmod.client.module.setting.impl.TickSetting;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import org.lwjgl.opengl.GL11;

public class TickComponent extends Component {
    private final TickSetting booleanSetting;
    private final ModuleComponent moduleComponent;
    private final Module module;

    public TickComponent(TickSetting booleanSetting, ModuleComponent moduleComponent){
        this.booleanSetting = booleanSetting;
        this.moduleComponent = moduleComponent;
        this.module = moduleComponent.getModule();
    }

    @Override
    public boolean isVisible() {
        return true;
    }

    @Override
    public void paint(FontRenderer fr) {
        Theme theme = Raven.clickGui.getTheme();

        Gui.drawRect(
                (int)this.getX(),
                (int)this.getY(),
                (int)(this.getX() + this.getWidth()),
                (int)(this.getY() + this.getHeight()),
                theme.getSelectionBackgroundColour().getRGB()
        );

        Gui.drawRect(
                (int)this.getX(),
                (int)this.getY(),
                (int)(this.getX() + Raven.clickGui.barWidth),
                (int)(this.getY() + this.getHeight()),
                theme.getAccentColour().getRGB()
        );

        float textMargin = (float)this.getWidth() * 0.0625f;
        double desiredTextSize = this.getHeight() * 0.6;
        double scaleFactor = desiredTextSize/ fr.FONT_HEIGHT;
        double coordFactor = 1/scaleFactor;
        double textY = this.getY() + (this.getHeight() - desiredTextSize) * 0.5;

        int tickSize = (int)(this.getHeight() * 0.6);
        int margin = (int)(tickSize * 0.2);
        int bgHeight = margin * 2 + tickSize;
        int bgWidth = (int)(1.61 * bgHeight);

        int bgX = (int)(this.getX() + this.getWidth() - bgWidth - textMargin);
        int bgY = (int) (this.getY() + (this.getHeight() - bgHeight) / 2);
        int tickY = bgY + margin;
        int tickX = booleanSetting.isToggled() ? (bgX + bgWidth - margin - tickSize) : (bgX + margin);

        Gui.drawRect(
                bgX,
                bgY,
                bgX + bgWidth,
                bgY + bgHeight,
                theme.getSecondBackgroundColour().getRGB()
        );


        Gui.drawRect(
                tickX,
                tickY,
                tickX + tickSize,
                tickY + tickSize,
                booleanSetting.isToggled() ? theme.getAccentColour().getRGB() : theme.getSelectionBackgroundColour().getRGB()
        );

        GL11.glPushMatrix();
        GL11.glScaled(scaleFactor, scaleFactor, scaleFactor);
        fr.drawString(
                booleanSetting.getName(),
                (float)((this.getX() + textMargin) * coordFactor),
                (float)(textY * coordFactor),
                theme.getTextColour().getRGB(),
                false
        );
        GL11.glPopMatrix();

        for(Component component: this.getComponents()){
            component.paint(fr);
        }
    }

    @Override
    public void mouseDown(int x, int y, int mb) {
        if(mouseOver(x, y))
            booleanSetting.toggle();
        super.mouseDown(x, y, mb);
    }
}
