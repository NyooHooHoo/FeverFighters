import javax.swing.JFrame;
import javax.swing.JComponent;
import java.awt.*;

public class SplashScreen {
    Font titleFont = new Font("SansSerif", Font.BOLD, 50);
    Font logoFont = new Font("SansSerif", Font.BOLD, 20);

        Color bg = new Color(201, 214, 248);
        Color logoBlue = new Color(10, 122, 199);
        public SplashScreen(){
            JFrame frame = new JFrame("Splash Screen");
            frame.setSize(600, 500);
            frame.add(new Drawing());
            frame.setVisible(true);
        }
        public static void main(String[] args){
            new SplashScreen();
        }
        class Drawing extends JComponent{
            public void paint(Graphics g){
                g.setColor(bg);
                g.fillRect(0, 0, 600, 500);
                g.setFont(titleFont);
                g.setColor(Color.BLACK);
                g.drawString("Fever Fighters", 120, 80);

            g.setColor(logoBlue);
            g.fillArc(180, 135, 240, 210 , 0, 180);
            g.setFont(logoFont);
            g.setColor(Color.WHITE);
            g.drawString("BRRR Studios", 220, 210);

            g.setColor(logoBlue);
            int[] x1 = {180, 206, 193};
            int[] x2 = {206, 236, 222};
            int[] x3 = {236, 250, 242};
            int[] x4 = {250, 284, 267};
            int[] x5 = {284, 308, 296};
            int[] x6 = {308, 332, 316};
            int[] x7 = {332, 358, 343};
            int[] x8 = {358, 390, 374};
            int[] x9 = {390, 420, 405};

            int[] y1 = {235, 235, 265};
            int[] y2 = {235, 235, 280};
            int[] y3 = {235, 235, 268};
            int[] y4 = {235, 235, 320};
            int[] y5 = {235, 235, 268};
            int[] y6 = {235, 235, 255};
            int[] y7 = {235, 235, 280};
            int[] y8 = {235, 235, 308};
            int[] y9 = {235, 235, 290};

            g.fillPolygon(x1, y1, 3);
            g.fillPolygon(x2, y2, 3);
            g.fillPolygon(x3, y3, 3);
            g.fillPolygon(x4, y4, 3);
            g.fillPolygon(x5, y5, 3);
            g.fillPolygon(x6, y6, 3);
            g.fillPolygon(x7, y7, 3);
            g.fillPolygon(x8, y8, 3);
            g.fillPolygon(x9, y9, 3);

            g.setColor(Color.WHITE);
            g.fillRect(0, 380, 600, 80);
        }
    }
}