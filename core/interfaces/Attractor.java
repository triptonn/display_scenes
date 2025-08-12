package interfaces;

import data.Vec;

/**
 * Capability for producing an attractive force on a {@link Moveable} body.
 *
 * <p>
 * Implementations may compute forces based on distance, mass, or other
 * properties. Returned forces are expected to be in world-space coordinates and
 * can be fed directly into {@link Moveable#applyForce(Vec)}.
 * </p>
 */
public interface Attractor {
    /**
     * Computes the attraction force acting on the provided body.
     *
     * @param m target body
     * @return attraction force vector to apply on {@code m}
     */
    Vec attract(Moveable m);
}
