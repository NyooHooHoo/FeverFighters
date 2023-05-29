import javax.swing.*;
import java.io.IOException;

public class Test extends JFrame {
    public Test() throws IOException {
        setTitle("Testing Window");
        setSize(600, 527);
        add(new Level(1));
        setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        new Test();
    }
}
