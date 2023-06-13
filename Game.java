import javax.swing.*;
import java.io.IOException;

/**
 * The Game class represents one instance of a game in a JFrame, managing all other classes through JPanels.
 *
 * <h2>Modifications:</h2>
 * May 23: Aidan created class
 * June 2: Aidan added LevelListener and JPanel switching
 *
 * <h2>Time Spent:</h2>
 * 3 hours
 *
 * <h2>Course Info:</h2>
 * ICS4U0 with V. Krasteva
 *
 * @author Aidan Wang
 * @version 1
 */
public class Game extends JFrame {
    /**
     * The current JPanel being displayed
     */
    private JPanel currentPanel;
    /**
     * The number ID of the current panel
     */
    private int currentPanelNum;
    /**
     * The item that caused game over when event fired
     */
    private String gameOverCause;
    /**
     * Implementation of LevelListener for the game
     */
    private final LevelListener levelListener = new LevelListener() {
        @Override
        public void levelComplete() throws IOException {
            currentPanelNum++;
            loadPanel(currentPanelNum);
        }

        @Override
        public void gameOver(String cause) throws IOException {
            gameOverCause = cause;
            currentPanelNum = 5;
            loadPanel(currentPanelNum);
        }

        @Override
        public void returnMenu() throws IOException {
            currentPanelNum = 0;
            loadPanel(currentPanelNum);
        }
    };
    /**
     * Implementation of MenuListener for the game
     */
    private final MenuListener menuListener = new MenuListener() {
        @Override
        public void startGame(int level) throws IOException {
            currentPanelNum = level;
            loadPanel(currentPanelNum);
        }

        @Override
        public void levelSelect() throws IOException {
            currentPanelNum = -1;
            loadPanel(currentPanelNum);
        }

        @Override
        public void about() throws IOException {
            currentPanelNum = -2;
            loadPanel(currentPanelNum);
        }

        @Override
        public void exit() throws IOException {
            currentPanelNum = 6;
            loadPanel(currentPanelNum);
        }

        @Override
        public void returnMenu() throws IOException {
            currentPanelNum = 0;
            loadPanel(currentPanelNum);
        }
    };
    /**
     * Implementation of AnimationListener for the game
     */
    private final AnimationListener animationListener = new AnimationListener() {
        @Override
        public void loadMenu() throws IOException {
            currentPanelNum = 0;
            loadPanel(currentPanelNum);
        }

        @Override
        public void close() {
            setVisible(false);
            dispose();
            System.exit(0);
        }
    };

    /**
     * Creates a new Game instance with the specified starting panel number.
     *
     * @param start the panel number to start the game from.
     * @throws IOException if an IO error occurs.
     */
    public Game(int start) throws IOException {
        setTitle("Fever Fighters");
        setSize(600, 521);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        gameOverCause = "";
        currentPanelNum = start;
        loadPanel(currentPanelNum);

        setVisible(true);
    }

    /**
     * Loads the specified panel based on the panel number.
     *
     * @param panelNum the panel number to load.
     * @throws IOException if an IO error occurs.
     */
    public void loadPanel(int panelNum) throws IOException {
        if (currentPanel != null) {
            remove(currentPanel);
        }

        JPanel panel = null;

        if (panelNum == 0) {
            panel = new Menu();
            ((MenuPanel) panel).addMenuListener(menuListener);
        } else if (panelNum == -1) {
            panel = new LevelSelect();
            ((MenuPanel) panel).addMenuListener(menuListener);
        } else if (panelNum == -2) {
            panel = new About();
            ((MenuPanel) panel).addMenuListener(menuListener);
        } else if (panelNum == -3) {
            panel = new SplashScreen();
            ((SplashScreen) panel).addAnimationListener(animationListener);
        } else if (panelNum == 1) {
            panel = new Level1();
            ((Level) panel).addLevelListener(levelListener);
        } else if (panelNum == 2) {
            panel = new Level2();
            ((Level) panel).addLevelListener(levelListener);
        } else if (panelNum == 3) {
            panel = new Level3();
            ((Level) panel).addLevelListener(levelListener);
        } else if (panelNum == 4) {
            panel = new Win();
            ((MenuPanel) panel).addMenuListener(menuListener);
        } else if (panelNum == 5) {
            panel = new Lose(gameOverCause);
            ((MenuPanel) panel).addMenuListener(menuListener);
        } else if (panelNum == 6) {
            panel = new Exit();
            ((Exit) panel).addAnimationListener(animationListener);
        }

        currentPanel = panel;
        add(currentPanel);
        currentPanel.requestFocusInWindow();
        setVisible(true);
    }
}
