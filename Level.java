import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class Level extends JPanel {
    private ArrayList<Sprite> sprites;
    private int levelNum;

    public Level(int levelNum) throws IOException {
        this.levelNum = levelNum;
        this.sprites = new ArrayList<>();
        if (levelNum == 1) {
            Character c = new Character(300, 250);
            sprites.add(c);

            getInputMap().put(KeyStroke.getKeyStroke("W"), "moveUp");
            getInputMap().put(KeyStroke.getKeyStroke("S"), "moveDown");
            getInputMap().put(KeyStroke.getKeyStroke("A"), "moveLeft");
            getInputMap().put(KeyStroke.getKeyStroke("D"), "moveRight");

            getActionMap().put("moveUp", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    c.setY(c.getY() - 10);
                    repaint();
                }
            });

            getActionMap().put("moveDown", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    c.setY(c.getY() + 10);
                    repaint();
                }
            });

            getActionMap().put("moveLeft", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    c.setX(c.getX() - 10);
                    repaint();
                }
            });

            getActionMap().put("moveRight", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    c.setX(c.getX() + 10);
                    repaint();
                }
            });
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {drawBackground(g);}
        catch (IOException e) {throw new RuntimeException(e);}
        drawSprites(g);
    }

    public void drawSprites(Graphics g) {
        for (Sprite s : sprites) {
            g.drawImage(s.getImg(), s.getX(), s.getY(), this);
        }
    }

    public void drawBackground(Graphics g) throws IOException {
        BufferedImage bg = ImageIO.read(new File("assets/bg" + levelNum + ".png"));
        g.drawImage(bg, 0, 0, this);
    }

    public void displayTextBox(int width, int height, String msg, String img) {

    }
}
