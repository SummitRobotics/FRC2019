package frc.robot.panelclaw.clawcommands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.panelclaw.*;
import frc.robot.panelclaw.clawcommands.*;

public class IntakePanel extends CommandGroup{
    public IntakePanel(){
        addSequential(new ActuateClaw(Claw.ClawState.OPEN));
        addSequential(new RaiseClaw(Claw.ClawArmState.DOWN));
        addSequential(new DetectPanel());
        addSequential(new ActuateClaw(Claw.ClawState.CLOSE));
        addSequential(new RaiseClaw(Claw.ClawArmState.UP));
    }
}