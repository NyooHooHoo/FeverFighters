import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class LevelSelect extends MenuPanel {
    public LevelSelect() {
        setLayout(new GridBagLayout()); // Use GridBagLayout for precise positioning

        JLabel title = new JLabel("Level Select");
        JButton level1 = new JButton("Level 1: Learning");
        JButton level2 = new JButton("Level 2: Maze");
        JButton level3 = new JButton("Level 3: Challenge");
        JButton menu = new JButton("Return to Menu");

        level1.addActionListener(e -> {
            try {
                startGameEvent(1);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        level2.addActionListener(e -> {
            try {
                startGameEvent(2);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        level3.addActionListener(e -> {
            try {
                startGameEvent(3);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        menu.addActionListener(e -> {
            try {
                returnMenuEvent();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        addTitle(title, 0, 0);
        addButton(level1, 1, 0);
        addButton(level2, 2, 0);
        addButton(level3, 3, 0);
        addButton(menu, 4, 0);

        setVisible(true);
    }
}
