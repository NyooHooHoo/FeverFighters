import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Level extends JPanel {
    private final ArrayList<Sprite> sprites;
    private final TextBox textBox;

    public Level() {
        this.sprites = new ArrayList<>();

        textBox = new TextBox(7, 30);
        this.add(textBox);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (getTextBoxVisible()) {
                        setTextBoxVisible(false);
                    }
                }
            }
        });

        getInputMap().put(KeyStroke.getKeyStroke("W"), "moveUp");
        getInputMap().put(KeyStroke.getKeyStroke("S"), "moveDown");
        getInputMap().put(KeyStroke.getKeyStroke("A"), "moveLeft");
        getInputMap().put(KeyStroke.getKeyStroke("D"), "moveRight");

        getActionMap().put("moveUp", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!getTextBoxVisible()) {
                    try {
                        move("up");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        getActionMap().put("moveDown", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!getTextBoxVisible()) {
                    try {
                        move("down");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        getActionMap().put("moveLeft", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!getTextBoxVisible()) {
                    try {
                        move("left");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        getActionMap().put("moveRight", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!getTextBoxVisible()) {
                    try {
                        move("right");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {drawBackground(g);}
        catch (IOException e) {throw new RuntimeException(e);}
        drawSprites(g);
    }

    private void drawSprites(Graphics g) {
        for (Sprite s : sprites) {
            g.drawImage(s.getImg(), s.getX(), s.getY(), this);
        }
    }

    public void checkCollisions() throws IOException {
        for (int i = 0; i < sprites.size() - 1; i++) {
            Sprite c = sprites.get(sprites.size() - 1);
            Sprite s = sprites.get(i);
            if (c.collideWithSprite(s)) {
                collide(c, s);
                break;
            }
        }
    }

    public ArrayList<Sprite> getSprites() {
        return sprites;
    }

    public boolean getTextBoxVisible() {
        return textBox.isVisible();
    }

    public void addSprite(Sprite s) {
        sprites.add(0, s);
    }

    public void setTextBox(String s) {
        textBox.setText(s);
    }

    public void setTextBoxVisible(boolean b) {
        textBox.setVisible(b);
    }

    public abstract void move(String direction) throws IOException;

    public abstract void collide(Sprite c, Sprite s) throws IOException;

    public abstract void drawBackground(Graphics g) throws IOException;
}
