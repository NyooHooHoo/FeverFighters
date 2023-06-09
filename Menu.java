import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * The Menu class represents the main menu of the Fever Fighters game.
 * It extends the MenuPanel class.
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
            try {
                startGameEvent(1);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        levelSelect.addActionListener(e -> {
            try {
                levelSelectEvent();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        about.addActionListener(e -> {
            try {
                aboutEvent();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        exit.addActionListener(e -> {
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

        setVisible(true);
    }
}
