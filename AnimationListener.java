import java.io.IOException;

/**
 * The AnimationListener interface provides callbacks for animation-related events.
 *
 * <h2>Course Info:</h2>
 * ICS4U0 with V. Krasteva
 *
 * @author Aidan Wang
 * @version 4
 * @date 06/07/2023
 */
public interface AnimationListener {
    /**
     * Notifies when the menu should be loaded.
     *
     * @throws IOException if an IO error occurs.
     */
    void loadMenu() throws IOException;

    /**
     * Notifies when the animation should be closed.
     */
    void close();
}

