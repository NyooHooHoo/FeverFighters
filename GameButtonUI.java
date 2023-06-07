import javax.swing.*;
import javax.swing.plaf.metal.MetalButtonUI;
import java.awt.*;

public class GameButtonUI extends MetalButtonUI {
    private Color pressedColor, unpressedColor;

    public GameButtonUI(Color unpressed, Color pressed) {
        this.pressedColor = pressed;
        this.unpressedColor = unpressed;
    }

//    @Override
//    protected void paintFocus(Graphics g, AbstractButton b, Rectangle viewRect, Rectangle textRect, Rectangle iconRect) {
//        super.paintFocus(g, b, viewRect, textRect, iconRect);
//        g.setColor(unpressedColor);
//        g.fillRect(0, 0, b.getWidth(), b.getHeight());
//    }

    @Override
    protected void paintButtonPressed(Graphics g, AbstractButton b) {
        super.paintButtonPressed(g, b);
        g.setColor(pressedColor);
        g.fillRect(0, 0, b.getWidth(), b.getHeight());
    }
}
