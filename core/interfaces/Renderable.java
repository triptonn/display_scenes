package interfaces;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Contract for objects that can be drawn onto a {@link Graphics2D} context and
 * expose minimal visual state.
 *
 * <p>
 * Implementations are free to choose their own local origin and rotation
 * pivot. Scene renderers typically call {@link #render(Graphics2D)} once per
 * animation frame. Unless otherwise stated by an implementation, the following
 * conventions apply:
 * </p>
 *
 * <ul>
 * <li>Angles are measured in radians and rotate counter-clockwise in the
 * screen plane.</li>
 * <li>Visibility toggles whether {@link #render(Graphics2D)} should draw
 * anything.</li>
 * <li>Color is the preferred fill/stroke color for the object.</li>
 * </ul>
 */
public interface Renderable {
    /**
     * Draws this object to the provided graphics context in world coordinates.
     *
     * @param g2d target drawing context; the caller controls any transforms
     *            (translate/rotate/scale) applied before invocation
     */
    void render(Graphics2D g2d);

    /**
     * Returns the object's rotation angle in radians.
     *
     * @return angle in radians, typically applied around the object's local pivot
     */
    double getAngle();

    /**
     * Sets the object's rotation angle in radians.
     *
     * @param angle angle in radians
     */
    void setAngle(double angle);

    /**
     * Indicates whether this object should currently be drawn.
     *
     * @return {@code true} if visible; {@code false} otherwise
     */
    boolean isVisible();

    /**
     * Sets whether this object should be drawn.
     *
     * @param visible {@code true} to draw; {@code false} to skip rendering
     */
    void setVisible(boolean visible);

    /**
     * Returns the preferred drawing color for this object.
     *
     * @return current color
     */
    Color getColor();

    /**
     * Sets the preferred drawing color for this object.
     *
     * @param color new color (non-null)
     */
    void setColor(Color color);
}
