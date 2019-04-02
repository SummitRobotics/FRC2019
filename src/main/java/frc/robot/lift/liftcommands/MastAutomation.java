package frc.robot.lift.liftcommands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.lift.Lift;
import frc.robot.panelclaw.Claw;
import frc.robot.panelclaw.clawcommands.ActuateClaw;

public class MastAutomation extends CommandGroup {

    Lift lift = Lift.getInstance();
    Claw claw = Claw.getInstance();

    public MastAutomation(Lift.LiftState liftState) {
        requires(lift);
        requires(claw);

        addSequential(new ActuateClaw().new SetClaw(Claw.ClawState.OPEN));
        addParallel(new MoveMast(liftState));
    }
}