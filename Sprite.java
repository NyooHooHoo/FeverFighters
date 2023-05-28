import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Sprite {
    private int x, y; // x and y coordinates of the top-left of the Sprite
    private int height, width; // the height and width of the Sprite's hitbox
    private BufferedImage img;

    public Sprite(int x, int y, String img) throws IOException {
        this.x = x;
        this.y = y;
        this.img = ImageIO.read(new File("assets/" + img + ".png"));
    }

    public boolean collideWithSprite(Sprite s) {
        int x1 = this.x;
        int x2 = this.x + this.width;
        int y1 = this.y;
        int y2 = this.y + this.height;
        int x3 = s.x;
        int x4 = s.x + s.width;
        int y3 = s.y;
        int y4 = s.y + s.height;

        // no collide with area 0
        if (x1 == x2 || y1 == y2 || x3 == x4 || y3 == y4)
            return false;
        // no collide if to the side
        if (x1 > x4 || x2 > x3)
            return false;
        // no collide if above
        return y1 <= y4 && y2 <= y3;
    }

    public BufferedImage getImg() {
        return img;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
