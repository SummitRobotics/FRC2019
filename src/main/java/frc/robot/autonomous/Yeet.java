package frc.robot.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.drivetrain.drivetraincommands.PowerDrive;

public class Yeet extends CommandGroup{
    public Yeet(){
        addSequential(new PowerDrive(1, 8));
    }
}