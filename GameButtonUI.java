import javax.swing.*;
import javax.swing.plaf.metal.MetalButtonUI;
import java.awt.*;

/**
 * The GameButtonUI class is a custom button UI that provides a specific appearance for game buttons.
 * It extends the MetalButtonUI class from the Java Swing framework.
 *
 * <h2>Modifications:</h2>
 * June 7: Aidan created class
 *
 * <h2>Time Spent:</h2>
 * less than 1 hour
 *
 * <h2>Course Info:</h2>
 * ICS4U0 with V. Krasteva
 *
 * @author Aidan Wang
 * @version 4
 */
public class GameButtonUI extends MetalButtonUI {
    /**
     * Color of the button when pressed
     */
    private final Color pressedColor;

    /**
     * Constructs a GameButtonUI with the specified pressed color.
     *
     * @param pressed the color to be used when the button is pressed.
     */
    public GameButtonUI(Color pressed) {
        this.pressedColor = pressed;
    }

    /**
     * Overrides the paintButtonPressed method to customize the appearance of the button when it is pressed.
     *
     * @param g the Graphics object used for painting.
     * @param b the AbstractButton being painted.
     */
    @Override
    protected void paintButtonPressed(Graphics g, AbstractButton b) {
        super.paintButtonPressed(g, b);
        g.setColor(pressedColor);
        g.fillRect(0, 0, b.getWidth(), b.getHeight());
    }
}
