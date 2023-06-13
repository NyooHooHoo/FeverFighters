import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * The LevelSelect class represents a panel that allows the user to select a game level.
 * It extends the MenuPanel class and provides buttons for different levels and a return to the menu option.
 *
 * <h2>Modifications:</h2>
 * May 16: Lauren created class with all content
 * June 7: Aidan added buttons and listeners
 *
 * <h2>Time Spent:</h2>
 * 1 hour
 *
 * <h2>Course Info:</h2>
 * ICS4U0 with V. Krasteva
 *
 * @author Lauren Hewang, Aidan Wang
 * @version 2
 */
public class LevelSelect extends MenuPanel {
    /**
     * Constructs a LevelSelect object.
     * Sets up the layout using GridBagLayout for precise positioning.
     * Creates and configures buttons for different levels and the return to menu option.
     * Adds the buttons to the panel and makes the panel visible.
     */
    public LevelSelect() {
        setLayout(new GridBagLayout());

        JLabel title = new JLabel("Level Select");
        JButton level1 = new JButton("Level 1: Learning");
        JButton level2 = new JButton("Level 2: Maze");
        JButton level3 = new JButton("Level 3: Escape");
        JButton menu = new JButton("Return to Menu");

        // ActionListener for level 1 button
        level1.addActionListener(e -> {
            endBackground();
            try {
                startGameEvent(1);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        // ActionListener for level 2 button
        level2.addActionListener(e -> {
            endBackground();
            try {
                startGameEvent(2);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        // ActionListener for level 3 button
        level3.addActionListener(e -> {
            endBackground();
            try {
                startGameEvent(3);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        // ActionListener for menu button
        menu.addActionListener(e -> {
            endBackground();
            try {
                returnMenuEvent();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        // Add components to the panel
        addTitle(title, 0, 0);
        addButton(level1, 1, 0);
        addButton(level2, 2, 0);
        addButton(level3, 3, 0);
        addButton(menu, 4, 0);

        startBackground();
        setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            drawBackground(g);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
