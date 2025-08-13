### MassiveBalls

Interactive demo showcasing multiple dynamic balls with gravity, friction, wind, and liquid drag using the DisplayScenes framework.

#### What it demonstrates
- **Moveable objects**: `objects.Ball` instances with mass, bounce, friction, and drag.
- **Simple fluids**: `objects.SimpleLiquid` applying quadratic drag inside a region.
- **Update and render pipeline**: `SceneModel#update` and `SceneModel#render` batched by capability.
- **Input-driven forces**: wind toggled by mouse buttons; pause/resume via keyboard.

#### How to run
```bash
java -cp bin massive_balls.MassiveBalls
```

#### Controls
- **Space**: pause/resume simulation.
- **Hold Left Mouse Button**: apply wind to the left.
- **Hold Right Mouse Button**: apply wind to the right.

#### Scene setup
- Spawns several `Ball` objects with randomized positions and radii.
- Enables bounce and sets coefficients:
  - `airBounceFactor = 0.8`
  - `waterBounceFactor = 0.2`
- Adds a `SimpleLiquid` region in the lower half of the scene (`ColorScheme.WATER_COLOR`).

#### Forces per tick (core loop)
- Gravity: `new Vec(0.0, 0.3) * mass` applied to each `Moveable`.
- Optional wind: horizontal impulse while a mouse button is held.
- Ground friction: opposite to velocity when `isLanded()` and the mover has friction enabled.
- Liquid drag: quadratic drag applied when inside `SimpleLiquid`; also reduces bounce factor.

#### File map
- `MassiveBalls.java`: window, input handling, main loop (`startLoop`, `update`, `setupScene`).
- `SceneModel.java`: object lists, update order, and draw order (background → actors → liquids).
- `ScenePanel.java`: lightweight `JPanel` delegating to renderer.
- `SceneRenderer.java`: clears the frame and renders the model; includes `drawVec` helper.

#### Extending the example
- Add more `Ball` or `MoBox` actors via `model.addObject(obj)`.
- Tweak coefficients (`friction`, `drag`, `bounceFactor`) per object for different materials.
- Add additional forces (e.g., wind fields) in `update()`.
- Change the `SimpleLiquid` size/position or add multiple regions.


