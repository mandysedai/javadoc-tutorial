// TRY: (RobotSubsystem.java)
//  1. In TryItHere.java, hover over shooter.stop(). Shooter.stop() has no comment
//     of its own, but the hover still shows the text below: an overriding method
//     inherits its docs automatically.
//  2. Hover over shooter.getName(). Shooter's comment uses {@inheritDoc} to pull
//     in this text and then adds a sentence of its own.
//  3. Change the wording here, save, and hover again. Both places update.

package frc.robot.subsystems;

/**
 * Something on the robot that the drive team can control, such as a shooter,
 * an intake, or a drivetrain.
 *
 * <p>This is a simplified stand-in for WPILib's {@code Subsystem} interface, so the
 * tutorial doesn't need the WPILib libraries.
 */
public interface RobotSubsystem {

    /**
     * Stops every motor in this subsystem immediately.
     *
     * <p>Called when the robot is disabled, and safe to call at any time.
     */
    void stop();

    /**
     * Returns a short name for this subsystem, used in dashboard keys and log
     * messages.
     *
     * @return the subsystem name, never {@code null}
     */
    String getName();
}
