// TRY: (PidGains.java)
//  1. In TryItHere.java, type `new PidGains(` and pause (or press Ctrl+Shift+Space).
//     The parameter hints come from the @param tags below.
//  2. Hover over gains.kP(). Records get accessor docs from these @param tags too.
//  3. Delete the "@param kD" line, then run `./gradlew javadoc` and look for the warning.

package frc.robot.util;

/**
 * Proportional-integral-derivative (PID) feedback gains for a motor controller.
 *
 * <p>Tune {@code kP} first, then {@code kD}, and only add {@code kI} if you have a
 * steady-state error you can't remove otherwise.
 *
 * @param kP proportional gain: output per unit of error
 * @param kI integral gain: output per unit of accumulated error
 * @param kD derivative gain: output per unit of change in error
 */
public record PidGains(double kP, double kI, double kD) {

    /**
     * Creates gains that use only a proportional term.
     *
     * @param kP proportional gain: output per unit of error
     * @return gains with {@code kI} and {@code kD} set to zero
     */
    public static PidGains proportional(double kP) {
        return new PidGains(kP, 0.0, 0.0);
    }
}
