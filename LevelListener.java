import java.io.IOException;

/**
 * The LevelListener interface provides callbacks for level-related events.
 */
public interface LevelListener {
    /**
     * Notifies when a level is completed.
     *
     * @throws IOException if an IO error occurs.
     */
    void levelComplete() throws IOException;

    /**
     * Notifies when the game is over with the specified cause.
     *
     * @param cause the cause of the game over.
     * @throws IOException if an IO error occurs.
     */
    void gameOver(String cause) throws IOException;

    /**
     * Notifies when the user wants to return to the main menu.
     *
     * @throws IOException if an IO error occurs.
     */
    void returnMenu() throws IOException;
}
