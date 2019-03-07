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

    }
    @Override
    protected void execute() {
        SmartDashboard.putBoolean("Yes", true);
        isDone = drivetrain.toPosition(distance);
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