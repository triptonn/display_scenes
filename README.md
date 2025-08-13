# DisplayScenes
Simple Swing-based framework to build small physics-like objects and render them in interactive scenes.

The project has two parts:
- `core`: minimal math (`data.Vec`), rendering and simulation contracts (`interfaces`), and ready-to-use objects (`objects` like `Ball`, `Box`, `SimpleLiquid`, `VectorArrow`).
- `app`: self-contained example scenes that assemble and drive objects (`massive_balls`, `box_draw`, etc.). Each scene keeps its own `SceneModel`, `ScenePanel`, and `SceneRenderer` (and optionally follows `templates/`).

### Dependencies
- JavaSE-21
- Used IDE: Eclipse

### Quick start
- Clone the repo
- Run the build script depending on your environment:

  - Windows Command Line (cmd)

  ```cmd
  REM Run this from the project root directory
  .\build\build.cmd
  ```
  - PowerShell

  ```powershell
  # Run this from the project root directory
  ./build/build.ps1
  ```
  - Bash [Linux and OSX (not tested)]

  ```bash
  # Run this from the project root directory
  ./build/build.sh
  ```

- To run the example applications from the project root directory use:

```bash
# BoxDraw 
java -cp bin box_draw.BoxDraw

# GaussianBars
java -cp bin gaussian_bars.GaussianBars

# Gravitational
java -cp bin gravitational.Gravitational

# MassiveBalls
java -cp bin massive_balls.MassiveBalls

# MouseHunter
java -cp bin mouse_hunter.MouseHunter

# RandomWalker
java -cp bin random_walker.RandomWalker

# VectorShizzle
java -cp bin vector_shizzle.VectorShizzle
```

### Library interfaces (how they are used)
- Informative: minimal read/write pose used for diagnostic/overlay renderers.
  - Example: `objects.VectorArrow` implements `Informative` + `Renderable` to draw a live arrow; scenes call `update(Vec)` to change its vector and `render(Graphics2D)` draws it.
- Renderable: can draw itself to a `Graphics2D` and exposes basic visual state (`getAngle`, `isVisible`, `getColor`).
  - Used by all visible objects. Scene renderers call `render(g2d)` per frame and sort layers by type (see each `SceneModel#render`).
- Inert: static/kinematic pose without forces (`get/setLocation`, `get/setAngle`).
  - Example: `objects.Box` is background geometry implementing `Inert, Renderable`.
- Updateable: advances its internal state each tick via `update()`.
  - Example: `objects.Ball` and `objects.MoBox` integrate velocity/position and handle simple bounce in `update()`.
- Moveable: dynamic bodies that accept forces and angular impulses and expose kinematic state and flags (mass, friction/drag toggles, bounce behavior, attractor flag).
  - Example: `objects.Ball` implements `Moveable, Attractor, Renderable, Updateable`. Scenes call `applyForce(Vec)` (e.g., gravity, wind) and optionally `applyMomentum(Vec)`; `Ball#update` integrates and resolves bounds/bounce using `isBouncy`/`setBounceFactor`.
- Attractor: provides `Vec attract(Moveable m)` to compute a force (e.g., gravity-like). Any `Moveable` can also be an `Attractor`. See `objects.Ball#attract` and the `gravitational` example.

### Core building blocks
- `data.Vec`: small mutable vectors with common operations (add, scale, norm, dot, reflect2D). Used for positions, velocities, forces.
- `objects.SceneObject`: shared base holding a `name`, object and scene dimensions, and a protected location for convenience.
- Ready objects:
  - `Ball` (circle) — dynamic, renderable, optional attractor, simple bouncing and friction/drag flags.
  - `MoBox` (rectangle) — dynamic, renderable variant with rotation support.
  - `Box` (rectangle) — inert, renderable backdrop/obstacle.
  - `SimpleLiquid` — inert area that can apply quadratic drag to `Moveable`s via `contains(m)` and `drag(m)`.
  - `VectorArrow` — informative overlay renderer for visualizing vectors.

### Scene structure
Each scene follows the same pattern:
- `SceneModel`: owns lists of objects split by capability (`Moveable`, `Renderable`, `Updateable`, etc.), updates tick order, and determines draw order (background → actors → liquids → overlays).
- `ScenePanel`: a `JPanel` that forwards `paintComponent` to a `SceneRenderer`.
- `SceneRenderer`: clears the frame and asks the model to render; may provide helpers like `drawVec`.
- Main class (e.g., `MassiveBalls`, `BoxDraw`): wires input, creates objects, adds them via `model.addObject`, and starts a Swing `Timer` to call `model.update()` + `repaint()` at ~60 FPS.

### How examples use the interfaces
- MassiveBalls
  - Creates several `Ball` instances, toggles `setBouncy(true)`, sets friction/drag coefficients, and adds a `SimpleLiquid` region.
  - On each tick, applies forces to all `Moveable`s: gravity, optional wind, friction when `isLanded()`, and liquid drag when inside `SimpleLiquid`.
  - Let each `Updateable` integrate itself; `Renderable`s are batched by the model and drawn.
- BoxDraw
  - Adds a static `Box` (`Inert, Renderable`) and a dynamic `MoBox` (`Moveable, Renderable, Updateable`).
  - Shows an angular velocity `VectorArrow` overlay (`Informative, Renderable`). The scene updates the arrow’s vector each frame and uses `applyMomentum` on the `Moveable`.

### Create your own scene
1) Copy `app/templates` into a new package under `app/<your_scene>` or start from an existing example.
2) Build objects and add them with `model.addObject(obj)`. Implement one or more of `Renderable`, `Updateable`, `Moveable`, `Inert`, `Informative` on your classes as needed.
3) In your main loop:
   - Apply forces to `Moveable`s via `applyForce(Vec)` and/or `applyMomentum(Vec)`.
   - Call `model.update()` then `repaint()`.
4) Use `setVisible(true)` to render, `setBouncy(true)` and `setBounceFactor` for simple bounds bounces, and friction/drag flags for basic resistive forces.

### Examples (gallery)
#### RandomWalker
![random_walker](https://github.com/user-attachments/assets/ca030da9-e4ba-4211-885b-5883e6290dee)

#### GaussianBars
![gaussian_bars](https://github.com/user-attachments/assets/e6a4298e-5b6c-4d3c-9a02-f85085cc1f08)

#### MouseHunter
![mouse_hunter](https://github.com/user-attachments/assets/30994e14-c98a-4948-a35c-558715320a41)

#### Gravitational
![gravitational](https://github.com/user-attachments/assets/02884cde-26e5-40d7-b14b-e1d4bf852bcc)

#### MassiveBalls
![massive_balls](https://github.com/user-attachments/assets/ad5a1ac3-ea69-41ad-9f27-c02f1c4da99a)
