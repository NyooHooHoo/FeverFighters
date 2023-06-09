import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SplashScreen extends JPanel {
    private final Timer timer;
    private int alpha;
    private AnimationListener listener;
    private final Font titleFont = new Font("SansSerif", Font.BOLD, 20);
    private final Color bg = new Color(201, 214, 248);
    private final BufferedImage logo = ImageIO.read(new File("assets/logo.png"));

    public SplashScreen() throws IOException {
        alpha = 0;

        timer = new Timer(20, e -> {
            alpha += 3;
            repaint();
        });

        timer.start();
        setVisible(true);
    }

    public void paintComponent(Graphics g) {
        g.setColor(bg);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setFont(titleFont);
        g.setColor(new Color(0, 0, 0, alpha));
        g.drawString("Developed By", 230, 80);

        Graphics2D g2 = (Graphics2D) g.create();
        AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha / 255.0f);
        g2.setComposite(alphaComposite);
        g2.drawImage(logo.getScaledInstance(400, 300, Image.SCALE_DEFAULT), 100, 100, this);

        if (alpha >= 255) {
            timer.stop();
            try {
                listener.loadMenu();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void addAnimationListener(AnimationListener listener) {
        this.listener = listener;
    }
}