package interfaces;

import data.Vec;

/**
 * Contract for dynamic bodies that respond to forces and can translate and/or
 * rotate over time. Implementations typically maintain state such as location,
 * velocity, acceleration, orientation, and angular rates.
 *
 * <p>
 * Conventions:
 * </p>
 * <ul>
 * <li>Linear quantities: {@link Vec} values are in scene units per axis.</li>
 * <li>Angles are in radians; angular velocity/acceleration are represented as
 * 2D vectors where the magnitude encodes the scalar rate and the direction can
 * be ignored by simple scenes. Implementations may choose to interpret the
 * vector direction if needed.</li>
 * <li>Mass is a positive scalar. Forces are applied using F = m · a.</li>
 * <li>Friction/drag flags and coefficients allow scenes to enable or disable
 * simple force models.</li>
 * </ul>
 */
public interface Moveable {
    /**
     * Applies a force to the body, accumulating into its linear acceleration for
     * the next integration step.
     *
     * @param force external force vector
     */
    void applyForce(Vec force);

    /**
     * Applies an angular impulse-like quantity, accumulating into angular
     * acceleration for the next integration step.
     *
     * @param momentum angular momentum/impulse proxy
     */
    void applyMomentum(Vec momentum);

    /**
     * Current world-space position of the body's reference point.
     *
     * @return location vector
     */
    Vec getLocation();

    /**
     * Current linear velocity.
     *
     * @return velocity vector
     */
    Vec getVelocity();

    /**
     * Current accumulated linear acceleration.
     *
     * @return acceleration vector
     */
    Vec getAcceleration();

    /**
     * Orientation angle in radians.
     *
     * @return angle in radians
     */
    double getAngle();

    /**
     * Current angular velocity representation.
     *
     * @return angular velocity vector (magnitude as scalar rate)
     */
    Vec getAngularVelocity();

    /**
     * Sets the current angular velocity representation.
     *
     * @param angularVel angular velocity vector
     */
    void setAngularVelocity(Vec angularVel);

    /**
     * Current angular acceleration representation.
     *
     * @return angular acceleration vector (magnitude as scalar rate)
     */
    Vec getAngularAcceleration();

    /**
     * Body mass.
     *
     * @return mass (must be positive)
     */
    double getMass();

    /**
     * Sets body mass.
     *
     * @param m mass (must be positive)
     */
    void setMass(double m);

    /**
     * Whether the body should bounce on collisions in simple scenes.
     *
     * @return {@code true} if bouncy
     */
    boolean isBouncy();

    /**
     * Enables/disables simple bouncing behavior.
     *
     * @param bouncy {@code true} to enable bouncing
     */
    void setBouncy(boolean bouncy);

    /**
     * Sets the bounce restitution factor in [0, 1].
     *
     * @param factor coefficient of restitution
     */
    void setBounceFactor(double factor);

    /**
     * Whether this body acts as a gravitational attractor in scenes that support
     * it.
     *
     * @return {@code true} if the body attracts others
     */
    boolean isAttractor();

    /**
     * Toggles whether this body acts as an attractor.
     *
     * @param isAttractor {@code true} to mark as attractor
     */
    void setAttractor(boolean isAttractor);

    /**
     * Whether the body is currently resting on a surface.
     *
     * @return {@code true} if landed
     */
    boolean isLanded();

    /**
     * Whether the body is currently sliding along a surface.
     *
     * @return {@code true} if sliding
     */
    boolean isSliding();

    /**
     * Whether friction forces should be applied to this body by the scene.
     *
     * @return {@code true} if friction is enabled
     */
    boolean getHasFriction();

    /**
     * Enables/disables friction forces for this body.
     *
     * @param hasFriction {@code true} to enable friction
     */
    void setHasFriction(boolean hasFriction);

    /**
     * Coefficient controlling the magnitude of friction applied by scenes.
     *
     * @return friction coefficient (≥ 0)
     */
    double getFrictionCoefficient();

    /**
     * Sets the friction coefficient.
     *
     * @param coefficient non-negative friction coefficient
     */
    void setFrictionCoefficient(double coefficient);

    /**
     * Whether quadratic drag forces should be applied.
     *
     * @return {@code true} if drag is enabled
     */
    boolean getHasDrag();

    /**
     * Enables/disables quadratic drag forces.
     *
     * @param hasDrag {@code true} to enable drag
     */
    void setHasDrag(boolean hasDrag);

    /**
     * Coefficient for quadratic drag magnitude.
     *
     * @return drag coefficient (≥ 0)
     */
    double getDragCoefficient();

    /**
     * Sets the drag coefficient.
     *
     * @param coefficient non-negative drag coefficient
     */
    void setDragCoefficient(double coefficient);
}
