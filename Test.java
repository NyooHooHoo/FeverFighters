import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Test extends JFrame {
    public Test() throws IOException {
        setTitle("Testing Window");
        setSize(600, 500);
        add(new Level(1));
        setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        new Test();
    }
}
