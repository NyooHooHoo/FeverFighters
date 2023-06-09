import java.io.IOException;

/**
 * The MenuListener interface provides callbacks for menu-related events.
 *
 * <h2>Course Info:</h2>
 * ICS4U0 with V. Krasteva
 *
 * @author Aidan Wang
 * @version 4
 * @date 06/07/2023
 */
public interface MenuListener {
    /**
     * Notifies when the user wants to start a game at the specified level.
     *
     * @param level the level to start the game from.
     * @throws IOException if an IO error occurs.
     */
    void startGame(int level) throws IOException;

    /**
     * Notifies when the user wants to access the level selection menu.
     *
     * @throws IOException if an IO error occurs.
     */
    void levelSelect() throws IOException;

    /**
     * Notifies when the user wants to access the "About" menu.
     *
     * @throws IOException if an IO error occurs.
     */
    void about() throws IOException;

    /**
     * Notifies when the user wants to exit the game.
     *
     * @throws IOException if an IO error occurs.
     */
    void exit() throws IOException;

    /**
     * Notifies when the user wants to return to the main menu.
     *
     * @throws IOException if an IO error occurs.
     */
    void returnMenu() throws IOException;
}
