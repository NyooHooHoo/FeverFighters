import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.IOException;

/**
 * The Lose class represents a panel that is displayed when the player loses the game.
 * It extends the MenuPanel class and displays the game over message and the cause of the loss.
 *
 * <h2>Modifications:</h2>
 * June 6: Lauren created content
 * June 8: Lauren added cases for each bad item
 *
 * <h2>Time Spent:</h2>
 * 1 hour
 *
 * <h2>Course Info:</h2>
 * ICS4U0 with V. Krasteva
 *
 * @author Lauren Hewang
 * @version 4
 */
public class Lose extends MenuPanel {
    /**
     * Font for the title
     */
    private final Font titleFont = new Font("Serif", Font.BOLD, 65);
    /**
     * Font for the body text
     */
    private final Font textFont = new Font("Monospace", Font.PLAIN, 20);
    /**
     * String for the type of Item that caused game over
     */
    private final String cause;
    /**
     * Color for background of the screen
     */
    private final Color red = new Color(234, 153, 153);

    /**
     * Constructs a Lose object with the specified cause of the loss.
     *
     * @param cause the cause of the loss
     */
    public Lose(String cause) {
        this.cause = cause;
        setLayout(null);

        JButton button = new JButton("Return to Menu");
        button.setBounds(220, 310, 160, 80);

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
    public void paintComponent(Graphics g) {
        g.setColor(red);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setFont(titleFont);
        g.setColor(Color.BLACK);
        g.drawString("GAME OVER", 90, 120);

        int yPos = 200;
        g.setFont(textFont);

        switch (cause) {
            case "chips" -> {
                g.drawString("Oh, no! Remember that chips and other junk foods", 68, yPos);
                g.drawString("are unhealthy and their rough textures can make", 76, yPos + 30);
                g.drawString("your throat hurt even more. Better luck next time!", 73, yPos + 60);
            }
            case "pepper" -> {
                g.drawString("Oh, no! Remember that spicy foods can aggravate", 70, yPos);
                g.drawString("your cold and should be avoided when you are", 90, yPos + 30);
                g.drawString("sick. Better luck next time!", 180, yPos + 60);
            }
            case "pill" -> {
                g.drawString("Oh, no! Remember that most non-cold medications", 66, yPos);
                g.drawString("and pills are actually ineffective against colds!", 83, yPos + 30);
                g.drawString("Better luck next time!", 210, yPos + 60);
            }
        }
    }
}
