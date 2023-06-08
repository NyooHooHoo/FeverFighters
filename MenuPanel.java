import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.IOException;

public class MenuPanel extends JPanel {
    private final Font titleFont = new Font("SansSerif", Font.BOLD, 50);
    private final Font optionsFont = new Font("Monospace", Font.BOLD, 30);
    private final Color pink = new Color(255, 200, 221);
    private final Color lightBlue = new Color(189, 224, 254);
    private final Color blue = new Color(162, 210, 255);
    private MenuListener menuListener;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(pink);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    public void addTitle(JLabel label, int row, int column) {
        label.setFont(titleFont);
        add(label, createConstraints(row, column));
    }

    public void addButton(JButton button, int row, int column) {
        button.setPreferredSize(new Dimension(340, 60));
        button.setUI(new GameButtonUI(blue));
        button.setBackground(lightBlue);
        button.setFont(optionsFont);
        button.setFocusPainted(false);
        button.setBorder(new LineBorder(blue, 4));
        add(button, createConstraints(row, column));
    }

    public GridBagConstraints createConstraints(int row, int column) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = column;
        constraints.gridy = row;
        constraints.insets = new Insets(0, 0, 20, 0); // Add vertical spacing between buttons
        return constraints;
    }

    public void addMenuListener(MenuListener listener) {
        this.menuListener = listener;
    }

    public void startGameEvent(int level) throws IOException {
        menuListener.startGame(level);
    }

    public void levelSelectEvent() throws IOException {
        menuListener.levelSelect();
    }

    public void aboutEvent() throws IOException {
        menuListener.about();
    }

    public void exitEvent() throws IOException {
        menuListener.exit();
    }

    public void returnMenuEvent() throws IOException {
        menuListener.returnMenu();
    }
}
