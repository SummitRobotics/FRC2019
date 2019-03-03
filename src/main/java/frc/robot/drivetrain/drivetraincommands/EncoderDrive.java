package frc.robot.drivetrain.drivetraincommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.drivetrain.Drivetrain;
import frc.robot.robotcore.RobotConstants;

public class EncoderDrive extends Command{
    private double distance, target;

    private Drivetrain drivetrain = Drivetrain.getInstance();

    public EncoderDrive(double distance){
        this.distance = distance;
    }
    @Override
    protected void initialize() {
        target = RobotConstants.NEO_INCHES_TO_TICKS(distance);
    }
    @Override
    protected void execute() {
        drivetrain.toPosition(target);
    }
    @Override
    protected boolean isFinished() {
        return false;
    }
    @Override
    protected void end() {

    }
}