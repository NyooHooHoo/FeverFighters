import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Represents the first (learning) level of the game.
 *
 * <h2>Modifications:</h2>
 * May 25: Aidan rote first half with Xs (originally in Level class)
 * May 26: Aidan implemented text boxes for items half of level
 * May 30: Aidan created subclass of new abstract class Level
 *
 * <h2>Course Info:</h2>
 * ICS4U0 with V. Krasteva
 *
 * @author Aidan Wang
 * @version 3
 * @date 05/30/2023
 */
public class Level1 extends Level {
    /**
     * Constructs a new instance of Level1.
     *
     * @throws IOException if an I/O error occurs
     */
    public Level1() throws IOException {
        super();

        // Create and add sprites to the level
        Character c = new Character(260, 310);
        addSprite(c);

        addSprite(new Item(60, 370, "target", false));
        addSprite(new Item(300, 450, "target", false));
        addSprite(new Item(20, 440, "target", false));
        addSprite(new Item(500, 390, "target", false));

        // Set the initial text box message
        String welcome = """
                Welcome to Fever Fighters! To control your character, use the WASD keys to move up, left, down, and right. Let's practice moving around by making your character walk to each of the red Xs on the screen.
                
                
                
                \t                [press ENTER to continue]""";
        setTextBox(welcome);
    }

    /**
     * Draws the background image of the level.
     *
     * @param g the Graphics object to draw on
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void drawBackground(Graphics g) throws IOException {
        // Draw the level background image
        BufferedImage bg = ImageIO.read(new File("assets/bg1.png"));
        g.drawImage(bg, 0, 0, this);
    }

    /**
     * Moves the player character in the specified direction.
     *
     * @param direction the direction to move ("up", "down", "left", or "right")
     * @throws IOException if an I/O error occurs
     */
    public void move(String direction) throws IOException {
        Sprite c = getSprites().get(getSprites().size() - 1);
        switch (direction) {
            case "up" -> {
                c.setY(c.getY() - 10);
                if (c.getY() < 290)
                    c.setY(290);
            }
            case "down" -> {
                c.setY(c.getY() + 10);
                if (c.getY() > 420)
                    c.setY(420);
            }
            case "left" -> {
                c.setX(c.getX() - 10);
                if (c.getX() < 0)
                    c.setX(0);
            }
            case "right" -> {
                c.setX(c.getX() + 10);
                if (c.getX() > 520)
                    c.setX(520);
            }
        }
        checkCollisions();
        repaint();
    }

    /**
     * Handles collisions between the player character and other sprites.
     *
     * @param c the player character sprite
     * @param s the collided sprite
     * @throws IOException if an I/O error occurs
     */
    public void collide(Sprite c, Sprite s) throws IOException {
        if (s.getType().equals("target")) {
            getSprites().remove(s);
            if (getSprites().size() == 1) {
                String msg = """
                        Great work! Now, each of the items around you represents a good or bad thing to do when surviving a cold. Using the same moving controls, pick up each items to learn more about them. Make sure to remember this information, as it will be important in later levels!
                         
                        
                        \t                [press ENTER to continue]""";
                setTextBox(msg);
                setTextBoxVisible(true);

                c.setX(260);
                c.setY(310);

                addSprite(new Item(60, 320, "water", false));
                addSprite(new Item(60, 380, "soup", false));
                addSprite(new Item(160, 400, "bed", false));
                addSprite(new Item(260, 400, "cold-med", false));
                addSprite(new Item(360, 400, "pill", true));
                addSprite(new Item(460, 380, "pepper", true));
                addSprite(new Item(460, 320, "chips", true));
            }
        }
        else {
            setTextBox(((Item) s).getDescription());
            setTextBoxVisible(true);

            removeKeyListener(getEnterAdapter());
            setEnterAdapter(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        getSprites().remove(s);
                        if (getTextBoxVisible()) {
                            setTextBoxVisible(false);
                        }
                        if (getSprites().size() == 1) {
                            endMessage();
                        }
                        repaint();
                    }
                }
            });
            addKeyListener(getEnterAdapter());
        }
    }

    /**
     * Displays the end message when all items have been collected.
     * Updates the text box and handles the level complete event.
     */
    private void endMessage() {
        String congrats = """
                Congratulations! Now that you’ve learned all about the dos and don’ts of surviving a cold, you can move on to the next level and test your knowledge of these items in a new setting.
                
                
                
                
                \t                [press ENTER to continue]""";
        setTextBox(congrats);
        setTextBoxVisible(true);
        removeKeyListener(getEnterAdapter());
        setEnterAdapter(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        fireLevelCompleteEvent();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        addKeyListener(getEnterAdapter());
    }
}
