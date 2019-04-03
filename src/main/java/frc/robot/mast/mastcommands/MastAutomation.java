package frc.robot.mast.mastcommands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.mast.Mast;
import frc.robot.panelclaw.Claw;
import frc.robot.panelclaw.clawcommands.ActuateClaw;

public class MastAutomation extends CommandGroup {

    public MastAutomation(Mast.MastState mastState) {
        requires(Mast.getInstance());
        requires(Claw.getInstance());

        addSequential(new ActuateClaw().new SetClaw(Claw.ClawState.OPEN));
        addParallel(new MoveMast(mastState));
    }
}