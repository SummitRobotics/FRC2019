package frc.robot.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.drivetrain.drivetraincommands.EncoderDrive;
import frc.robot.drivetrain.drivetraincommands.GyroTurn;
import frc.robot.drivetrain.drivetraincommands.PowerDrive;
import frc.robot.drivetrain.drivetraincommands.RealGyroTurn;

public class Yeet extends CommandGroup{
    public Yeet(){
        //addSequential(new PowerDrive(1, 4.5));
        addSequential(new EncoderDrive(15));
        //addSequential(new RealGyroTurn(20, 0.35));
    }
}