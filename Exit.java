import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JComponent;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Exit {
    Font ty = new Font("Serif", Font.BOLD, 55);
    Font fp = new Font("SansSerif", Font.BOLD, 30);

    Font ff = new Font("Serif", Font.BOLD, 65);

    Font text = new Font("Monospace", Font.BOLD, 17);

    Color blue = new Color(159, 197, 232);
    BufferedImage logo = ImageIO.read(new File("assets/logo.png"));


    public Exit() throws IOException{
        JFrame frame = new JFrame("Exit");
        frame.setSize(600, 500);
        frame.add(new Exit.Drawing());
        frame.setVisible(true);
    }
    public static void main(String[] args) throws IOException {
        new Exit();
    }
    class Drawing extends JComponent{
        public void paint(Graphics g){
            g.setColor(blue);
            g.fillRect(0, 0, 1200, 800);
            g.setColor(Color.BLACK);

            int yPos = 120;
            g.setFont(ty);
            g.drawString("Thank You", 155, yPos);
            g.setFont(fp);
            g.drawString("FOR PLAYING", 190, yPos+50);
            g.setFont(ff);
            g.drawString("Fever Fighters!", 80, yPos+120);

            g.setFont(text);
            g.drawString("BRRR Studios", 235, 410);
            g.drawString("Lauren Hewang, Aidan Wang", 175, 430);

            g.drawImage(logo.getScaledInstance(145, 110, Image.SCALE_DEFAULT), 227, 270, this);


        }
    }
}
