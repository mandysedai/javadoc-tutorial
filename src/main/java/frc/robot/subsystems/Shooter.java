// TRY: (Shooter.java uses classic /** ... */ javadoc comments)
//  1. In TryItHere.java, hover over `new Shooter()`, `spinUp`, and `isAtSpeed`.
//     Look at how the <p>, <ul>, {@code}, and {@link} markup below gets rendered.
//  2. In TryItHere.java, type `shooter.` and arrow through the completion list.
//     The panel on the side (expand it with Ctrl+Space) shows each method's comment.
//  3. Delete the "@throws" line from spinUp(), save, and hover again.
//  4. Change {@link #isAtSpeed()} below to {@link #isReady()} (a method that doesn't
//     exist) and run `./gradlew javadoc`. Doclint reports a "reference not found" error.
//  5. setPercent() is @Deprecated. Notice the strikethrough where TryItHere calls it,
//     and hover over it to read the @deprecated note.

package frc.robot.subsystems;

import frc.robot.Constants.ShooterConstants;

/**
 * A single flywheel shooter that launches game pieces.
 *
 * <p>The flywheel runs at closed-loop speed control. Typical use during a match:
 * <ul>
 *   <li>Call {@link #spinUp(double)} when the driver lines up a shot.</li>
 *   <li>Wait until {@link #isAtSpeed()} returns {@code true}.</li>
 *   <li>Feed the game piece, then call {@link #stop()} or go back to idle.</li>
 * </ul>
 *
 * <p>Example:
 * <pre>{@code
 * Shooter shooter = new Shooter();
 * shooter.spinUp(4000);
 * if (shooter.isAtSpeed()) {
 *     intake.feed();
 * }
 * }</pre>
 *
 * <p>This tutorial version <em>simulates</em> the motor so it runs without robot
 * hardware. Call {@link #periodic()} to advance the simulation.
 *
 * @author FRC Team 3082
 * @version 1.0
 * @since 2026
 * @see ShooterConstants
 * @see Intake
 */
public class Shooter implements RobotSubsystem {

    /** Fraction of the remaining speed error closed on each periodic loop. */
    private static final double kSimResponse = 0.25;

    private double targetRpm = 0.0;
    private double currentRpm = 0.0;

    /**
     * Creates a shooter with the flywheel stopped.
     *
     * <p>The motor uses CAN ID {@value ShooterConstants#kMotorCanId}.
     */
    public Shooter() {}

    /**
     * Starts spinning the flywheel toward a target speed.
     *
     * <p>This method returns immediately. The flywheel takes time to reach speed,
     * so check {@link #isAtSpeed()} before shooting.
     *
     * @param rpm the target speed in rotations per minute, from 0 to
     *            {@link ShooterConstants#kMaxRpm}
     * @throws IllegalArgumentException if {@code rpm} is negative or above
     *         {@link ShooterConstants#kMaxRpm}
     */
    public void spinUp(double rpm) {
        if (rpm < 0 || rpm > ShooterConstants.kMaxRpm) {
            throw new IllegalArgumentException(
                "rpm must be between 0 and " + ShooterConstants.kMaxRpm + ", got " + rpm);
        }
        targetRpm = rpm;
    }

    /**
     * Spins the flywheel at its idle speed, so the next {@link #spinUp(double)}
     * takes less time.
     *
     * @see ShooterConstants#kIdleRpm
     */
    public void idle() {
        targetRpm = ShooterConstants.kIdleRpm;
    }

    /**
     * Reports whether the flywheel is close enough to its target to shoot.
     *
     * @return {@code true} if the measured speed is within
     *         {@link ShooterConstants#kToleranceRpm} of the target and the target
     *         isn't zero
     */
    public boolean isAtSpeed() {
        return targetRpm > 0
            && Math.abs(targetRpm - currentRpm) <= ShooterConstants.kToleranceRpm;
    }

    /**
     * Returns the measured flywheel speed.
     *
     * @return the current speed in rotations per minute
     */
    public double getRpm() {
        return currentRpm;
    }

    /**
     * Runs the flywheel at a fixed fraction of full power, with no speed control.
     *
     * @param percent motor output from 0.0 to 1.0
     * @deprecated Open-loop shots vary with battery voltage. Use
     *             {@link #spinUp(double)} instead.
     */
    @Deprecated(since = "2026")
    public void setPercent(double percent) {
        targetRpm = percent * ShooterConstants.kMaxRpm;
    }

    /**
     * Updates the simulated flywheel. Call this once per robot loop (every 20 ms).
     */
    public void periodic() {
        currentRpm += (targetRpm - currentRpm) * kSimResponse;
    }

    // No doc comment here on purpose. The hover and the HTML show the docs
    // inherited from RobotSubsystem.stop().
    @Override
    public void stop() {
        targetRpm = 0.0;
    }

    /**
     * {@inheritDoc}
     *
     * <p>For the shooter, this is always {@code "Shooter"}.
     */
    @Override
    public String getName() {
        return "Shooter";
    }
}
