import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class Sprite {
    private int x, y; // x and y coordinates of the top-left of the Sprite
    private int height, width; // the height and width of the Sprite's image
    private final String type; // the type of sprite
    private BufferedImage img;
    private Rectangle hitbox;

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

    public boolean collideWithSprite(Sprite s) {
        return this.hitbox.x < s.hitbox.x + s.hitbox.width && this.hitbox.x + this.hitbox.width > s.hitbox.x
                && this.hitbox.y < s.hitbox.y + s.hitbox.height && this.hitbox.y + this.hitbox.height > s.hitbox.y;
    }

    public void draw(Graphics g, ImageObserver observer) {
        g.drawImage(img, x, y, observer);
//        Graphics2D g2 = (Graphics2D) g;
//        g2.setStroke(new BasicStroke(1));
//        g.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
    }

    public String getType() {
        return type;
    }

    public BufferedImage getImg() {
        return img;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
        if (!type.equals("wall"))
            this.hitbox.x = this.x + this.width / 6;
    }

    public void setY(int y) {
        this.y = y;
        if (!type.equals("wall")) {
            this.hitbox.y = this.getType().equals("character") ? (this.y + this.height / 2) :
                    (this.y + this.height / (this.getType().equals("pepper") ? 3 : 4));
        }
    }
}
