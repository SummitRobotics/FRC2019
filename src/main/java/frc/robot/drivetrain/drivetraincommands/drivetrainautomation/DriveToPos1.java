package frc.robot.drivetrain.drivetraincommands.drivetrainautomation;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.climber.climbautomation.Level2Descend;
import frc.robot.drivetrain.drivetraincommands.GyroTurn;

public class DriveToPos1 extends CommandGroup {

    public DriveToPos1(boolean isRight){
        //addSequential(new EncoderDrive(12));
        //addSequential(new EncoderDrive(3));
        addSequential(new Level2Descend());
        //addSequential(new PowerDrive(-0.35, 2.0));
        //addSequential(new GyroTurn(45));
        //addSequential(new PowerDrive(-0.70, false, 2.0));
        //addSequential(new GyroTurn(-45));
        //addSequential(new TargetAlignment());

    }
}