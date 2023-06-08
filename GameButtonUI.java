import javax.swing.*;
import javax.swing.plaf.metal.MetalButtonUI;
import java.awt.*;

public class GameButtonUI extends MetalButtonUI {
    private final Color pressedColor;

    public GameButtonUI(Color pressed) {
        this.pressedColor = pressed;
    }

    @Override
    protected void paintButtonPressed(Graphics g, AbstractButton b) {
        super.paintButtonPressed(g, b);
        g.setColor(pressedColor);
        g.fillRect(0, 0, b.getWidth(), b.getHeight());
    }
}
