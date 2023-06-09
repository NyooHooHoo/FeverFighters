import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.IOException;

/**
 * The MenuPanel class represents a panel used in the game menu.
 * It extends the JPanel class.
 */
public class MenuPanel extends JPanel {
    /**
     * Font for the title
     */
    private final Font titleFont = new Font("SansSerif", Font.BOLD, 50);
    /**
     * Font for the buttons
     */
    private final Font optionsFont = new Font("Monospace", Font.BOLD, 30);
    /**
     * Color for the background
     */
    private final Color pink = new Color(255, 200, 221);
    /**
     * Color for the button fill
     */
    private final Color lightBlue = new Color(189, 224, 254);
    /**
     * Color for the button border
     */
    private final Color blue = new Color(162, 210, 255);
    /**
     * MenuListener to fire events when buttons clicked
     */
    private MenuListener menuListener;

    /**
     * Overrides the paintComponent method to set the background color of the panel.
     * It fills the panel with a pink color.
     *
     * @param g the Graphics object used for painting
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(pink);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    /**
     * Adds a title label to the panel at the specified row and column.
     * Sets the font of the label to the title font.
     *
     * @param label  the JLabel to add as the title
     * @param row    the row index for the grid layout
     * @param column the column index for the grid layout
     */
    public void addTitle(JLabel label, int row, int column) {
        label.setFont(titleFont);
        add(label, createConstraints(row, column));
    }

    /**
     * Adds a button to the panel at the specified row and column.
     * Sets the preferred size, background color, font, and border of the button.
     *
     * @param button the JButton to add
     * @param row    the row index for the grid layout
     * @param column the column index for the grid layout
     */
    public void addButton(JButton button, int row, int column) {
        button.setPreferredSize(new Dimension(340, 60));
        button.setUI(new GameButtonUI(blue));
        button.setBackground(lightBlue);
        button.setFont(optionsFont);
        button.setFocusPainted(false);
        button.setBorder(new LineBorder(blue, 4));
        add(button, createConstraints(row, column));
    }

    /**
     * Creates and returns GridBagConstraints for the specified row and column.
     * Sets the grid indices and adds vertical spacing between buttons.
     *
     * @param row    the row index for the grid layout
     * @param column the column index for the grid layout
     * @return the created GridBagConstraints
     */
    public GridBagConstraints createConstraints(int row, int column) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = column;
        constraints.gridy = row;
        constraints.insets = new Insets(0, 0, 20, 0); // Add vertical spacing between buttons
        return constraints;
    }

    /**
     * Adds a MenuListener to the MenuPanel.
     *
     * @param listener the MenuListener to add
     */
    public void addMenuListener(MenuListener listener) {
        this.menuListener = listener;
    }

    /**
     * Triggers the start game event with the specified level.
     *
     * @param level the level to start the game with
     * @throws IOException if an I/O error occurs during the event handling
     */
    public void startGameEvent(int level) throws IOException {
        menuListener.startGame(level);
    }

    /**
     * Triggers the level select event.
     *
     * @throws IOException if an I/O error occurs during the event handling
     */
    public void levelSelectEvent() throws IOException {
        menuListener.levelSelect();
    }

    /**
     * Triggers the about event.
     *
     * @throws IOException if an I/O error occurs during the event handling
     */
    public void aboutEvent() throws IOException {
        menuListener.about();
    }

    /**
     * Triggers the exit event.
     *
     * @throws IOException if an I/O error occurs during the event handling
     */
    public void exitEvent() throws IOException {
        menuListener.exit();
    }

    /**
     * Triggers the return to menu event.
     *
     * @throws IOException if an I/O error occurs during the event handling
     */
    public void returnMenuEvent() throws IOException {
        menuListener.returnMenu();
    }
}
