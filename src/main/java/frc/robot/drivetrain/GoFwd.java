package frc.robot.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotConstants;

public class GoFwd extends Command{
    private double leftTarget, rightTarget;
    private double leftError, rightError;
    private double power, distance;
    private final double THRESHOLD = 25;

    private Drivetrain drivetrain = Drivetrain.getInstance();

    public GoFwd(double distance, double power){
        requires(drivetrain);
        this.power = power;
        this.distance = distance;
    }
    @Override
    protected void initialize() {
        leftTarget = drivetrain.getLeftEncoderPos() + RobotConstants.NEO_INCHES_TO_TICKS(distance);
        rightTarget = drivetrain.getRightEncoderPos() + RobotConstants.NEO_INCHES_TO_TICKS(distance);
    }
    @Override
    protected void execute() {
        while(((leftError > THRESHOLD) || (leftError < -THRESHOLD)) && ((rightError > THRESHOLD) || (rightError < -THRESHOLD))){
            drivetrain.robotDrive.tankDrive(power * leftDirection, power * rightDirection);
            leftError = leftTarget - drivetrain.getLeftEncoderPos();
            rightError = rightTarget - drivetrain.getRightEncoderPos();
        }
        drivetrain.stopRobot();
    }
    @Override
    protected boolean isFinished() {
        return ((leftError <= THRESHOLD) && (leftError >= -THRESHOLD)
                ||(rightError <= THRESHOLD) && (rightError >= -THRESHOLD));
    }
    @Override
    protected void end() {

    }
}