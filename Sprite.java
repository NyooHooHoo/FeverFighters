public class Sprite {
    int x, y; // x and y coordinates of the center of the Sprite
    int height, width; // the height and width of the Sprite's hitbox

    public Sprite(int x, int y, String img) {
        this.x = x;
        this.y = y;
        // get image and set height and width based on image
    }

    public boolean collideWithSprite(Sprite s) {
        int x1 = this.x - (this.width / 2);
        int x2 = this.x + (this.width / 2);
        int y1 = this.y - (this.height / 2);
        int y2 = this.y + (this.height / 2);
        int x3 = s.x - (s.width / 2);
        int x4 = s.x + (s.width / 2);
        int y3 = s.y - (s.height / 2);
        int y4 = s.y + (s.height / 2);

        // no collide with area 0
        if (x1 == x2 || y1 == y2 || x3 == x4 || y3 == y4)
            return false;
        // no collide if to the side
        if (x1 > x4 || x2 > x3)
            return false;
        // no collide if above
        return y1 <= y4 && y2 <= y3;
    }
}
