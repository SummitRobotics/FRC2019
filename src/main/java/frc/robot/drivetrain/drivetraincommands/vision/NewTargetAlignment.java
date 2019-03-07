package frc.robot.drivetrain.drivetraincommands.vision;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.devices.Limelight;
import frc.robot.drivetrain.Drivetrain;
import frc.robot.drivetrain.drivetraincommands.EncoderDrive;

public class NewTargetAlignment extends CommandGroup{
    Drivetrain drivetrain = Drivetrain.getInstance();
    Limelight limelight = Limelight.getInstance();

    public NewTargetAlignment(){
        addSequential(new TargetAlignment(0.5));
        addSequential(new EncoderDrive(5));
    }
}