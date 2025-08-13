### MouseHunter

Interactive demo where a ball is gently pulled toward the mouse cursor while showing live velocity and acceleration vectors using the DisplayScenes framework.

#### What it demonstrates
- **Cursor-seeking behavior**: computes a direction `mouse - position` and applies a small force toward the cursor each tick.
- **Bouncy bounds**: the ball toggles bounce at window edges.
- **Informative overlays**: two `objects.VectorArrow` instances render velocity and acceleration vectors.
- **Update and render pipeline**: `SceneModel#update` and `SceneModel#render` batched by capability.

#### How to run
```bash
java -cp bin mouse_hunter.MouseHunter
```

#### Controls
- **Move mouse**: ball steers toward the cursor.
- **Space**: toggle component overlay (in this scene, it refreshes visuals; model flag is available for use).
- **A**: apply a small leftward impulse to the ball.

#### Scene setup
- One `Ball` centered, visible, and bouncy: `radius = 15`, `mass = 5.0`, `color = Color.orange`.
- Two `VectorArrow` overlays anchored at the ball's position:
  - `"Acc"` shows acceleration scaled up (`acc * 10000`).
  - `"Vel"` shows velocity scaled (`vel * 100`).
- The model records `ballPos`, `ballVel`, `ballAcc` each frame and updates arrow vectors and positions accordingly.

#### Forces per tick (core loop)
- Compute `direction = mousePos - ballPos`.
- If `direction.mag() > 0`, apply `dragToCursor = direction.norm().scale(0.05)` to the ball.
- Bounce when the ball crosses window edges.

#### File map
- `MouseHunter.java`: window, input (mouse move, keys), main loop (`startLoop`, `updateScene`, `setupScene`).
- `SceneModel.java`: object lists, per-tick behavior (seek mouse, update arrows, bounce), draw order.
- `ScenePanel.java`: lightweight `JPanel` delegating to renderer.
- `SceneRenderer.java`: clears the frame and renders the model; includes helpers for vector drawing.

#### Extending the example
- Vary the seek strength or make it distance-dependent.
- Add damping or friction to smooth motion.
- Show more overlays (e.g., target vector) or multiple seekers.



