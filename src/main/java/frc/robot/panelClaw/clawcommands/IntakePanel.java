package frc.robot.panelclaw.clawcommands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.panelclaw.*;

public class IntakePanel extends CommandGroup{
    public IntakePanel(){
        addSequential(new ActuateClaw(Claw.ClawState.OPEN));
        addSequential(new RaiseClaw(-1));
        addSequential(new DetectPanel());
        addSequential(new ActuateClaw(Claw.ClawState.CLOSE));
        addSequential(new RaiseClaw(1));
    }
}