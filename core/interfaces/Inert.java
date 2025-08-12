package interfaces;

import data.Vec;

/**
 * Contract for non-accelerating, kinematic or static objects that expose their
 * pose (position and orientation) but do not accept forces.
 */
public interface Inert {
    /**
     * Returns the object's world-space position.
     *
     * @return location vector
     */
    Vec getLocation();

    /**
     * Sets the object's world-space position.
     *
     * @param loc new location
     */
    void setLocation(Vec loc);

    /**
     * Returns the orientation angle in radians.
     *
     * @return angle in radians
     */
    double getAngle();

    /**
     * Sets the orientation angle in radians.
     *
     * @param angle angle in radians
     */
    void setAngle(double angle);
}
