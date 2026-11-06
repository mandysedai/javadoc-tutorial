// TRY: (Undocumented.java)
// This class intentionally has NO doc comments. Use it as a baseline:
//  1. In TryItHere.java, hover over Undocumented.clamp(...). You only get the
//     signature. Compare that with hovering over a Shooter method.
//  2. Run `./gradlew javadoc` and count the "no comment" warnings for this file.
//  3. Your turn: write a /** */ comment for clamp(), with a @param for each parameter
//     and a @return. Then hover again and rerun javadoc. The warnings should go away.
//  4. Bonus: type /** on the line above a method and press Enter. VS Code fills in
//     the @param and @return tags for you.

package frc.robot.util;

public final class Undocumented {
    private Undocumented() {}

    public static double clamp(double value, double low, double high) {
        return Math.max(low, Math.min(high, value));
    }

    public static double deadband(double value, double deadband) {
        return Math.abs(value) < deadband ? 0.0 : value;
    }
}
