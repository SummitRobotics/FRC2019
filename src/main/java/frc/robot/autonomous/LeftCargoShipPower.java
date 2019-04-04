package frc.robot.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.chair.Chair;
import frc.robot.chair.chairautomation.EjectPanel;
import frc.robot.chair.chairautomation.PinPanel;
import frc.robot.drivetrain.Drivetrain;
import frc.robot.drivetrain.drivetraincommands.GyroTurn;
import frc.robot.drivetrain.drivetraincommands.PowerDrive;
import frc.robot.drivetrain.drivetraincommands.vision.TargetAlignment;
import frc.robot.panelclaw.Claw;

public class LeftCargoShipPower extends CommandGroup{

    public LeftCargoShipPower(){
        setInterruptible(true);
        requires(Drivetrain.getInstance());
        requires(Claw.getInstance());
        requires(Chair.getInstance());

        addSequential(new PowerDrive(0.35, 2.0));
        addSequential(new TargetAlignment());
        addSequential(new EjectPanel());
        addSequential(new PowerDrive(-0.45, 0.5));
        addSequential(new GyroTurn(-45));
        addSequential(new PowerDrive(-0.40, 2));
        addSequential(new GyroTurn(-135));
        addSequential(new TargetAlignment());
        addSequential(new PinPanel());
        addSequential(new GyroTurn(-30));
        addSequential(new PowerDrive(0.45, 2));
        addSequential(new GyroTurn(120));
        addSequential(new TargetAlignment());
        addSequential(new EjectPanel());
    }

    @Override
    protected void interrupted() {
        super.interrupted();
        Drivetrain.getInstance().kill();
        Claw.getInstance().kill();
    }
}