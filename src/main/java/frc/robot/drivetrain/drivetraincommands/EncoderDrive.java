package frc.robot.drivetrain.drivetraincommands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.drivetrain.Drivetrain;
import frc.robot.robotcore.RobotConstants;

public class EncoderDrive extends Command{
    private double distance, target;
    private boolean isDone;

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
        SmartDashboard.putBoolean("Encoder Command Finished ", true);
        isDone = drivetrain.toPosition(target);
    }
    @Override
    protected boolean isFinished() {
        return isDone;
        
    }
    @Override
    protected void end() {
        SmartDashboard.putBoolean("Yes", true);
        drivetrain.stopRobot();
    }
}