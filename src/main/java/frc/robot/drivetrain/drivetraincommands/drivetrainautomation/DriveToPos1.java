package frc.robot.drivetrain.drivetraincommands.drivetrainautomation;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.drivetrain.Drivetrain;
import frc.robot.drivetrain.drivetraincommands.GyroTurn;
import frc.robot.drivetrain.drivetraincommands.PowerDrive;


public class DriveToPos1 extends CommandGroup {

    public DriveToPos1(){
        addSequential(new GyroTurn(10));
        addSequential(new PowerDrive(-0.7, 2.4));
        addSequential(new GyroTurn(35));
    }
}