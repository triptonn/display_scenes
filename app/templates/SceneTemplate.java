package templates;

import javax.swing.Timer;

import data.Vec;

import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

/**
 * Minimal runnable example application built on the scene framework.
 *
 * <p>Creates a window, wires basic input, and runs a ~60 FPS {@link Timer}
 * that drives updates and repaints. Extend {@link #setupScene()} and
 * {@link #updateSceneState()} to build an interactive scene.</p>
 */
public class SceneTemplate {

	/** Default window size. */
    public Dimension dim = new Dimension(1280, 720);

    private Timer sceneTimer;
    private final SceneModel model;
    private final ScenePanel panel;
    private final JFrame frame;

	/**
	 * Initialize the window, model, panel, and basic input handlers.
	 */
    public SceneTemplate() {
        model = new SceneModel(this.dim);
        panel = new ScenePanel(model);
        frame = new JFrame("Scene");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);

        panel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                model.setMousePos(new Vec(e.getX(), e.getY()));
                panel.repaint();
            }
        });

        panel.setFocusable(true);
        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    model.toggleShowComponents();
                    panel.repaint();
                }
            }
        });
    }

	/** Start the ~60 FPS loop and show the window. */
    public void startLoop() {
        if (sceneTimer == null) {
            sceneTimer = new Timer(16, e -> {
                updateSceneState();
                panel.repaint();
            });
        }
        sceneTimer.start();
        frame.setVisible(true);
    }

	/** Stop the loop if running. */
    public void stopLoop() {
        if (sceneTimer != null) {
            sceneTimer.stop();
        }
    };

	/** Place per-frame game logic here. */
    private void updateSceneState() {
        // Game logic
    };

	/** Construct and register your scene objects here. */
    private void setupScene() {
        // Scene setup
    }

    public static void main(String[] args) {
        SceneTemplate st = new SceneTemplate();
        st.setupScene();
        st.startLoop();
    }

}
