// TRY: (Constants.java)
//  1. In TryItHere.java, hover over ShooterConstants.kMaxRpm. The hover shows the
//     comment below *and* the constant's value.
//  2. Change kMaxRpm to 6000, save, and hover again. {@value} in the javadoc HTML
//     updates automatically, so the docs never drift from the code.
//  3. Delete the comment on kIdleRpm, then run `./gradlew javadoc` and look for the
//     "no comment" warning.

package frc.robot;

/**
 * Robot-wide constants, grouped into one nested class per subsystem.
 *
 * <p>Keeping numbers here, rather than scattered through subsystems, means a
 * teammate can hover over any constant and see its units and where it came from.
 *
 * <p>This is the same pattern WPILib's command-based templates use.
 */
public final class Constants {
    private Constants() {}

    /** Constants for the {@link frc.robot.subsystems.Shooter} flywheel. */
    public static final class ShooterConstants {
        private ShooterConstants() {}

        /** CAN ID of the flywheel motor controller. */
        public static final int kMotorCanId = 10;

        /**
         * Fastest the flywheel is allowed to spin, in rotations per minute.
         *
         * <p>Value: {@value}. Measured on the practice bot with a fresh battery.
         */
        public static final double kMaxRpm = 5800.0;

        /** Speed the flywheel holds between shots so spin-up is faster, in RPM. */
        public static final double kIdleRpm = 1500.0;

        /**
         * How close to the target speed (in RPM) counts as "ready to shoot".
         *
         * @see frc.robot.subsystems.Shooter#isAtSpeed()
         */
        public static final double kToleranceRpm = 50.0;
    }

    /** Constants for the {@link frc.robot.subsystems.Intake} rollers. */
    public static final class IntakeConstants {
        private IntakeConstants() {}

        /** CAN ID of the intake roller motor controller. */
        public static final int kMotorCanId = 20;

        /** Roller output used when collecting game pieces, from -1.0 to 1.0. */
        public static final double kIntakeSpeed = 0.8;
    }
}
