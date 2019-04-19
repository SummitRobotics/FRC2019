package frc.robot.drivetrain.drivetraincommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.drivetrain.Drivetrain;

public class EncoderDrive extends Command{
    private double distance, leftTarget, rightTarget;

    private Drivetrain drivetrain = Drivetrain.getInstance();

    public EncoderDrive(double distance){
        setInterruptible(true);
        requires(drivetrain);
        this.distance = distance;
    }
    @Override
    protected void initialize() {
        drivetrain.setDrivetrainEncoders(0);
        drivetrain.toPosition(distance,distance);
        /*leftTarget = drivetrain.getLeftEncoder() + distance;
        rightTarget = drivetrain.getRightEncoder() + distance;
        drivetrain.toPosition(leftTarget, rightTarget);*/

    }
    @Override
    protected void execute() {
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
    }
}