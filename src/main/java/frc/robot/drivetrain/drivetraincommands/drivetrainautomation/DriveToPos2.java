package frc.robot.drivetrain.drivetraincommands.drivetrainautomation;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.drivetrain.drivetraincommands.GyroTurn;
import frc.robot.drivetrain.drivetraincommands.PowerDrive;
import frc.robot.robotcore.universalcommands.Wait;

public class DriveToPos2 extends CommandGroup {

    public DriveToPos2(){
        addSequential(new GyroTurn(-10));
        addSequential(new PowerDrive(-0.7, 2.4));
        addSequential(new GyroTurn(-35));
    }
}