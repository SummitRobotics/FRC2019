package frc.robot.drivetrain.drivetraincommands;

import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.robot.robotcore.RobotConstants;
import frc.robot.drivetrain.Drivetrain;

public class EncoderDrive extends PIDCommand{
    private double leftTarget, rightTarget;
    private double leftError, rightError;
    private double power, distance;
    private final double THRESHOLD = 25;
    private static final double
    P = 1,
    I = 0, 
    D = 0;

    private Drivetrain drivetrain = Drivetrain.getInstance();

    public EncoderDrive(double distance, double power){
        super(P, I, D);
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
            drivetrain.robotDrive.tankDrive(power, power);
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