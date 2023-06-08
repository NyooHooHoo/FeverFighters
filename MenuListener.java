import java.io.IOException;

public interface MenuListener {
    void startGame(int level) throws IOException;
    void levelSelect() throws IOException;
    void about() throws IOException;
    void exit() throws IOException;
    void returnMenu() throws IOException;
}
