import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The Level class represents a level in the game. It is an abstract class that provides common functionality and structure for different levels.
 * It extends JPanel and contains methods for drawing sprites, handling collisions, and managing the level state.
 */
public abstract class Level extends JPanel {
    /**
     * ArrayList of all Sprites to display in a Level
     */
    private final ArrayList<Sprite> sprites;
    /**
     * TextBox of the level to display info
     */
    private final TextBox textBox;
    /**
     * JButton to return to menu
     */
    private final JButton button;
    /**
     * KeyAdapter to respond to user hitting the enter key
     */
    private KeyAdapter enterAdapter;
    /**
     * LevelListener to fire events to higher class
     */
    private LevelListener levelListener;

    /**
     * Constructs a Level object.
     * It sets up the necessary components, such as TextBox and Button, and defines key bindings for player movement.
     */
    public Level() {
        this.sprites = new ArrayList<>();

        setLayout(null); // Disable layout manager

        textBox = new TextBox(7, 30);
        textBox.setBounds(125, 10, 350, 165); // Center horizontally
        add(textBox);

        button = new JButton("Menu");
        button.setBounds(10, 10, 70, 50); // Top-left corner
        button.setUI(new GameButtonUI(new Color(122, 231, 108)));
        button.setBackground(new Color(165, 239, 157));
        button.setFocusPainted(false);
        button.setBorder(new LineBorder(new Color(0, 138, 43), 2));
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.addActionListener(e -> {
            try {
                levelListener.returnMenu();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        add(button);

        this.enterAdapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (getTextBoxVisible()) {
                        setTextBoxVisible(false);
                    }
                    repaint();
                }
            }
        };

        addKeyListener(enterAdapter);

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

    /**
     * Overrides the paintComponent method to draw the level background and sprites.
     *
     * @param g the Graphics object to paint on.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            drawBackground(g);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        drawSprites(g);
    }

    /**
     * Draws all the sprites on the level.
     *
     * @param g the Graphics object to paint on.
     */
    private void drawSprites(Graphics g) {
        for (Sprite s : sprites) {
            s.draw(g, this);
        }
    }

    /**
     * Checks for collisions between sprites and performs appropriate actions.
     *
     * @throws IOException if an error occurs while handling collisions.
     */
    public void checkCollisions() throws IOException {
        for (int i = 0; i < sprites.size() - 1; i++) {
            Sprite c = sprites.get(sprites.size() - 1);
            Sprite s = sprites.get(i);
            if (s.collideWithSprite(c)) {
                collide(c, s);
                break;
            }
        }
    }

    /**
     * Returns the list of sprites on the level.
     *
     * @return the list of sprites.
     */
    public ArrayList<Sprite> getSprites() {
        return sprites;
    }

    /**
     * Checks if the text box is currently visible.
     *
     * @return true if the text box is visible, false otherwise.
     */
    public boolean getTextBoxVisible() {
        return textBox.isVisible();
    }

    /**
     * Adds a sprite to the level.
     *
     * @param s the sprite to add.
     */
    public void addSprite(Sprite s) {
        sprites.add(0, s);
    }

    /**
     * Sets the text of the text box.
     *
     * @param s the text to set.
     */
    public void setTextBox(String s) {
        textBox.setText(s);
    }

    /**
     * Sets the visibility of the text box.
     *
     * @param b true to make the text box visible, false to hide it.
     */
    public void setTextBoxVisible(boolean b) {
        textBox.setVisible(b);
    }

    /**
     * Returns the menu button.
     *
     * @return the menu button.
     */
    public JButton getButton() {
        return button;
    }

    /**
     * Returns the KeyAdapter used for handling key events.
     *
     * @return the KeyAdapter object.
     */
    public KeyAdapter getEnterAdapter() {
        return enterAdapter;
    }

    /**
     * Sets the KeyAdapter used for handling key events.
     *
     * @param enterAdapter the KeyAdapter object to set.
     */
    public void setEnterAdapter(KeyAdapter enterAdapter) {
        this.enterAdapter = enterAdapter;
    }

    /**
     * Adds a LevelListener to the level.
     *
     * @param listener the LevelListener to add.
     */
    public void addLevelListener(LevelListener listener) {
        this.levelListener = listener;
    }

    /**
     * Fires a level complete event to the registered LevelListener.
     *
     * @throws IOException if an error occurs while handling the event.
     */
    protected void fireLevelCompleteEvent() throws IOException {
        if (levelListener != null) {
            levelListener.levelComplete();
        }
    }

    /**
     * Fires a game over event with the specified cause to the registered LevelListener.
     *
     * @param cause the cause of the game over.
     * @throws IOException if an error occurs while handling the event.
     */
    protected void fireGameOverEvent(String cause) throws IOException {
        if (levelListener != null) {
            levelListener.gameOver(cause);
        }
    }

    /**
     * Moves the player in the specified direction.
     *
     * @param direction the direction to move in.
     * @throws IOException if an error occurs while moving.
     */
    public abstract void move(String direction) throws IOException;

    /**
     * Handles the collision between two sprites.
     *
     * @param c the first sprite involved in the collision.
     * @param s the second sprite involved in the collision.
     * @throws IOException if an error occurs while handling the collision.
     */
    public abstract void collide(Sprite c, Sprite s) throws IOException;

    /**
     * Draws the level background.
     *
     * @param g the Graphics object to paint on.
     * @throws IOException if an error occurs while drawing the background.
     */
    public abstract void drawBackground(Graphics g) throws IOException;
}