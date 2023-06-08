import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.IOException;

public class Lose extends MenuPanel {
    Font titleFont = new Font("Serif", Font.BOLD, 65);
    Font text = new Font("Monospace", Font.PLAIN, 20);
    String cause;
    Color red = new Color(234, 153, 153);

    public Lose(String cause) {
        this.cause = cause;
        setLayout(null);

        JButton button = new JButton("Return to Menu");
        button.setBounds(220, 310, 160, 80); // Top-left corner

        Color pink = new Color(243, 106, 157);
        button.setUI(new GameButtonUI(pink));

        Color lightPink = new Color(255, 200, 221);
        button.setBackground(lightPink);
        button.setFocusPainted(false);
        button.setBorder(new LineBorder(pink, 4));
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.addActionListener(e -> {
            try {
                returnMenuEvent();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        add(button);

        setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g){
        g.setColor(red);
        g.fillRect(0, 0, getWidth(), getHeight());
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
    }
}
