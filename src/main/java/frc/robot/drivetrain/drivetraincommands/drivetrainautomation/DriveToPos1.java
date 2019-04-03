package frc.robot.drivetrain.drivetraincommands.drivetrainautomation;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.drivetrain.drivetraincommands.GyroTurn;
import frc.robot.drivetrain.drivetraincommands.PowerDrive;
import frc.robot.drivetrain.drivetraincommands.vision.TargetAlignment;

public class DriveToPos1 extends CommandGroup {

    public DriveToPos1(boolean isRight){
        addSequential(new PowerDrive(-0.35, 2.0));
        addSequential(new GyroTurn(30));
        //addSequential(new PowerDrive(-0.70, false, 2.0));
        //addSequential(new GyroTurn(-45));
        //addSequential(new TargetAlignment());

    }
}