package templates;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

/**
 * Swing panel that hosts and paints a {@link SceneModel} using
 * a {@link SceneRenderer}.
 */
public class ScenePanel extends JPanel {
    private final SceneModel model;
    private final SceneRenderer renderer;

	/**
	 * Construct a panel for the given model.
	 *
	 * @param model scene state to render
	 */
    public ScenePanel(SceneModel model) {
        this.model = model;
        this.renderer = new SceneRenderer(this.model.getDimensions());

        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.black);
    }

    @Override
	/**
	 * Paint callback; clears background and delegates to renderer.
	 */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        renderer.render(g2d, model);
    }
}
