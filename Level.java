import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Level extends JPanel {
    private final ArrayList<Sprite> sprites;
    private final int levelNum;
    private final JTextArea textBox;
    private boolean showTextBox;
    private int shownItemIndex;

    public Level(int levelNum) throws IOException {
        this.levelNum = levelNum;
        this.sprites = new ArrayList<>();
        this.showTextBox = true;
        this.shownItemIndex = -1;

        textBox = new JTextArea(7, 30);
        textBox.setBackground(new Color(237, 204, 202));
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        textBox.setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        textBox.setLineWrap(true);
        textBox.setWrapStyleWord(true);
        this.add(textBox);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (showTextBox) {
                        showTextBox = false;
                        removeTextBox();
                        if (shownItemIndex >= 0) {
                            sprites.remove(shownItemIndex);
                            shownItemIndex = -1;
                        }
                    }
                }
            }
        });

        if (levelNum == 1) {
            Character c = new Character(260, 330);
            sprites.add(c);

            getInputMap().put(KeyStroke.getKeyStroke("W"), "moveUp");
            getInputMap().put(KeyStroke.getKeyStroke("S"), "moveDown");
            getInputMap().put(KeyStroke.getKeyStroke("A"), "moveLeft");
            getInputMap().put(KeyStroke.getKeyStroke("D"), "moveRight");

            getActionMap().put("moveUp", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!showTextBox) {
                        c.setY(c.getY() - 10);
                        if (c.getY() < 290)
                            c.setY(290);
                        try {
                            checkCollisions();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        repaint();
                    }
                }
            });

            getActionMap().put("moveDown", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!showTextBox) {
                        c.setY(c.getY() + 10);
                        if (c.getY() > 420)
                            c.setY(420);
                        try {
                            checkCollisions();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        repaint();
                    }
                }
            });

            getActionMap().put("moveLeft", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!showTextBox) {
                        c.setX(c.getX() - 10);
                        if (c.getX() < 0)
                            c.setX(0);
                        try {
                            checkCollisions();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        repaint();
                    }
                }
            });

            getActionMap().put("moveRight", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!showTextBox) {
                        c.setX(c.getX() + 10);
                        if (c.getX() > 520)
                            c.setX(520);
                        try {
                            checkCollisions();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        repaint();
                    }
                }
            });

            sprites.add(0, new Item(60, 370, "target", false));
            sprites.add(0, new Item(300, 460, "target", false));
            sprites.add(0, new Item(20, 450, "target", false));
            sprites.add(0, new Item(500, 390, "target", false));

            String welcome = """
                Welcome to Fever Fighters! To control your character, use the WASD keys to move up, left, down, and right. To practice moving around, make your character walk to each of the red Xs on the screen.


                \t\t[press ENTER to continue]""";
            textBox.setText(welcome);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {drawBackground(g);}
        catch (IOException e) {throw new RuntimeException(e);}
        drawSprites(g);
    }

    public void checkCollisions() throws IOException {
        for (int i = 0; i < sprites.size() - 1; i++) {
            Sprite c = sprites.get(sprites.size() - 1);
            Sprite s = sprites.get(i);
            if (c.collideWithSprite(s)) {
                if (s.getType().equals("target")) {
                    sprites.remove(i);
                    i--;
                    if (sprites.size() == 1) {
                        String msg = """
                                Great work! Now, each of the items around you represents a good or bad thing to do when surviving a cold. Using the same moving controls, pick up each items to learn more about them. Make sure to remember this information, as it will be important in later levels!
                                 
                                \t\t[press ENTER to continue]""";
                        textBox.setText(msg);
                        showTextBox = true;
                        this.add(textBox);
                        c.setX(260);
                        c.setY(330);
                        sprites.add(0, new Item(60, 320, "water", false));
                        sprites.add(0, new Item(60, 380, "soup", false));
                        sprites.add(0, new Item(160, 410, "bed", false));
                        sprites.add(0, new Item(260, 410, "cold-med", false));
                        sprites.add(0, new Item(360, 410, "pill", true));
                        sprites.add(0, new Item(460, 380, "pepper", true));
                        sprites.add(0, new Item(460, 320, "chips", true));
                        break;
                    }
                }
                else if (levelNum == 1) {
                    textBox.setText(((Item) s).getDescription());
                    showTextBox = true;
                    this.add(textBox);
                    shownItemIndex = i;
                }
            }
        }
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

    public void removeTextBox() {
        this.remove(textBox);
        revalidate();
        repaint();
    }
}
