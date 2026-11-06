# Javadoc Tutorial for FRC Java

A small, hands-on project for learning how **doc comments** work in Java, and how
they show up in VS Code's IntelliSense while you write robot code.

Good doc comments mean a teammate can hover over `shooter.spinUp(...)` and see
*what units it takes and what it throws* without opening your file. That matters
a lot when five people are editing the same robot code the week of competition.

The classes look like FRC robot code (`frc.robot`, subsystems, `Constants`) but
**don't depend on WPILib**, so the project builds anywhere. Everything here works
the same way in a real GradleRIO robot project.

## Getting started

1. Open this folder in **WPILib VS Code** (or regular VS Code with the
   *Extension Pack for Java*).
2. Wait for the Java extension to finish loading. Watch the status bar at the
   bottom.
3. Open [`src/main/java/frc/robot/TryItHere.java`](src/main/java/frc/robot/TryItHere.java)
   and work through the `// TRY:` comments, from top to bottom.

To build and run from a terminal:

```
./gradlew run        # run TryItHere (on Windows cmd: gradlew run)
./gradlew javadoc    # generate HTML docs into build/docs/javadoc/
```

Gradle needs a JDK to run. WPILib's JDK works:
`C:\Users\Public\wpilib\<year>\jdk` on Windows. Set `JAVA_HOME` to it if
`./gradlew` can't find Java.

## What's in the project

| File | Comment style | Shows |
|---|---|---|
| [`TryItHere.java`](src/main/java/frc/robot/TryItHere.java) | `//` | **Start here.** Calls everything else; every line is something to hover over or change |
| [`Constants.java`](src/main/java/frc/robot/Constants.java) | `/** */` | Documenting constants, `{@value}`, nested classes |
| [`subsystems/Shooter.java`](src/main/java/frc/robot/subsystems/Shooter.java) | `/** */` | The full classic toolkit: `@param`, `@return`, `@throws`, `{@link}`, `{@code}`, HTML lists, code examples, `@see`, `@deprecated` |
| [`subsystems/Intake.java`](src/main/java/frc/robot/subsystems/Intake.java) | `///` | The same features written as **Markdown** doc comments (Java 23+, see below) |
| [`subsystems/RobotSubsystem.java`](src/main/java/frc/robot/subsystems/RobotSubsystem.java) | `/** */` | Interface docs, inherited docs, `{@inheritDoc}` |
| [`util/PidGains.java`](src/main/java/frc/robot/util/PidGains.java) | `/** */` | Documenting a `record` with `@param` |
| [`util/Undocumented.java`](src/main/java/frc/robot/util/Undocumented.java) | none | A blank baseline, and your exercise to document |

Every file starts with its own `// TRY:` list of things to change.

## Where doc comments show up in VS Code

| Action | How | What you see |
|---|---|---|
| **Hover** | Mouse over any class, method, or field | The rendered doc comment |
| **Completion** | Type `shooter.` (or press Ctrl+Space) | Method list; the side panel shows each method's docs |
| **Parameter hints** | Cursor inside `( )`, press Ctrl+Shift+Space | Each `@param` description, highlighting the current argument |
| **Go to Definition** | Ctrl+click, or F12 | Jumps to the source and its comment |
| **Deprecation** | Call a `@Deprecated` method | ~~Strikethrough~~ plus the `@deprecated` note on hover |
| **Generate a stub** | Type `/**` above a method and press Enter | VS Code fills in `@param`/`@return` for you |

## The two comment styles

Java now has two ways to write a doc comment. They produce the same documentation.

**Classic javadoc (`/** ... */`)** uses HTML and `{@tags}`. It works on every Java
version.

```java
/**
 * Starts spinning the flywheel toward a target speed.
 *
 * <p>Check {@link #isAtSpeed()} before shooting.
 *
 * @param rpm the target speed, from 0 to {@link ShooterConstants#kMaxRpm}
 * @throws IllegalArgumentException if {@code rpm} is out of range
 */
public void spinUp(double rpm) { ... }
```

**Markdown (`///`)** uses Markdown instead of HTML. It needs **Java 23 or newer**.

```java
/// Starts spinning the flywheel toward a target speed.
///
/// Check [#isAtSpeed()] before shooting.
///
/// @param rpm the target speed, from 0 to [ShooterConstants#kMaxRpm]
/// @throws IllegalArgumentException if `rpm` is out of range
public void spinUp(double rpm) { ... }
```

