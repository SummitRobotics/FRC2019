package frc.robot.chair.chairautomation;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.chair.Chair;
import frc.robot.chair.chaircommands.ActuatePeg;
import frc.robot.panelclaw.*;
import frc.robot.panelclaw.clawcommands.ActuateClaw;
import frc.robot.panelclaw.clawcommands.DetectPanel;
import frc.robot.panelclaw.clawcommands.RaiseClaw;
import frc.robot.robotcore.universalcommands.Wait;

public class FloorIntakePanel extends CommandGroup{

    public FloorIntakePanel(){
        setInterruptible(true);
        requires(Chair.getInstance());
        requires(Claw.getInstance());
        
        addSequential(new ActuatePeg().new SetPeg(Chair.PegState.DOWN));
        addSequential(new ActuateClaw().new SetClaw(Claw.ClawState.OPEN));
        addSequential(new RaiseClaw(Claw.ClawArmState.DOWN));
        addSequential(new DetectPanel());
        addSequential(new ActuateClaw().new SetClaw(Claw.ClawState.CLOSE));
        addSequential(new Wait(1.0));
        addSequential(new RaiseClaw(Claw.ClawArmState.UP));
        addSequential(new ActuatePeg().new SetPeg(Chair.PegState.UP));
        addSequential(new ActuateClaw().new SetClaw(Claw.ClawState.OPEN));
    }

    @Override
    protected void interrupted() {
        super.interrupted();
        Claw.getInstance().kill();
    }
}