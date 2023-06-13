import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * The Menu class represents the main menu of the Fever Fighters game.
 * It extends the MenuPanel class.
 *
 * <h2>Modifications</h2>
 * May 16: Lauren create basic layout
 * May 19: Created Colors and buttons
 * June 7: Aidan added MenuPanel superclass and listeners
 *
 * <h2>Time Spent:</h2>
 * 2 hours
 *
 * <h2>Course Info:</h2>
 * ICS4U0 with V. Krasteva
 *
 * @author Lauren Hewang, Aidan Wang
 * @version 1
 */
public class Menu extends MenuPanel {
    /**
     * Constructs a new Menu object.
     * Sets up the layout and adds buttons to the menu.
     */
    public Menu() {
        setLayout(new GridBagLayout()); // Use GridBagLayout for precise positioning

        JLabel title = new JLabel("Fever Fighters");
        JButton play = new JButton("Play");
        JButton levelSelect = new JButton("Level Select");
        JButton about = new JButton("About");
        JButton exit = new JButton("Exit");

        play.addActionListener(e -> {
            endBackground();
            try {
                startGameEvent(1);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        levelSelect.addActionListener(e -> {
            endBackground();
            try {
                levelSelectEvent();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        about.addActionListener(e -> {
            endBackground();
            try {
                aboutEvent();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        exit.addActionListener(e -> {
            endBackground();
            try {
                exitEvent();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        addTitle(title, 0, 0);
        addButton(play, 1, 0);
        addButton(levelSelect, 2, 0);
        addButton(about, 3, 0);
        addButton(exit, 4, 0);

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