| To write... | Classic `/** */` | Markdown `///` |
|---|---|---|
| New paragraph | `<p>` | blank `///` line |
| Inline code | `{@code value}` | `` `value` `` |
| Link to a member | `{@link Shooter#stop()}` | `[Shooter#stop()]` |
| Link with custom text | `{@linkplain Shooter#stop() stop it}` | `[stop it][Shooter#stop()]` |
| Bullet list | `<ul><li>...</li></ul>` | `- item` |
| Code example | `<pre>{@code ... }</pre>` | ```` ``` ```` fenced block |
| Emphasis | `<em>word</em>` | `*word*` |
| Tags | `@param`, `@return`, `@throws`, `@see`, ... | same |

### ⚠️ Java version: why `Intake` shows no docs

This project targets **Java 17**, the JDK that ships with WPILib before the
2027 season. On Java 17, `///` is just an ordinary `//` comment that happens
to start with a slash. So in this project:

- Hovering over anything in `Intake` shows only the signature.
- `./gradlew javadoc` prints `warning: no comment` for each `Intake` member.
- The generated HTML for `Intake` has no descriptions.

This is intentional, so you can see the difference.

**WPILib moves to Java 25 for the 2027 season.** Java 25 supports `///`, so
once your team's robot project is on WPILib 2027, both comment styles work in
robot code. Until then, use `/** */` comments, which work on every version.

### Trying Markdown comments

To see the `///` comments render, you need a JDK 23 or newer. The Java 25 JDK
that ships with WPILib 2027 works.

1. Install WPILib 2027 (or any JDK 25).
2. In [`build.gradle`](build.gradle), change `def javaRelease = 17` to `25`.
3. Run Gradle with that JDK: set `JAVA_HOME` to it (for example,
   `C:\Users\Public\wpilib\2027\jdk`) and run `./gradlew javadoc`.
   `Intake.html` now has full documentation, and the warnings are gone.
4. For VS Code hovers, open the project in WPILib 2027 VS Code, or add the JDK
   under `java.configuration.runtimes` in your settings. Then run
   **Java: Clean Java Language Server Workspace**.

If your team is still on an older WPILib, change `javaRelease` back to `17`
when you're done.

## Exercises

Work through these in order. Each one is a change to make, followed by what to
look for.

1. **Hover tour.** In `TryItHere.java`, hover over `Shooter`, `spinUp`, `kMaxRpm`,
   and `collect`. Which ones show documentation, and why doesn't `collect`?
2. **Completion docs.** Retype `shooter.` and arrow through the list with the
   details panel open (Ctrl+Space again expands it).
3. **Parameter hints.** Put the cursor inside `spinUp(4000)` and
   `new PidGains(...)`, then press Ctrl+Shift+Space.
4. **Remove a tag.** Delete the `@throws` line from `Shooter.spinUp`, save, and
   hover. Put it back.
5. **Live values.** Change `kMaxRpm` in `Constants.java`, run `./gradlew javadoc`,
   and open `build/docs/javadoc/index.html`. The `{@value}` in the docs updates
   automatically.
6. **Break a link.** Change `{@link #isAtSpeed()}` in `Shooter` to
   `{@link #isReady()}` and run `./gradlew javadoc`. Read the error, then fix it.
7. **Deprecation.** Find the crossed-out `setPercent` call. Mark another method
   `@Deprecated` with a `@deprecated` tag that says what to use instead, and watch
   its callers get crossed out.
8. **Inherited docs.** Hover over `shooter.stop()` and `shooter.getName()`. Edit the
   comments in `RobotSubsystem.java` and hover again.
9. **Write your own.** Document both methods in `Undocumented.java`. Start by
   typing `/**` and pressing Enter. You're done when `./gradlew javadoc` has no
   warnings for that file.
10. **Convert a style.** Rewrite `Intake.hasGamePiece()`'s `///` comment as
    `/** */`. It shows up in hovers right away, even on Java 17.
11. **Private docs.** In `build.gradle`, uncomment
    `options.memberLevel = JavadocMemberLevel.PRIVATE` and regenerate. Private
    fields like `Shooter.kSimResponse` now appear in the HTML.
12. **(Java 25 / WPILib 2027)** Follow *Trying Markdown comments* above, and compare
    `Intake.html` with `Shooter.html`.

## Tips for robot code

- Always put **units** in the comment: RPM, meters, degrees, or volts. This is the
  single most useful thing a robot-code comment can say.
- Say which **range** a value is allowed to be in (`-1.0 to 1.0`, `0 to kMaxRpm`).
- Document anything that **blocks or takes time** ("returns immediately; check
  `isAtSpeed()`").
- Start with one sentence that ends in a period. That first sentence is the
  summary in completion lists and the HTML index.
