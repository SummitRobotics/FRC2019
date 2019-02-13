package frc.robot.drivetrain;

import edu.wpi.first.wpilibj.command.Command;

public class Turn extends Command{
    private Drivetrain drivetrain = Drivetrain.getInstance();
    private double angle, power;
    private double targetAngle, direction, error;
    private final double THRESHOLD = 3;

    public Turn(double angle, double power){
        requires(drivetrain);
        this.angle = angle;
        this.power = power;

    }
    /*public Turn(double angle, double radius, double power){
        
    }*/
    @Override
    protected void initialize() {
        targetAngle = drivetrain.getGyroYaw() + angle;
        error = targetAngle - drivetrain.getGyroYaw();
    }
    @Override
    protected void execute() {
        direction = Math.copySign(1, error);
        drivetrain.robotDrive.tankDrive(-power * direction, power * direction);
        error = targetAngle - drivetrain.getGyroYaw();
    }
    @Override
    protected boolean isFinished() {
        return ((error <= THRESHOLD) && (error >= -THRESHOLD));
    }
    @Override
    protected void end() {
        drivetrain.stopRobot();
    }
}