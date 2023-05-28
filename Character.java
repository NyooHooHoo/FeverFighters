import java.io.IOException;

public class Character extends Sprite {
    private int health;
    private int points;

    public Character(int x, int y) throws IOException {
        super(x, y, "character");
        health = 3;
        points = 0;
    }

    public void loseHealth() {
        health--;
    }

    public void gainPoints() {
        points += 100;
    }

    public int getHealth() {
        return health;
    }

    public int getPoints() {
        return points;
    }
}
