import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.IOException;

public class Wall extends Sprite {
    private final int length;
    private final String direction;

    public Wall(int x, int y, int length, String direction) throws IOException {
        super(x, y, "wall");
        this.length = length;
        this.direction = direction;
    }

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
