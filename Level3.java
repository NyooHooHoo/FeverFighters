import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Level3 extends Level {
    private final Timer timer;
    private int bgY;

    public Level3() throws IOException {
        super();

        Character c = new Character(260, 310);
        addSprite(c);
        this.bgY = 0;
        generateRow();

        timer = new Timer(100, e -> {
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

        String welcome = """
                Welcome to the final level! To survive your cold once and for all, escape this endless hall by running through collecting only good items (100 points each) until 5000 points. Avoid bad items, which will lose one of your three lives!


                \t\t[press ENTER to continue]""";
        setTextBox(welcome);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawInfo(g);
    }

    private void drawInfo(Graphics g) {
        g.setColor(new Color(252, 181, 228));
        g.fillRect(20, 430, 90, 70);
    }

    private void generateRow() throws IOException {
        String[] good = {"water", "bed", "soup", "cold-med"};
        String[] bad = {"chips", "pepper", "pill"};
        int[] template = new int[3];
        template[0] = (int) (Math.random() * 3) - 1;
        template[1] = (int) (Math.random() * 3) - 1;
        template[2] = template[0] == -1 && template[1] == -1 ? (int) (Math.random() * 2) : (int) (Math.random() * 3) - 1;

        for (int i = 0; i < template.length; i++) {
            if (template[i] != 0) {
                String type = template[i] > 0 ? good[(int) (Math.random() * good.length)] : bad[(int) (Math.random() * bad.length)];
                addSprite(new Item(170 + 100 * i, -100, type, template[i] < 0));
            }
        }

        repaint();
    }

    private void updateRows() throws IOException {
        for (int i = 0; i < getSprites().size() - 1; i++) {
            Sprite s = getSprites().get(i);
            s.setY(s.getY() + 10);
            if (s.getY() > 600) {
                getSprites().remove(s);
            }
        }
        if (getSprites().get(0).getY() > 0) {
            generateRow();
        }

        bgY += 10;
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
            if (((Character) c).getPoints() >= 5000) {
                timer.stop();

                String congrats = """
                        You've reached 5000 points! Blahblahblah...
                        
                        
                        \t\t[press ENTER to continue]""";
                setTextBox(congrats);
                setTextBoxVisible(true);

                removeKeyListener(getEnterAdapter());
                setEnterAdapter(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                            try {
                                fireLevelCompleteEvent();
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }
                });
                addKeyListener(getEnterAdapter());
            }
        }
        System.out.println(((Character) c).getHealth());
        System.out.println(((Character) c).getPoints());
    }
}
