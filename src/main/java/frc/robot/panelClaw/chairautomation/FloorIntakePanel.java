package frc.robot.panelclaw.chairautomation;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.panelclaw.*;
import frc.robot.panelclaw.clawcommands.ActuateClaw;
import frc.robot.panelclaw.clawcommands.DetectPanel;
import frc.robot.panelclaw.clawcommands.RaiseClaw;
import frc.robot.panelclaw.pegcommands.ActuatePeg;
import frc.robot.robotcore.universalcommands.Wait;

public class FloorIntakePanel extends CommandGroup{
    private Peg peg = Peg.getInstance();
    private Claw claw = Claw.getInstance();
    public FloorIntakePanel(){
        setInterruptible(true);
        requires(claw);
        requires(peg);
        
        addSequential(new ActuatePeg().new SetPeg(Peg.PegState.DOWN));
        addSequential(new ActuateClaw().new SetClaw(Claw.ClawState.OPEN));
        addSequential(new RaiseClaw(Claw.ClawArmState.DOWN));
        addSequential(new DetectPanel());
        addSequential(new ActuateClaw().new SetClaw(Claw.ClawState.CLOSE));
        addSequential(new Wait(1.0));
        addSequential(new RaiseClaw(Claw.ClawArmState.UP));
        addSequential(new ActuatePeg().new SetPeg(Peg.PegState.UP));
        addSequential(new ActuateClaw().new SetClaw(Claw.ClawState.OPEN));
    }
}