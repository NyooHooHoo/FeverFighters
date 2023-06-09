import java.io.IOException;

/**
 * The Character class represents a character sprite in a game.
 * It extends the Sprite class.
 *
 * <h2>Modifications:</h2>
 * May 23: Aidan created class
 *
 * <h2>Time Spent:</h2>
 * less than 1 hour
 *
 * <h2>Course Info:</h2>
 * ICS4U0 with V. Krasteva
 *
 * @author Aidan Wang
 * @version 1
 */
public class Character extends Sprite {
    /**
     * Number of health for character
     */
    private int health;
    /**
     * Number of points the character has
     */
    private int points;

    /**
     * Constructs a Character object with the specified position.
     *
     * @param x the x-coordinate of the character's position.
     * @param y the y-coordinate of the character's position.
     * @throws IOException if an error occurs while loading the character sprite.
     */
    public Character(int x, int y) throws IOException {
        super(x, y, "character");
        health = 3;
        points = 0;
    }

    /**
     * Decreases the character's health by 1.
     */
    public void loseHealth() {
        health--;
    }

    /**
     * Increases the character's points by 100.
     */
    public void gainPoints() {
        points += 100;
    }

    /**
     * Returns the current health of the character.
     *
     * @return the character's health.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Returns the current points of the character.
     *
     * @return the character's points.
     */
    public int getPoints() {
        return points;
    }
}
