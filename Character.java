import java.io.IOException;

/**
 * The Character class represents a character sprite in a game.
 * It extends the Sprite class.
 */
public class Character extends Sprite {
    private int health;
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
