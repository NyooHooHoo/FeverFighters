import javax.swing.JFrame;
import javax.swing.JComponent;
import java.awt.*;

public class LevelSelect {
    Font titleFont = new Font("SansSerif", Font.BOLD, 50);
    Font optionsFont = new Font("Monospace", Font.BOLD, 18);

    Color pink = new Color(255, 200, 221);
    Color lightBlue = new Color(189, 224, 254);
    Color blue = new Color(162, 210, 255);

    public LevelSelect(){
        JFrame frame = new JFrame("Level Select");
        frame.setSize(600, 500);
        frame.add(new Drawing());
        frame.setVisible(true);
    }

    public static void main(String[] args){
        new LevelSelect();
    }

    class Drawing extends JComponent{
        public void paint(Graphics g){
            g.setColor(pink);
            g.fillRect(0, 0, 1200, 800);
            g.setFont(titleFont);
            g.setColor(Color.BLACK);
            g.drawString("Level Select", 120, 80);

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
            g.drawString("Level 1: Learning", 250, 150);
            g.drawString("Level 2: Maze", 250, 230);
            g.drawString("Level 3: Escape Room", 250, 310);
            g.drawString("Return to Main Menu", 250, 390);
        }
    }
}