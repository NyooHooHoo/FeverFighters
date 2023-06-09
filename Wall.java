import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.IOException;

/**
 * The Wall class represents a wall sprite in the game.
 *
 * <h2>Course Info:</h2>
 * ICS4U0 with V. Krasteva
 *
 * @author Aidan Wang
 * @version 3
 * @date 06/02/2023
 */
public class Wall extends Sprite {
    /**
     * The length of the Wall
     */
    private final int length;
    /**
     * The direction of the wall (horizontal or vertical)
     */
    private final String direction;

    /**
     * Constructs a Wall sprite with the specified coordinates, length, and direction.
     *
     * @param x         the x-coordinate of the top-left corner of the wall
     * @param y         the y-coordinate of the top-left corner of the wall
     * @param length    the length of the wall
     * @param direction the direction of the wall ("horizontal" or "vertical")
     * @throws IOException if there is an error reading the wall image
     */
    public Wall(int x, int y, int length, String direction) throws IOException {
        super(x, y, "wall");
        this.length = length;
        this.direction = direction;
    }

    /**
     * Overrides the draw() method to draw the wall on the graphics context.
     *
     * @param g        the graphics context
     * @param observer the image observer
     */
    @Override
    public void draw(Graphics g, ImageObserver observer) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(112, 60, 15));
        g2.setStroke(new BasicStroke(10));
        if (direction.equals("horizontal")) {
            g2.drawLine(getX(), getY(), getX() + length, getY());
        } else if (direction.equals("vertical")) {
            g2.drawLine(getX(), getY(), getX(), getY() + length);
        }
    }

    /**
     * Overrides the collideWithSprite() method to check for collision with another sprite.
     *
     * @param s the sprite to check for collision with
     * @return true if there is a collision, false otherwise
     */
    @Override
    public boolean collideWithSprite(Sprite s) {
        if (direction.equals("horizontal")) {
            return s.getHitbox().y <= this.getY() && s.getHitbox().y + s.getHitbox().height >= this.getY()
                    && ((s.getHitbox().x >= this.getX() && s.getHitbox().x <= this.getX() + this.length) ||
                    (s.getHitbox().x + s.getHitbox().width >= this.getX() && s.getHitbox().x + s.getHitbox().width
                            <= this.getX() + this.length));
        } else { // vertical
            return s.getHitbox().x <= this.getX() && s.getHitbox().x + s.getHitbox().width >= this.getX()
                    && ((s.getHitbox().y >= this.getY() && s.getHitbox().y <= this.getY() + this.length) ||
                    (s.getHitbox().y + s.getHitbox().height >= this.getY() && s.getHitbox().y + s.getHitbox().height
                            <= this.getY() + this.length));
        }
    }
}

