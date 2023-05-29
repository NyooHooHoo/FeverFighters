import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Sprite {
    private int x, y; // x and y coordinates of the top-left of the Sprite
    private final int height, width; // the height and width of the Sprite's hit box
    private final String type; // the type of sprite
    private final BufferedImage img;

    public Sprite(int x, int y, String type) throws IOException {
        this.x = x;
        this.y = y;
        this.type = type;
        this.img = ImageIO.read(new File("assets/" + type + ".png"));
        this.height = (int) (getImg().getHeight() * 0.8);
        this.width = (int) (getImg().getWidth() * 0.5);
    }

    public boolean collideWithSprite(Sprite s) {
        int x1 = this.x;
        int x2 = this.x + this.width;
        int y1 = this.y + (int) (this.height * 0.9);
        int y2 = this.y + this.height;
        int x3 = s.x;
        int x4 = s.x + s.width;
        int y3 = s.y;
        int y4 = s.y + s.height;

        // no collide with area 0
        if (x1 == x2 || y1 == y2 || x3 == x4 || y3 == y4) {
            return false;
        }
        // no collide if to the side
        if (x1 > x4 || x2 < x3) {
            return false;
        }

        // no collide if above or below
        return y1 <= y4 && y2 >= y3;
    }

    public String getType() {
        return type;
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
