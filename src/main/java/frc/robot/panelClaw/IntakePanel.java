package frc.robot.panelClaw;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.lift.MoveMast;
import frc.robot.lift.Lift.LiftState;
import frc.robot.panelClaw.Claw.ClawState;

public class IntakePanel extends CommandGroup{
    public IntakePanel(){
        addSequential(new ActuateClaw(ClawState.OPEN));
        addSequential(new RaiseClaw(-1));
        addSequential(new DetectPanel());
        addSequential(new ActuateClaw(ClawState.CLOSE));
        addSequential(new RaiseClaw(1));
    }
}