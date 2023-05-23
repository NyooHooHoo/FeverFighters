import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameCharacterWindow extends JFrame {
    public GameCharacterWindow() {
        setTitle("Game Character");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GamePanel gamePanel = new GamePanel();
        add(gamePanel);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameCharacterWindow::new);
    }
}

class GamePanel extends JPanel {
    private int characterX = 50;
    private int characterY = 50;

    public GamePanel() {
        setFocusable(true);

        getInputMap().put(KeyStroke.getKeyStroke("W"), "moveUp");
        getInputMap().put(KeyStroke.getKeyStroke("S"), "moveDown");
        getInputMap().put(KeyStroke.getKeyStroke("A"), "moveLeft");
        getInputMap().put(KeyStroke.getKeyStroke("D"), "moveRight");

        getActionMap().put("moveUp", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                characterY -= 10;
                repaint();
            }
        });

        getActionMap().put("moveDown", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                characterY += 10;
                repaint();
            }
        });

        getActionMap().put("moveLeft", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                characterX -= 10;
                repaint();
            }
        });

        getActionMap().put("moveRight", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                characterX += 10;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillRect(characterX, characterY, 50, 50);
    }
}
