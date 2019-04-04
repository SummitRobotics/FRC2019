package frc.robot.drivetrain.drivetraincommands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.drivetrain.Drivetrain;
import frc.robot.robotcore.RobotConstants;

public class EncoderDrive extends Command{
    private double distance, leftTarget, rightTarget;
    private boolean isDone;

    private Drivetrain drivetrain = Drivetrain.getInstance();

    public EncoderDrive(double distance){
        setInterruptible(true);
        requires(drivetrain);
        this.distance = distance;
    }
    @Override
    protected void initialize() {
        SmartDashboard.putBoolean("Encoder command finished", false);
        /*leftTarget = drivetrain.getLeftEncoder() + RobotConstants.DRIVETRAIN_INCHES_TO_TICKS(distance);
        rightTarget = drivetrain.getRightEncoder() + RobotConstants.DRIVETRAIN_INCHES_TO_TICKS(distance);
        SmartDashboard.putNumber("Left Target for Drivetrain", leftTarget);
        SmartDashboard.putNumber("right target for drivetrain", rightTarget);*/
        drivetrain.toPosition(170, 170);

    }
    @Override
    protected void execute() {
        SmartDashboard.putNumber("Left Pos", drivetrain.getLeftEncoder());
        SmartDashboard.putNumber("Right Pos", drivetrain.getRightEncoder());
    }
    @Override
    protected boolean isFinished() {
        return drivetrain.isInThreshold(leftTarget) || drivetrain.isInThreshold(rightTarget);
    }
    @Override
    protected void interrupted() {
        super.interrupted();
        drivetrain.kill();
    }
    @Override
    protected void end() {
        drivetrain.kill();
        SmartDashboard.putBoolean("Encoder command finished", true);
    }
}