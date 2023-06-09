import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * The Exit class represents the exit panel displayed when the game is exited.
 * It provides a thank-you message and credits.
 *
 * <h2>Modifications:</h2>
 * June 6: Lauren created content
 * June 7: Aidan added animation and listener
 *
 * <h2>Course Info:</h2>
 * ICS4U0 with V. Krasteva
 *
 * @author Lauren Hewang, Aidan Wang
 * @version 4
 * @date 06/06/2023
 */
public class Exit extends JPanel {
    /**
     * Timer to run animation
     */
    private final Timer timer;
    /**
     * Opacity of components on screen
     */
    private int alpha;
    /**
     * AnimationListener to fire events when animation complete
     */
    private AnimationListener listener;
    /**
     * Font of the "Thank You" text
     */
    private final Font ty = new Font("Serif", Font.BOLD, 55);
    /**
     * Font of the "For Playing" text
     */
    private final Font fp = new Font("SansSerif", Font.BOLD, 30);
    /**
     * Font of the "Fever Fighters" text
     */
    private final Font ff = new Font("Serif", Font.BOLD, 65);
    /**
     * Font of the rest of the body text
     */
    private final Font text = new Font("Monospace", Font.BOLD, 17);
    /**
     * Color of the background
     */
    private final Color blue = new Color(159, 197, 232);
    /**
     * BufferedImage for the company logo
     */
    private final BufferedImage logo = ImageIO.read(new File("assets/logo.png"));

    /**
     * Constructs an Exit panel.
     *
     * @throws IOException if an error occurs while loading the logo image.
     */
    public Exit() throws IOException {
        alpha = 255;

        timer = new Timer(20, e -> {
            alpha -= 3;
            repaint();
        });

        timer.start();
        setVisible(true);
    }

    /**
     * Overrides the paintComponent method to paint the graphics on the panel.
     *
     * @param g the Graphics object used for painting.
     */
    @Override
    public void paintComponent(Graphics g) {
        g.setColor(blue);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(new Color(0, 0, 0, alpha));

        int yPos = 100;
        g.setFont(ty);
        g.drawString("Thank You", 155, yPos);
        g.setFont(fp);
        g.drawString("FOR PLAYING", 190, yPos + 50);
        g.setFont(ff);
        g.drawString("Fever Fighters!", 80, yPos + 120);

        g.setFont(text);
        g.drawString("BRRR Studios", 235, 390);
        g.drawString("Lauren Hewang, Aidan Wang", 175, 410);

        Graphics2D g2 = (Graphics2D) g.create();
        AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha / 255.0f);
        g2.setComposite(alphaComposite);
        g2.drawImage(logo.getScaledInstance(145, 110, Image.SCALE_DEFAULT), 227, 250, this);

        if (alpha <= 0) {
            timer.stop();
            listener.close();
        }
    }

    /**
     * Adds an AnimationListener to the Exit panel.
     *
     * @param listener the AnimationListener to be added.
     */
    public void addAnimationListener(AnimationListener listener) {
        this.listener = listener;
    }
}
