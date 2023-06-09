import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * The TextBox class represents a text area for displaying text content.
 *
 * <h2>Time Spent</h2>
 * less than 1 hour
 *
 * <h2>Course Info:</h2>
 * ICS4U0 with V. Krasteva
 *
 * @author Aidan Wang
 * @version 3
 */
public class TextBox extends JTextArea {
    /**
     * Constructs a TextBox with the specified number of rows and columns.
     *
     * @param rows    the number of rows in the text area
     * @param columns the number of columns in the text area
     */
    public TextBox(int rows, int columns) {
        super(rows, columns);
        setBackground(new Color(237, 204, 202));
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        setLineWrap(true);
        setWrapStyleWord(true);
        setEditable(false);
    }

    /**
     * Overrides the isFocusable() method to make the TextBox non-focusable.
     *
     * @return false to indicate that the TextBox is not focusable
     */
    @Override
    public boolean isFocusable() {
        return false;
    }
}
