import java.io.IOException;

public interface PanelListener {
    void levelComplete() throws IOException;
    void gameOver(String cause) throws IOException;
}
