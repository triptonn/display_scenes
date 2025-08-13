package templates;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import data.Vec;

/**
 * Renders a scene to a {@link Graphics2D} context.
 *
 * <p>Responsibilities:</p>
 * <ul>
 * 	<li>Clear the canvas with a background color</li>
 * 	<li>Delegate drawing to the provided {@link SceneModel}</li>
 * 	<li>Provide small drawing utilities (e.g., vector arrows)</li>
 * </ul>
 */
public class SceneRenderer {
	/**
	 * Dimensions of the drawing surface; used for full-canvas operations.
	 */
    private Dimension scene;
	/**
	 * Base size (in pixels) for vector arrow heads.
	 */
    private static final int ARROW_SIZE = 10;

	/**
	 * Create a new renderer for a given scene size.
	 *
	 * @param scene dimensions of the drawing surface
	 */
    public SceneRenderer(Dimension scene) {
        this.scene = scene;
    }

	/**
	 * Clear the background and delegate rendering to the model.
	 *
	 * @param g2d   target graphics context
	 * @param model scene model that knows how to render its contents
	 */
    public void render(Graphics2D g2d, SceneModel model) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, (int) this.scene.getWidth(), (int) this.scene.getHeight());
        model.render(g2d);
    }

	/**
	 * Draw a vector arrow from an origin point to {@code origin + vector}.
	 *
	 * @param g2d     target graphics context
	 * @param origin  starting point of the arrow
	 * @param vector  displacement to draw from the origin
	 * @param color   color of the arrow
	 */
    public void drawVec(Graphics2D g2d, Vec origin, Vec vector, Color color) {
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(2));

        Vec end = origin.plus(vector);

        g2d.drawLine((int) origin.x(), (int) origin.y(),
                (int) end.x(), (int) end.y());

        drawArrowHead(g2d, origin, end);
    }

	/**
	 * Draw a filled triangular arrow head at the end of a line from
	 * {@code start} to {@code end}.
	 *
	 * @param g2d   target graphics context
	 * @param start start of the line
	 * @param end   end of the line where the arrow head is drawn
	 */
    private void drawArrowHead(Graphics2D g2d, Vec start, Vec end) {
        Vec direction = end.minus(start);
        Vec normalized = direction.norm();

        Vec perp = new Vec(-normalized.y(), normalized.x());

        Vec arrow1 = end.minus(normalized.scale(ARROW_SIZE))
                .plus(perp.scale(ARROW_SIZE / 2));
        Vec arrow2 = end.minus(normalized.scale(ARROW_SIZE))
                .minus(perp.scale(ARROW_SIZE / 2));

        int[] xPoints = { (int) end.x(), (int) arrow1.x(), (int) arrow2.x() };
        int[] yPoints = { (int) end.y(), (int) arrow1.y(), (int) arrow2.y() };
        g2d.fillPolygon(xPoints, yPoints, 3);
    }

}
