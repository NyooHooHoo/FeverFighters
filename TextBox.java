import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class TextBox extends JTextArea {
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

    @Override
    public boolean isFocusable() {
        return false;
    }


}