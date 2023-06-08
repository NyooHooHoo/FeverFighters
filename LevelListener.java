import java.io.IOException;

public interface LevelListener {
    void levelComplete() throws IOException;
    void gameOver(String cause) throws IOException;
    void returnMenu() throws IOException;
}
