import java.io.IOException;

/**
 * The AnimationListener interface provides callbacks for animation-related events.
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

