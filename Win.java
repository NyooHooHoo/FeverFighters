import javax.swing.JFrame;
import javax.swing.JComponent;
import java.awt.*;
public class Win {
    Font titleFont = new Font("Serif", Font.BOLD, 65);
    Font text = new Font("Monospace", Font.BOLD, 25);

    Color pink = new Color(255, 200, 221);
    Color green = new Color(150, 224, 114);

    public Win(){
        JFrame frame = new JFrame("About");
        frame.setSize(600, 500);
        frame.add(new Win.Drawing());
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        new Win();
    }
    class Drawing extends JComponent{
        public void paint(Graphics g){
            g.setColor(green);
            g.fillRect(0, 0, 1200, 800);
            g.setFont(titleFont);
            g.setColor(Color.BLACK);
            g.drawString("You WIN!", 150, 120);

            int yPos = 210;
            g.setFont(text);
            g.drawString("Congratulations! You have successfully", 60, yPos);
            g.drawString("completed all three levels and are now", 65, yPos+30);
            g.drawString("prepared to survive any cold!", 110, yPos+60);

            g.setColor(pink);
            g.fillRect(225, 350, 150, 70);

        }
    }
}
