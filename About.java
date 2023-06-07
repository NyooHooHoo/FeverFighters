import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JComponent;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class About {
    Font titleFont = new Font("SansSerif", Font.BOLD, 50);
    Font text = new Font("Monospace", Font.PLAIN, 15);

    Color pink = new Color(255, 200, 221);
    Color lightBlue = new Color(189, 224, 254);
    BufferedImage logo = ImageIO.read(new File("assets/logo.png"));

    public About() throws IOException {
        JFrame frame = new JFrame("About");
        frame.setSize(600, 500);
        frame.add(new Drawing());
        frame.setVisible(true);
    }
    public static void main(String[] args) throws IOException {
        new About();
    }
    class Drawing extends JComponent{
        public void paint(Graphics g){
            g.setColor(pink);
            g.fillRect(0, 0, 1200, 800);
            g.setFont(titleFont);
            g.setColor(Color.BLACK);
            g.drawString("About", 220, 90);

            g.setColor(lightBlue);
            g.fillRect(80, 135, 430, 250);
            g.setColor(Color.BLACK);
            g.drawRect(80, 135, 430, 250);

            g.setFont(text);
            int xPos = 275;
            int yPos = 170;
            g.drawString("Fever Fighters is a game", xPos, yPos);
            String[] lines = new String[8];
            lines[0] = "Fever Fighters is a game";
            lines[1] = "developed by BRRR Studios in";
            lines[2] = "cooperation with VK Enterprises.";
            lines[3] = "The development team was";
            lines[4] = "overseen by project leader Lauren";
            lines[5] = "Hewang, who contributed to";
            lines[6] = "planning and programming along";
            lines[7] = "with project member Aidan Wang.";

            int i=0;
            while(i<lines.length){
                g.drawString(lines[i], xPos, yPos);
                yPos +=26;
                i++;
            }
            g.drawImage(logo.getScaledInstance(150, 120, Image.SCALE_DEFAULT), 100, 200, this);

        }
    }
}