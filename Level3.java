import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Represents the Level 3 of the game.
 * Extends the Level class and introduces new features specific to this level.
 */
public class Level3 extends Level {
    /**
     * Timer to control animation of Items
     */
    private final Timer timer;
    /**
     * The y-coordinate of the background
     */
    private int bgY;
    /**
     * The number of pixels the screen shifts each Timer tick
     */
    private static final int shift = 4;

    /**
     * Constructs a new Level3 object.
     * Initializes the level by creating a Character object, setting the background position,
     * generating the initial row of items, and setting up the timer for the continuous movement of rows.
     * Adds the character object to the list of sprites and displays a welcome message.
     *
     * @throws IOException if there is an error loading the required image files.
     */
    public Level3() throws IOException {
        super();

        Character c = new Character(260, 310);
        addSprite(c);
        this.bgY = 0;
        generateRow();

        timer = new Timer(20, e -> {
            try {
                updateRows();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            repaint();
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    timer.start();
                    removeKeyListener(this);
                }
            }
        });

        getButton().addActionListener(e -> timer.stop());

        String welcome = """
                Welcome to the final level! To survive your cold once and for all, escape this endless hall by running through collecting only good items (worth 100 points each) until 10,000 points. Avoid bad items, which will lose one of your three lives!



                \t                [press ENTER to continue]""";
        setTextBox(welcome);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            drawInfo(g);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Draws additional information on the screen, such as the score and health.
     *
     * @param g the Graphics object used for drawing.
     * @throws IOException if there is an error loading the required image files.
     */
    private void drawInfo(Graphics g) throws IOException {
        // Draw background rectangles
        g.setColor(new Color(173, 222, 243));
        g.fillRect(20, 425, 110, 60);
        g.fillRect(470, 425, 115, 60);

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));
        g.setColor(Color.black);
        g.drawRect(20, 425, 110, 60);
        g.drawRect(470, 425, 115, 60);

        // Draw text labels
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString("Points", 504, 445);
        g.drawString("Health", 53, 445);

        // Draw points and health values
        g.setFont(new Font("Arial", Font.BOLD, 24));
        Character c = (Character) getSprites().get(getSprites().size()-1);
        String points = String.format("%05d", c.getPoints());
        g.drawString(points, 495, 473);

        // Load heart images and draw hearts based on character's health
        BufferedImage heart = ImageIO.read(new File("assets/heart.png"));
        BufferedImage emptyHeart = ImageIO.read(new File("assets/empty-heart.png"));

        for (int i = 1; i <= 3; i++) {
            if (c.getHealth() >= i)
                g.drawImage(heart, 30 * i + 3, 450, this);
            else
                g.drawImage(emptyHeart, 30 * i + 3, 450, this);
        }
    }

    /**
     * Generates a new row of items based on the previous row's configuration.
     * The items can be good (worth 100 points) or bad (decrease health).
     * The generation process takes into account the previous row's configuration to ensure balanced gameplay.
     *
     * @throws IOException if there is an error loading the required image files.
     */
    private void generateRow() throws IOException {
        String[] good = {"water", "bed", "soup", "cold-med"};
        String[] bad = {"chips", "pepper", "pill"};

        // Check previous row's configuration
        boolean prev1 = false;
        boolean prev2 = false;
        boolean prev3 = false;
        boolean prev4 = false;
        boolean prev6 = false;

        for (int i = 0; i < getSprites().size() - 1; i++) {
            Item item = (Item) getSprites().get(i);
            if (item.getX() == 170 && item.getY() == shift) {
                prev3 = item.isBad();
            } else if (item.getX() == 270 && item.getY() == shift) {
                prev2 = item.isBad();
            } else if (item.getX() == 370 && item.getY() == shift) {
                prev1 = item.isBad();
            } else if (item.getX() == 170 && item.getY() == 120 + 2 * shift) {
                prev6 = item.isBad();
            } else if (item.getX() == 370 && item.getY() == 120 + 2 * shift) {
                prev4 = item.isBad();
            }
        }

        // Generate new row based on the previous row's configuration
        int[] template = new int[3];
        template[0] = (prev1 || prev4) && prev2 ? (int) (Math.random() * 2) : (int) (Math.random() * 3) - 1;
        template[1] = prev1 && (prev3 || template[0] == -1) ? (int) (Math.random() * 2) : (int) (Math.random() * 3) - 1;
        template[2] = ((prev2 || template[1] == -1) && (prev3 || template[0] == -1)) || (prev2 && prev6)
                ? (int) (Math.random() * 2) : (int) (Math.random() * 3) - 1;

        for (int i = 0; i < template.length; i++) {
            if (template[i] != 0) {
                String type = template[i] > 0 ? good[(int) (Math.random() * good.length)] : bad[(int) (Math.random() * bad.length)];
                addSprite(new Item(170 + 100 * i, -120, type, template[i] < 0));
            }
        }

        repaint();
    }

    /**
     * Updates the positions of the rows and checks for collisions with the character.
     * If a row goes off the screen, a new row is generated.
     *
     * @throws IOException if there is an error loading the required image files.
     */
    private void updateRows() throws IOException {
        // Update row positions
        for (int i = 0; i < getSprites().size() - 1; i++) {
            Sprite s = getSprites().get(i);
            s.setY(s.getY() + shift);
            if (s.getY() > 600) {
                getSprites().remove(s);
            }
        }
        if (getSprites().get(0).getY() > 0) {
            generateRow();
        }

        // Update background position
        bgY += shift;
        if (bgY >= 500) {
            bgY = 0;
        }

        checkCollisions();
        repaint();
    }

    @Override
    public void drawBackground(Graphics g) throws IOException {
        BufferedImage bg = ImageIO.read(new File("assets/bg3.png"));
        g.drawImage(bg, 0, bgY, this);
        g.drawImage(bg, 0, bgY - 500, this);
    }

    @Override
    public void move(String direction) throws IOException {
        Sprite c = getSprites().get(getSprites().size() - 1);
        switch (direction) {
            case "left" -> {
                if (c.getX() != 160)
                    c.setX(c.getX() - 100);
            }
            case "right" -> {
                if (c.getX() != 360)
                    c.setX(c.getX() + 100);
            }
        }
        checkCollisions();
        repaint();
    }

    @Override
    public void collide(Sprite c, Sprite s) throws IOException {
        getSprites().remove(s);
        if (((Item) s).isBad()) {
            ((Character) c).loseHealth();
            if (((Character) c).getHealth() == 0) {
                timer.stop();
                fireGameOverEvent(s.getType());
            }
        }
        else {
            ((Character) c).gainPoints();
            if (((Character) c).getPoints() >= 10000) {
                timer.stop();
                fireLevelCompleteEvent();
            }
        }
    }
}
