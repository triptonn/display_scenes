package interfaces;

/**
 * Contract for objects whose internal state advances over time.
 *
 * <p>
 * Scene controllers call {@link #update()} on each simulation tick (often once
 * per animation frame) to progress physics, AI, timers, or other time-based
 * behaviors before rendering. Implementations should keep this method
 * non-blocking and avoid long-running I/O; timing and frame cadence are managed
 * by the caller.
 * </p>
 *
 * <ul>
 * <li>Typical order of operations in a frame: update → render.</li>
 * <li>Do not call {@code Thread.sleep} or otherwise delay inside this method.</li>
 * <li>Threading model is scene-dependent; most scenes invoke updates on a
 * single simulation thread.</li>
 * </ul>
 */
public interface Updateable {
    /**
     * Advances this object's state by one simulation tick.
     */
    void update();
}
