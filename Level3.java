import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Level3 extends Level {
    public Level3() throws IOException {
        super();

        Character c = new Character(260, 310);
        addSprite(c);

        String welcome = """
                Welcome to the final level! To survive your cold once and for all, escape this endless hall by running through collecting only good items (100 points each) until 20,000 points. Avoid bad items, which will lose one of your three lives!


                \t\t[press ENTER to continue]""";
        setTextBox(welcome);
    }

    @Override
    public void drawBackground(Graphics g) throws IOException {
        BufferedImage bg = ImageIO.read(new File("assets/bg3.png"));
        g.drawImage(bg, 0, 0, this);
    }

    @Override
    public void move(String direction) throws IOException {

    }

    @Override
    public void collide(Sprite c, Sprite s) throws IOException {

    }
}
