import java.io.IOException;

public interface LevelListener {
    void levelComplete() throws IOException;
    void gameOver() throws IOException;
}
