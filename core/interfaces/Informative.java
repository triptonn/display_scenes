package interfaces;

import data.Vec;

/**
 * Minimal read/write access to an object's spatial information for UI overlays
 * and diagnostic renderers.
 */
public interface Informative {
    /**
     * Returns the current world-space position used for display.
     *
     * @return location vector
     */
    Vec getLocation();

    /**
     * Updates the world-space position used for display.
     *
     * @param loc new location
     */
    void setLocation(Vec loc);
}
