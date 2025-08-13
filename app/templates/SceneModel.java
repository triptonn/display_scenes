package templates;

import interfaces.Attractor;
import interfaces.Inert;
import interfaces.Informative;
import interfaces.Moveable;
import interfaces.Renderable;
import interfaces.Updateable;
import objects.SceneObject;
import objects.SimpleLiquid;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;

import data.Vec;

/**
 * Central state holder and orchestrator for a scene.
 *
 * <p>Maintains registries of objects grouped by capabilities and defines
 * update and render ordering. Typical usage is to add domain objects via
 * {@link #addObject(objects.SceneObject)} and call {@link #update()} each
 * frame before delegating drawing to {@link #render(Graphics2D)}.</p>
 */
public class SceneModel {
	/** Whether to show optional debug/diagnostic overlays. */
    private boolean isShowComponents = false;

    private ArrayList<Attractor> attractors;
    private ArrayList<Informative> informatives;
    private ArrayList<Moveable> movers;
    private ArrayList<SceneObject> objects;
    private ArrayList<Renderable> renderers;
    private ArrayList<Updateable> updaters;

    private Dimension dim;
    private Vec origin;

    private Vec mousePos = origin;

	/**
	 * Create a model for a scene of the given dimensions.
	 *
	 * @param scene logical size of the scene in pixels
	 */
    public SceneModel(Dimension scene) {
        this.dim = scene;
        this.origin = new Vec(this.dim.width / 2, this.dim.height / 2);

        objects = new ArrayList<>();
        movers = new ArrayList<>();
        renderers = new ArrayList<>();
        updaters = new ArrayList<>();
    }

	/**
	 * Register a new object with the scene and index it by its capabilities.
	 *
	 * @param obj scene object to add
	 */
    public void addObject(SceneObject obj) {
        objects.add(obj);
        if (obj instanceof Moveable) {
            movers.add((Moveable) obj);
        }

        if (obj instanceof Updateable) {
            updaters.add((Updateable) obj);
        }

        if (obj instanceof Renderable) {
            renderers.add((Renderable) obj);
        }

        if (obj instanceof Informative) {
            informatives.add((Informative) obj);
        }
    }

	/**
	 * Advance the simulation by one tick.
	 *
	 * <p>Apply global forces, then call {@link Updateable#update()} for all
	 * registered updaters, and finally perform any per-object bookkeeping.</p>
	 */
    public void update() {
		for (int i = 0, n = movers.size(); i < n; i++) {
            // Gravity could go here
        }

        for (Updateable updater : updaters) {
            updater.update();
        }

		for (int i = 0, n = objects.size(); i < n; i++) {
            // for updates to all objects
        }
    }

	/**
	 * Render all visible objects in layers: background, actors, then overlays.
	 *
	 * @param g2d target graphics context
	 */
    public void render(Graphics2D g2d) {
        ArrayList<Renderable> background = new ArrayList<>();
        ArrayList<Renderable> liquidBodies = new ArrayList<>();
        ArrayList<Renderable> actors = new ArrayList<>();

        for (Renderable r : renderers) {
            if (r.isVisible()) {
                if (r instanceof Moveable) {
                    actors.add(r);
                }

                if (r instanceof SimpleLiquid) {
                    liquidBodies.add(r);
                }

                if (r instanceof Inert) {
                    background.add(r);
                }
            }
        }

        for (Renderable b : background) {
            b.render(g2d);
        }

        for (Renderable a : actors) {
            a.render(g2d);
        }

        for (Renderable l : liquidBodies) {
            l.render(g2d);
        }
    }

	/**
	 * Enable or disable optional component visualization.
	 * @param state true to show, false to hide
	 */
    public void setShowComponents(boolean state) {
        this.isShowComponents = state;
    }

	/** @return all registered scene objects */
    public ArrayList<SceneObject> getObjects() {
        return this.objects;
    }

	/** @return registered attractors (may be null if never initialized) */
    public ArrayList<Attractor> getAttractors() {
        return this.attractors;
    }

	/** @return registered informative overlays (may be null if never initialized) */
    public ArrayList<Informative> getInformatives() {
        return this.informatives;
    }

	/** @return whether debug components should be drawn */
    public boolean isShowComponents() {
        return isShowComponents;
    }

	/** @return scene dimensions */
    public Dimension getDimensions() {
        return this.dim;
    }

	/** @return scene origin (center point) */
    public Vec getOrigin() {
        return this.origin;
    }

	/** @return last known mouse position */
    public Vec getMousePos() {
        return this.mousePos;
    }

	/**
	 * Update the stored mouse position.
	 * @param pos new mouse position
	 */
    public void setMousePos(Vec pos) {
        this.mousePos = pos;
    }

	/** Toggle the component-visibility flag. */
    public void toggleShowComponents() {
        this.isShowComponents = !this.isShowComponents;
    }
}
