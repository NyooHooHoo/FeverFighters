import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The SplashScreen class represents a panel used for displaying a splash screen with a fading animation.
 * It extends the JPanel class.
 *
 * <h2>Modifications</h2>
 * May 23: Lauren created class with Colors and text, drew logo
 * June 5: Replaced logo with scaled image
 * June 7: Aidan added animation and listeners
 *
 * <h2>Time Spent:</h2>
 * 1 hour
 *
 * <h2>Course Info:</h2>
 * ICS4U0 with V. Krasteva
 *
 * @author Lauren Hewang, Aidan Wang
 * @version 2
 */
public class SplashScreen extends JPanel {
    /**
     * Timer to control animation
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
     * Font for the title
     */
    private final Font titleFont = new Font("SansSerif", Font.BOLD, 20);
    /**
     * Color for the background
     */
    private final Color bg = new Color(201, 214, 248);
    /**
     * BufferedImage for the company logo
     */
    private final BufferedImage logo;

    /**
     * Constructs a SplashScreen panel.
     *
     * @throws IOException if an I/O error occurs during image loading
     */
    public SplashScreen() throws IOException {
        alpha = 0;
        timer = new Timer(20, e -> {
            alpha += 3;
            repaint();
        });
        timer.start();

        // Load the logo image
        logo = ImageIO.read(new File("assets/logo.png"));

        setVisible(true);
    }

    /**
     * Overrides the paintComponent method to paint the splash screen.
     *
     * @param g the Graphics object used for painting
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw background
        g.setColor(bg);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Draw title with fading alpha
        g.setFont(titleFont);
        g.setColor(new Color(0, 0, 0, alpha));
        g.drawString("Developed By", 230, 80);

        // Draw logo with fading alpha
        Graphics2D g2 = (Graphics2D) g.create();
        AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha / 255.0f);
        g2.setComposite(alphaComposite);
        g2.drawImage(logo.getScaledInstance(400, 300, Image.SCALE_DEFAULT), 100, 100, this);

        // Stop the timer and load the menu when the alpha reaches 255
        if (alpha >= 255) {
            timer.stop();
            try {
                listener.loadMenu();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Adds an AnimationListener to the SplashScreen panel.
     *
     * @param listener the AnimationListener to add
     */
    public void addAnimationListener(AnimationListener listener) {
        this.listener = listener;
    }
}
