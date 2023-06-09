import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

/**
 * The Sprite class represents a game sprite with its position, image, hitbox, and collision logic.
 *
 * <h2>Modifications:</h2>
 * May 23: Aidan added collideWithSprite method to detect collisions
 * June 2: Aidan improved collision detection using Rectangle objects for hitbox
 *
 * <h2>Time Spent:</h2>
 * 2 hours
 *
 * <h2>Course Info:</h2>
 * ICS4U0 with V. Krasteva
 *
 * @author Aidan Wang
 * @version 1
 */
public class Sprite {
    /**
     * The x-coordinate of the Sprite
     */
    private int x;
    /**
     * The y-coordinate of the Sprite
     */
    private int y;
    /**
     * The height of the sprite
     */
    private int height;
    /**
     * The width of the sprite
     */
    private int width;
    /**
     * The type of Sprite
     */
    private final String type;
    /**
     * The BufferedImage to display for the Sprite on screen
     */
    private BufferedImage img;
    /**
     * The hitbox for the Sprite where it can collide with other Sprites
     */
    private Rectangle hitbox;

    /**
     * Constructs a Sprite with the specified position and type.
     *
     * @param x    the x-coordinate of the top-left of the Sprite
     * @param y    the y-coordinate of the top-left of the Sprite
     * @param type the type of the Sprite
     * @throws IOException if an I/O error occurs during image loading
     */
    public Sprite(int x, int y, String type) throws IOException {
        this.x = x;
        this.y = y;
        this.type = type;
        if (!type.equals("wall")) {
            this.img = ImageIO.read(new File("assets/" + type + ".png"));
            this.height = getImg().getHeight();
            this.width = getImg().getWidth();
            this.hitbox = new Rectangle(
                    this.x + this.width / 6,
                    this.getType().equals("character") ? (this.y + this.height / 2) :
                            (this.y + this.height / (this.getType().equals("pepper") ? 3 : 4)),
                    this.width * 2 / 3,
                    this.getType().equals("character") ? (this.height / 2) : (this.height * 3 / 5)
            );
        }
    }

    /**
     * Checks if this Sprite collides with another Sprite.
     *
     * @param s the other Sprite to check collision with
     * @return true if the Sprites collide, false otherwise
     */
    public boolean collideWithSprite(Sprite s) {
        return this.hitbox.x < s.hitbox.x + s.hitbox.width && this.hitbox.x + this.hitbox.width > s.hitbox.x
                && this.hitbox.y < s.hitbox.y + s.hitbox.height && this.hitbox.y + this.hitbox.height > s.hitbox.y;
    }

    /**
     * Draws the Sprite on the specified Graphics object.
     *
     * @param g        the Graphics object to draw on
     * @param observer the ImageObserver for loading the image
     */
    public void draw(Graphics g, ImageObserver observer) {
        g.drawImage(img, x, y, observer);
    }

    /**
     * Returns the type of the Sprite.
     *
     * @return the type of the Sprite
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the image of the Sprite.
     *
     * @return the image of the Sprite
     */
    public BufferedImage getImg() {
        return img;
    }

    /**
     * Returns the hitbox of the Sprite.
     *
     * @return the hitbox of the Sprite
     */
    public Rectangle getHitbox() {
        return hitbox;
    }

    /**
     * Returns the x-coordinate of the Sprite.
     *
     * @return the x-coordinate of the Sprite
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y-coordinate of the Sprite.
     *
     * @return the y-coordinate of the Sprite
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the x-coordinate of the Sprite and updates the hitbox if the type is not "wall".
     *
     * @param x the new x-coordinate of the Sprite
     */
    public void setX(int x) {
        this.x = x;
        if (!type.equals("wall"))
            this.hitbox.x = this.x + this.width / 6;
    }

    /**
     * Sets the y-coordinate of the Sprite and updates the hitbox if the type is not "wall".
     *
     * @param y the new y-coordinate of the Sprite
     */
    public void setY(int y) {
        this.y = y;
        if (!type.equals("wall")) {
            this.hitbox.y = this.getType().equals("character") ? (this.y + this.height / 2) :
                    (this.y + this.height / (this.getType().equals("pepper") ? 3 : 4));
        }
    }
}
