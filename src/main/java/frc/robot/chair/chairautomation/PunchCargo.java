package frc.robot.chair.chairautomation;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.chair.Chair;
import frc.robot.chair.chaircommands.ActuatePeg;
import frc.robot.chair.chaircommands.BopIt;
import frc.robot.drivetrain.drivetraincommands.PowerDrive;
import frc.robot.panelclaw.Claw;
import frc.robot.panelclaw.Claw.ClawState;
import frc.robot.panelclaw.clawcommands.ActuateClaw;
import frc.robot.panelclaw.clawcommands.PowerMoveClawWrist;
import frc.robot.robotcore.universalcommands.Wait;

public class PunchCargo extends CommandGroup{

    public PunchCargo(){
        setInterruptible(true);
        requires(Chair.getInstance());
        requires(Claw.getInstance());

        addSequential(new ActuateClaw().new SetClaw(ClawState.OPEN));
        addSequential(new Wait(.1));
        addSequential(new ActuatePeg().new SetPeg(Chair.PegState.DOWN));
        addSequential(new PowerDrive(-0.3, 0.2));
        addSequential(new Wait(0.1));
        addSequential(new BopIt().new SetBop(Chair.PneumaticState.OUT));
        addSequential(new Wait(0.25));
        addSequential(new BopIt().new SetBop(Chair.PneumaticState.IN));
        addSequential(new ActuatePeg().new SetPeg(Chair.PegState.DOWN));
        addSequential(new PowerMoveClawWrist(1.5, Claw.ClawSpeed.REVERSE));
        addSequential(new ActuateClaw().new SetClaw(ClawState.CLOSE));
    }
}