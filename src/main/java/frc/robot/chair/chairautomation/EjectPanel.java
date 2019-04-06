package frc.robot.chair.chairautomation;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.chair.Chair;
import frc.robot.chair.chaircommands.ActuateChair;
import frc.robot.chair.chaircommands.ActuatePeg;
import frc.robot.drivetrain.Drivetrain;
import frc.robot.drivetrain.drivetraincommands.PowerDrive;
import frc.robot.panelclaw.Claw;
import frc.robot.panelclaw.clawcommands.ActuateClaw;
import frc.robot.panelclaw.clawcommands.LimitClaw;
import frc.robot.robotcore.universalcommands.Wait;

public class EjectPanel extends CommandGroup{

    public EjectPanel(){
        setInterruptible(true);
        requires(Chair.getInstance());
        requires(Claw.getInstance());
        
        addSequential(new ActuateChair().new SetChair(Chair.PneumaticState.IN));
        addSequential(new ActuateClaw().new SetClaw(Claw.ClawState.OPEN));
        addSequential(new ActuatePeg().new SetPeg(Chair.PegState.DOWN));
        addSequential(new PowerDrive(0.25, 0.4));
        addSequential(new Wait(0.1)); 
        addSequential(new ActuateChair().new SetChair(Chair.PneumaticState.OUT));
        addSequential(new Wait(0.2));
        addSequential(new ActuateChair().new SetChair(Chair.PneumaticState.IN));
        addSequential(new Wait(0.25));
        addSequential(new LimitClaw(-0.2));
        addSequential(new ActuateClaw().new SetClaw(Claw.ClawState.CLOSE));

        //addSequential(new ActuatePeg().new SetPeg(PegState.UP));
        //addSequential(new MoveMast(LiftState.LOW));
    }

    @Override
    protected void interrupted() {
        super.interrupted();
        Drivetrain.getInstance().kill();
        Claw.getInstance().kill();
    }
}