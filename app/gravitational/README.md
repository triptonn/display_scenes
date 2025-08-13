### Gravitational

Interactive demo of a simple n-body-style attraction: an attractor mass pulls on one or more movers using the DisplayScenes framework.

#### What it demonstrates
- **Attractor interaction**: `objects.Ball` acting as an attractor (`setAttractor(true)`) applying `a.attract(m)` forces to movers.
- **Moveable dynamics**: a mover integrates forces without friction/drag for clean orbital motion.
- **Update and render pipeline**: `SceneModel#update` and `SceneModel#render` batched by capability.
- **Basic control**: pause/resume the simulation.

#### How to run
```bash
java -cp bin gravitational.Gravitational
```

#### Controls
- **Space**: pause/resume simulation.

#### Scene setup
- One attractor `Ball` at the center:
  - `radius = 50`, `mass = 5`, `color = Color.blue`, `attractor = true`.
- One mover `Ball` starting left of center with a small upward velocity:
  - `radius = 5`, `mass = 0.1`, `velocity = (0.0, 1.0)`, `color = Color.green`.
- Mover has `frictionCoefficient = 0` and `dragCoefficient = 0`.

#### Forces per tick (core loop)
- For each attractor `a` and mover `m` (where `m` is not an attractor):
  - Compute `Vec force = a.attract(m)` and apply `m.applyForce(force)`.
- Let each `Updateable` integrate in `model.update()`.

#### File map
- `Gravitational.java`: window, input (pause), main loop (`startLoop`, `update`, `setupScene`).
- `SceneModel.java`: object lists, update order, and draw order (background → actors → liquids).
- `ScenePanel.java`: lightweight `JPanel` delegating to renderer.
- `SceneRenderer.java`: clears the frame and renders the model.

#### Extending the example
- Add more movers or multiple attractors to explore complex orbits.
- Introduce friction/drag to see circularization or decay.
- Spawn movers with different initial velocities to compare trajectories.



