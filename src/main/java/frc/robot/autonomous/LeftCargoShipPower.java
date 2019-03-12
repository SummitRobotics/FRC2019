package frc.robot.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.drivetrain.drivetraincommands.GyroTurn;
import frc.robot.drivetrain.drivetraincommands.PowerDrive;
import frc.robot.drivetrain.drivetraincommands.RealGyroTurn;
import frc.robot.drivetrain.drivetraincommands.vision.TargetAlignment;
import frc.robot.panelclaw.chairautomation.EjectPanel;
import frc.robot.panelclaw.chairautomation.PinPanel;

public class LeftCargoShipPower extends CommandGroup{
    public LeftCargoShipPower(){
        addSequential(new PowerDrive(0.35, false, 2.0));
        addSequential(new TargetAlignment());
        addSequential(new EjectPanel());
        addSequential(new PowerDrive(-0.45, false, 0.5));
        addSequential(new RealGyroTurn(-45, 0.75));
        addSequential(new PowerDrive(-0.40, false, 2));
        addSequential(new RealGyroTurn(-135, 0.75));
        addSequential(new TargetAlignment());
        addSequential(new PinPanel());
        addSequential(new RealGyroTurn(-30, 0.75));
        addSequential(new PowerDrive(0.45, false, 2));
        addSequential(new RealGyroTurn(120, 0.75));
        addSequential(new TargetAlignment());
        addSequential(new EjectPanel());
    }
}