import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The About class represents a panel that displays information about the game.
 * It extends the MenuPanel class.
 */
public class About extends MenuPanel {
    private final Font titleFont = new Font("SansSerif", Font.BOLD, 50);
    private final Font text = new Font("Monospace", Font.PLAIN, 15);
    private final Color lightBlue = new Color(189, 224, 254);
    private final Color blue = new Color(162, 210, 255);
    private final BufferedImage logo;

    /**
     * Constructs an About panel.
     *
     * @throws IOException if an error occurs while loading the logo image.
     */
    public About() throws IOException {
        setLayout(null);

        JButton button = new JButton("Return to Menu");
        button.setBounds(220, 385, 160, 40); // Top-left corner
        button.setUI(new GameButtonUI(blue));
        button.setBackground(lightBlue);
        button.setFocusPainted(false);
        button.setBorder(new LineBorder(blue, 4));
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.addActionListener(e -> {
            try {
                returnMenuEvent();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        add(button);

        logo = ImageIO.read(new File("assets/logo.png"));

        setVisible(true);
    }

    /**
     * Overrides the paintComponent method to paint the About panel.
     *
     * @param g the Graphics object used for painting.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setFont(titleFont);
        g.setColor(Color.BLACK);
        g.drawString("About", 220, 95);

        g.setColor(lightBlue);
        g.fillRect(85, 120, 430, 250);
        g.setColor(blue);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(4));
        g.drawRect(85, 120, 430, 250);

        g.setColor(Color.BLACK);
        g.setFont(text);
        int xPos = 275;
        int yPos = 160;
        g.drawString("Fever Fighters is a game", xPos, yPos);
        String[] lines = new String[9];
        lines[0] = "Fever Fighters is a game";
        lines[1] = "developed by BRRR Studios in";
        lines[2] = "cooperation with VK";
        lines[3] = "Enterprises. The development";
        lines[4] = "team was overseen by project";
        lines[5] = "leader Lauren Hewang, who";
        lines[6] = "contributed to planning and";
        lines[7] = "programming along with";
        lines[8] = "project member Aidan Wang.";

        int i = 0;
        while (i < lines.length) {
            g.drawString(lines[i], xPos, yPos);
            yPos += 22;
            i++;
        }
        g.drawImage(logo.getScaledInstance(150, 120, Image.SCALE_DEFAULT), 105, 185, this);
    }
}
