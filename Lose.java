import javax.swing.JFrame;
import javax.swing.JComponent;
import java.awt.*;
public class Lose {
    Font titleFont = new Font("Serif", Font.BOLD, 65);
    Font text = new Font("Monospace", Font.PLAIN, 20);
    String cause;

    Color pink = new Color(255, 200, 221);
    Color red = new Color(234, 153, 153);

    public Lose(String cause){
        JFrame frame = new JFrame("Lose");
        frame.setSize(600, 500);
        frame.add(new Lose.Drawing());
        frame.setVisible(true);
        this.cause = cause;
    }
    public static void main(String[] args) {
        new Lose("pepper");
    }
    class Drawing extends JComponent{
        public void paint(Graphics g){
            g.setColor(red);
            g.fillRect(0, 0, 1000, 1000);
            g.setFont(titleFont);
            g.setColor(Color.BLACK);
            g.drawString("GAME OVER", 90, 120);

            int yPos = 200;
            g.setFont(text);

            if(cause.equals("pepper")){
                g.drawString("Oh, no! Remember that chips and other junk foods should be", 28, yPos);
                g.drawString("avoided when you are sick because they are unhealthy and", 32, yPos+30);
                g.drawString("their rough textures can make your throat hurt even more.", 39, yPos+60);
                g.drawString("Better luck next time!", 205, yPos+90);

            }


            g.setColor(pink);
            g.fillRect(220, 350, 150, 70);

        }
    }
}
