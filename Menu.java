import javax.swing.JFrame;
import javax.swing.JComponent;
import java.awt.*;

public class Menu {
    int level;

    Font titleFont = new Font("SansSerif", Font.BOLD, 50);
    Font optionsFont = new Font("Monospace", Font.BOLD, 30);

    Color pink = new Color(255, 200, 221);
    Color lightBlue = new Color(189, 224, 254);
    Color blue = new Color(162, 210, 255);

    public Menu(){
        JFrame frame = new JFrame("Main Menu");
        frame.setSize(600, 500);
        frame.add(new Drawing());
        frame.setVisible(true);
    }

    public static void main(String[] args){
        new Menu();
    }

    class Drawing extends JComponent{
        public void paint(Graphics g){
            g.setColor(pink);
            g.fillRect(0, 0, 1200, 800);
            g.setFont(titleFont);
            g.setColor(Color.BLACK);
            g.drawString("Fever Fighters", 120, 80);

            g.setColor(lightBlue);
            g.fillRect(120, 110, 340, 60);
            g.fillRect(120, 190, 340, 60);
            g.fillRect(120, 270, 340, 60);
            g.fillRect(120, 350, 340, 60);

            g.setColor(blue);
            g.drawRect(120, 110, 340, 60);
            g.drawRect(120, 190, 340, 60);
            g.drawRect(120, 270, 340, 60);
            g.drawRect(120, 350, 340, 60);

            g.setColor(Color.BLACK);
            g.setFont(optionsFont);
            g.drawString("Play", 250, 150);
            g.drawString("Level Select", 200, 230);
            g.drawString("About", 250, 310);
            g.drawString("Exit", 250, 390);
        }
    }
}