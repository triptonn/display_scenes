### Scene Template

Starter skeleton for building a new interactive scene in the DisplayScenes framework.

#### What it includes
- **`SceneTemplate.java`**: window creation, input wiring, and game loop (`Timer` at ~60 FPS).
- **`SceneModel.java`**: object registries by capability, tick/update ordering, draw ordering.
- **`ScenePanel.java`**: `JPanel` that forwards painting to the renderer.
- **`SceneRenderer.java`**: clears the background and calls `model.render`; includes a `drawVec` helper.

#### How to use
1) Copy the entire `app/templates` directory to `app/<your_scene>` and update the package name.
2) Implement your objects using the core interfaces (`Renderable`, `Updateable`, `Moveable`, `Inert`, etc.).
3) Register them with `model.addObject(obj)` inside `setupScene()`.
4) Put your per-frame logic (forces, AI, timers) in `updateSceneState()` or directly inside your model’s `update()`.
5) Start the loop via `startLoop()`; toggle any debug overlays with your own keybindings.

#### Controls (default)
- **Space**: toggles `model.toggleShowComponents()` for simple diagnostics (you can repurpose this).
- Mouse move updates `model.setMousePos(...)` for pointer-aware scenes.

#### Render order (default)
`Inert` background → `Moveable` actors → `SimpleLiquid` overlays. You can adapt this in `SceneModel#render`.

#### Tips
- Keep physics state inside objects implementing `Updateable`; let the model only orchestrate.
- Use `data.Vec` for all vector math (positions, forces, velocities).
- Prefer small, composable objects implementing only the interfaces they need.


