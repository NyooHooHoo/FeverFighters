import javax.swing.*;
import java.io.IOException;

public class Game extends JFrame {
    private Level currentLevel;
    private int currentLevelNum;

    public Game(int start) throws IOException {
        setTitle("Fever Fighters");
        setSize(600, 521);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        currentLevelNum = start;
        loadLevel(currentLevelNum);

        setVisible(true);
    }

    public void loadLevel(int levelNum) throws IOException {
        if (currentLevel != null) {
            remove(currentLevel);
        }

        if (levelNum == 1) {
            Level level1 = new Level1();
            level1.addLevelListener(new PanelListener() {
                @Override
                public void levelComplete() throws IOException {
                    nextLevel();
                }

                @Override
                public void gameOver(String cause) throws IOException {

                }
            });
            currentLevel = level1;
        }

        else if (levelNum == 2) {
            Level level2 = new Level2();
            level2.addLevelListener(new PanelListener() {
                @Override
                public void levelComplete() throws IOException {
                    nextLevel();
                }

                @Override
                public void gameOver(String cause) throws IOException {

                }
            });
            currentLevel = level2;
        }

        else if (levelNum == 3) {
            Level level3 = new Level3();
            level3.addLevelListener(new PanelListener() {
                @Override
                public void levelComplete() throws IOException {
                    nextLevel();
                }

                @Override
                public void gameOver(String cause) throws IOException {

                }
            });
            currentLevel = level3;
        }

        add(currentLevel);
        currentLevel.requestFocusInWindow();
    }

    private void nextLevel() throws IOException {
        currentLevelNum++;
        if (currentLevelNum <= 3) {
            loadLevel(currentLevelNum);
        } else {
            // Game over or end of levels
        }
        setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        new Game(3);
    }
}
