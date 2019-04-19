package frc.robot.drivetrain.drivetraincommands.drivetrainautomation;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.drivetrain.drivetraincommands.GyroTurn;
import frc.robot.drivetrain.drivetraincommands.PowerDrive;

public class DriveToPos3 extends CommandGroup {

    public DriveToPos3(){
        addSequential(new GyroTurn(10));
        addSequential(new PowerDrive(-0.7, 1.2));
        addSequential(new GyroTurn(120));
    }
}