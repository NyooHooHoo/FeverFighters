import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Represents the second (maze) level of the game.
 *
 * <h2>Modifications:</h2>
 * May 29: Aidan implemented moving mechanism (originally in Level class)
 * May 30: Aidan created subclass of new abstract class Level
 * June 2: Aidan implemented move and collide methods
 *
 * <h2>Course Info:</h2>
 * ICS4U0 with V. Krasteva
 *
 * @author Aidan Wang
 * @version 3
 * @date 05/30/2023
 */
public class Level2 extends Level {
    /**
     * The x-coordinate of the background
     */
    private int bgX;
    /**
     * The y-coordinate of the background
     */
    private int bgY;
    /**
     * The reverse of the most recent direction the character moved (up, down, left, right)
     */
    private String reverseDirection;

    public Level2() throws IOException {
        super();
        bgX = -60;
        bgY = -50;

        Character c = new Character(260, 210);
        addSprite(c);

        int mazeX = 250;
        int mazeY = 200;

        // Outer Walls
        addSprite(new Wall(mazeX, mazeY, 600, "horizontal"));
        addSprite(new Wall(mazeX, mazeY, 500, "vertical"));
        addSprite(new Wall(mazeX, mazeY + 500, 300, "horizontal"));
        addSprite(new Wall(mazeX + 400, mazeY + 500, 200, "horizontal"));
        addSprite(new Wall(mazeX + 600, mazeY, 500, "vertical"));

        // Inner Walls
        addSprite(new Wall(mazeX, mazeY + 200, 200, "horizontal"));
        addSprite(new Wall(mazeX + 100, mazeY + 100, 100, "vertical"));
        addSprite(new Wall(mazeX + 200, mazeY + 100, 100, "horizontal"));
        addSprite(new Wall(mazeX + 300, mazeY + 100, 200, "vertical"));
        addSprite(new Wall(mazeX + 300, mazeY + 200, 200, "horizontal"));
        addSprite(new Wall(mazeX + 200, mazeY + 300, 400, "horizontal"));
        addSprite(new Wall(mazeX + 100, mazeY + 300, 100, "vertical"));
        addSprite(new Wall(mazeX + 200, mazeY + 300, 100, "vertical"));
        addSprite(new Wall(mazeX + 100, mazeY + 400, 100, "horizontal"));
        addSprite(new Wall(mazeX + 300, mazeY + 400, 100, "vertical"));
        addSprite(new Wall(mazeX + 300, mazeY + 400, 100, "horizontal"));
        addSprite(new Wall(mazeX + 400, mazeY, 100, "vertical"));
        addSprite(new Wall(mazeX + 500, mazeY + 100, 100, "horizontal"));
        addSprite(new Wall(mazeX + 500, mazeY + 400, 100, "vertical"));

        // Items
        addSprite(new Item(mazeX + 320, mazeY + 520, "target", false));
        addSprite(new Item(mazeX + 20, mazeY + 110, "chips", true));
        addSprite(new Item(mazeX + 120, mazeY + 10, "water", false));
        addSprite(new Item(mazeX + 220, mazeY + 210, "bed", false));
        addSprite(new Item(mazeX + 10, mazeY + 210, "cold-med", false));
        addSprite(new Item(mazeX + 110, mazeY + 310, "pill", true));
        addSprite(new Item(mazeX + 20, mazeY + 410, "bed", false));
        addSprite(new Item(mazeX + 220, mazeY + 410, "water", false));
        addSprite(new Item(mazeX + 310, mazeY + 10, "soup", false));
        addSprite(new Item(mazeX + 410, mazeY + 110, "pepper", true));
        addSprite(new Item(mazeX + 510, mazeY + 10, "pill", true));
        addSprite(new Item(mazeX + 520, mazeY + 210, "chips", true));
        addSprite(new Item(mazeX + 310, mazeY + 210, "cold-med", false));
        addSprite(new Item(mazeX + 410, mazeY + 410, "soup", false));
        addSprite(new Item(mazeX + 510, mazeY + 310, "pepper", true));

        String welcome = """
                Welcome to the maze level! While you cannot see the entire maze at once, following the good items will lead you to the red X at the exit. Make sure to avoid bad items, as touching them will make you start from the beginning again!



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
        BufferedImage bg = ImageIO.read(new File("assets/bg2.png"));
        g.drawImage(bg, bgX, bgY, this);
    }

    /**
     * Moves the player character in the specified direction.
     *
     * @param direction the direction to move ("up", "down", "left", or "right")
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void move(String direction) throws IOException {
        boolean valid = true;

        switch (direction) {
            case "up" -> {
                reverseDirection = "down";
                bgY += 10;
                if (bgY > 0) {
                    bgY -= 10;
                    valid = false;
                }
            }
            case "down" -> {
                reverseDirection = "up";
                bgY -= 10;
                if (bgY < -500) {
                    bgY += 10;
                    valid = false;
                }
            }
            case "left" -> {
                reverseDirection = "right";
                bgX += 10;
                if (bgX > 0) {
                    bgX -= 10;
                    valid = false;
                }
            }
            case "right" -> {
                reverseDirection = "left";
                bgX -= 10;
                if (bgX < -600) {
                    bgX += 10;
                    valid = false;
                }
            }
        }

        if (valid) {
            for (int i = 0; i < getSprites().size() - 1; i++) {
                Sprite s = getSprites().get(i);
                switch (direction) {
                    case "up" -> s.setY(s.getY() + 10);
                    case "down" -> s.setY(s.getY() - 10);
                    case "left" -> s.setX(s.getX() + 10);
                    case "right" -> s.setX(s.getX() - 10);
                }
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
    @Override
    public void collide(Sprite c, Sprite s) throws IOException {
        if (s.getType().equals("wall")) {
            move(reverseDirection);
        }
        else if (s.getType().equals("target")) {
            String congrats = """
                Congratulations on completing Level 2! You've completed the maze using your knowledge on surviving a cold, but you're not a master yet! It's now time for the final level, where you'll face your toughest challenge yet to finally escape your cold.
                                
                                
                                
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
        else if (((Item) s).isBad()) {
            fireGameOverEvent(s.getType());
        }
        else {
            getSprites().remove(s);
        }
    }
}
