// Start here! Open this file in VS Code and work through each "TRY:" comment.
// Run it with `./gradlew run` (or the "Run" link above main()).

package frc.robot;

import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.util.PidGains;
import frc.robot.util.Undocumented;

/**
 * Playground for exploring how doc comments show up in IntelliSense.
 *
 * <p>Every line in {@link #main(String[])} is there to be hovered over, changed,
 * or broken on purpose.
 */
public final class TryItHere {
    private TryItHere() {}

    /**
     * Simulates a short shooting sequence and prints what happens.
     *
     * @param args ignored
     */
    public static void main(String[] args) {
        // TRY: Hover over "Shooter" and "Intake" to see their class-level docs.
        //      Shooter (/** */) shows a full description. Intake (///) shows nothing on
        //      Java 17. See the note at the top of Intake.java.
        Shooter shooter = new Shooter();
        Intake intake = new Intake();

        // TRY: Hover over kMaxRpm. The hover shows the comment and the value.
        System.out.println("Max shooter speed: " + ShooterConstants.kMaxRpm + " RPM");

        // TRY: Put the cursor after "shooter." below, delete "spinUp(4000)", and
        //      type "shooter." again. Arrow through the list and read each method's docs.
        // TRY: Put the cursor inside the parentheses and press Ctrl+Shift+Space for
        //      parameter hints. They come from the @param tag.
        shooter.spinUp(4000);

        // TRY: Hover over "collect". Compare it with hovering over "spinUp" above.
        intake.collect();

        for (int loop = 0; loop < 20 && !shooter.isAtSpeed(); loop++) {
            shooter.periodic();
            System.out.printf("loop %2d: %.0f RPM%n", loop, shooter.getRpm());
        }

        // TRY: Hover over isAtSpeed. Then Ctrl+click it (Go to Definition) to jump
        //      to the source and its comment.
        if (shooter.isAtSpeed() && intake.hasGamePiece()) {
            intake.feed();
            System.out.println("Shot fired!");
        }

        // TRY: setPercent is crossed out because it's @Deprecated. Hover to see why
        //      and what to use instead.
        shooter.setPercent(0.5);

        // TRY: stop() has no comment in Shooter.java, but the hover still shows text.
        //      It's inherited from RobotSubsystem.
        // TRY: getName() uses {@inheritDoc} and adds an extra sentence.
        shooter.stop();
        System.out.println(shooter.getName() + " stopped");

        // TRY: Type "new PidGains(" and look at the parameter hints for kP, kI, kD.
        PidGains gains = new PidGains(0.1, 0.0, 0.01);
        System.out.println("kP = " + gains.kP());

        // TRY: Hover over clamp. There are no docs, only the signature. Go write some!
        //      (See the instructions at the top of Undocumented.java.)
        double clamped = Undocumented.clamp(7.5, 0.0, 5.0);
        System.out.println("clamped = " + clamped);
    }
}
