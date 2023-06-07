import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JComponent;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SplashScreen {
    Font titleFont = new Font("SansSerif", Font.BOLD, 20);
    BufferedImage logo = ImageIO.read(new File("assets/logo.png"));

        Color bg = new Color(201, 214, 248);
        Color logoBlue = new Color(10, 122, 199);
        public SplashScreen() throws IOException {
            JFrame frame = new JFrame("Splash Screen");
            frame.setSize(600, 500);
            frame.add(new Drawing());
            frame.setVisible(true);
        }
        public static void main(String[] args) throws IOException {
            new SplashScreen();
        }
        class Drawing extends JComponent{
            public void paint(Graphics g){
                g.setColor(bg);
                g.fillRect(0, 0, 600, 500);
                g.setFont(titleFont);
                g.setColor(Color.BLACK);
                g.drawString("Developed By", 230, 80);

            g.setColor(logoBlue);
            g.fillArc(180, 135, 240, 210 , 0, 180);

            g.setColor(logoBlue);
            g.drawImage(logo.getScaledInstance(400, 300, Image.SCALE_DEFAULT), 100, 100, this);
        }
    }
}