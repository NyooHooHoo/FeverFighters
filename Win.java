import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.IOException;

public class Win extends MenuPanel {
    private final Font titleFont = new Font("Serif", Font.BOLD, 65);
    private final Font text = new Font("Monospace", Font.BOLD, 25);
    private final Color green = new Color(150, 224, 114);

    public Win() {
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

    public void paintComponent(Graphics g){
        g.setColor(green);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setFont(titleFont);
        g.setColor(Color.BLACK);
        g.drawString("You WIN!", 155, 120);

        int yPos = 200;
        g.setFont(text);
        g.drawString("Congratulations! You have successfully", 49, yPos);
        g.drawString("completed all three levels and are now", 52, yPos+30);
        g.drawString("prepared to survive any cold!", 110, yPos+60);
    }
}
