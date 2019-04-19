package frc.robot.drivetrain.drivetraincommands.drivetrainautomation;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.drivetrain.drivetraincommands.GyroTurn;
import frc.robot.drivetrain.drivetraincommands.PowerDrive;
import frc.robot.robotcore.universalcommands.Wait;

public class DriveToPos4 extends CommandGroup {

    public DriveToPos4(){
        addSequential(new GyroTurn(-10));
        addSequential(new PowerDrive(-0.7, 1.2));
        addSequential(new GyroTurn(-120));
    }
}