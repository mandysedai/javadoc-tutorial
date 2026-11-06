// TRY: (Intake.java uses Markdown /// doc comments, JEP 467)
//
// IMPORTANT: /// comments are only doc comments on Java 23 and newer. This project
// targets Java 17 (WPILib's JDK), so on 17 or 21 they're ordinary comments:
// hovers show nothing, and `./gradlew javadoc` warns "no comment" for every member.
//
//  1. In TryItHere.java, hover over intake.collect() and then shooter.spinUp().
//     Only the Shooter hover shows documentation.
//  2. Compare the /// comment on collect() with the /** */ comment on
//     Shooter.spinUp(). Both say the same thing:
//       <p>             -> blank /// line
//       {@code x}       -> `x`
//       {@link Foo#m()} -> [Foo#m()]
//       <ul><li>        -> - list items
//       <pre>{@code}    -> ``` fenced code block
//  3. Convert the comment on hasGamePiece() to /** */ style. Save, then hover. It shows
//     up right away, even on Java 17.
//  4. (Optional, needs JDK 23+) Follow "Trying Markdown comments" in README.md and
//     watch every /// comment in this file turn into real documentation.

package frc.robot.subsystems;

import frc.robot.Constants.IntakeConstants;

/// Rollers that pull game pieces off the floor and feed them into the [Shooter].
///
/// ## Typical use
///
/// - Hold the intake button: call [#collect()].
/// - Release the button: call [#stop()].
/// - When the shooter [is at speed][Shooter#isAtSpeed()], call [#feed()].
///
/// ```
/// if (shooter.isAtSpeed()) {
///     intake.feed();
/// }
/// ```
///
/// This tutorial version *simulates* the beam-break sensor, so it runs
/// without robot hardware.
///
/// @author FRC Team 3082
/// @since 2026
/// @see IntakeConstants
public class Intake implements RobotSubsystem {

    private double output = 0.0;
    private boolean hasGamePiece = false;

    /// Creates an intake with the rollers stopped.
    ///
    /// The motor uses CAN ID {@value IntakeConstants#kMotorCanId}.
    public Intake() {}

    /// Spins the rollers inward to collect a game piece.
    ///
    /// The rollers run at [IntakeConstants#kIntakeSpeed]. Once a piece is
    /// detected, the rollers stop on their own so the piece isn't fed into the
    /// shooter early.
    ///
    /// @return `true` if a game piece is now held, `false` if still collecting
    public boolean collect() {
        output = IntakeConstants.kIntakeSpeed;
        // Simulation: pretend the beam break trips right away.
        hasGamePiece = true;
        output = 0.0;
        return hasGamePiece;
    }

    /// Pushes the held game piece into the shooter.
    ///
    /// Does nothing if [#hasGamePiece()] is `false`.
    ///
    /// @throws IllegalStateException if called while the rollers are running in reverse
    public void feed() {
        if (output < 0) {
            throw new IllegalStateException("can't feed while ejecting");
        }
        hasGamePiece = false;
    }

    /// Runs the rollers outward to spit out a stuck game piece.
    ///
    /// @param speed roller output from 0.0 to 1.0. The sign is flipped for you.
    public void eject(double speed) {
        output = -Math.abs(speed);
        hasGamePiece = false;
    }

    /// Reports whether the beam-break sensor sees a game piece.
    ///
    /// @return `true` if a game piece is held
    public boolean hasGamePiece() {
        return hasGamePiece;
    }

    /// {@inheritDoc}
    @Override
    public void stop() {
        output = 0.0;
    }

    /// {@inheritDoc}
    ///
    /// For the intake, this is always `"Intake"`.
    @Override
    public String getName() {
        return "Intake";
    }
}
