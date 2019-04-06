package frc.robot.chair.chairautomation;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.chair.Chair;
import frc.robot.chair.chaircommands.ActuateChair;
import frc.robot.chair.chaircommands.ActuatePeg;
import frc.robot.drivetrain.Drivetrain;
import frc.robot.drivetrain.drivetraincommands.PowerDrive;
import frc.robot.panelclaw.Claw;
import frc.robot.robotcore.universalcommands.Wait;

public class PinPanel extends CommandGroup{

    public PinPanel(){
        setInterruptible(true);
        requires(Drivetrain.getInstance());
        requires(Claw.getInstance());
        requires(Chair.getInstance());

        //addSequential(new ActuatePeg().new TogglePeg());\
        addSequential(new ActuatePeg().new SetPeg(Chair.PegState.UP));
        addSequential(new Wait(0.1));
        addSequential(new PowerDrive(-0.3, 0.5));
        addSequential(new Wait(0.5));
        addSequential(new ActuateChair().new ToggleChair());
    }

    @Override
    protected void interrupted() {
        super.interrupted();
        Drivetrain.getInstance().kill();
        Claw.getInstance().kill();
    }
}