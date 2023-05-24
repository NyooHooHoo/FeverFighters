public class Character extends Sprite {
    int health;
    int points;

    public Character(int x, int y, String img) {
        super(x, y, img);
        health = 3;
        points = 0;
    }
}
