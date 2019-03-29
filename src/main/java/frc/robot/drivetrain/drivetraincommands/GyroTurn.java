package frc.robot.drivetrain.drivetraincommands;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.drivetrain.Drivetrain;

public class GyroTurn extends PIDCommand{

    private Drivetrain drivetrain = Drivetrain.getInstance();

    private double angle, target;
    private final double THRESHOLD = 1;
    private static final double
    P = 0.045,
    I = 0.00,
    D = 0.1;

    public GyroTurn(double angle){
        super(P, I, D, Drivetrain.getInstance());
        setInterruptible(true);
        requires(drivetrain);
        this.angle = angle;
    }
    /*public GyroTurn(double angle, double radius, double power){
        
    }*/
    @Override
    protected void initialize() {
        target = angle + drivetrain.getYaw();
        setSetpoint(target);
    }
    @Override
    protected double returnPIDInput() {
        return drivetrain.getYaw();
    }
    @Override
    protected void usePIDOutput(double output) {
        SmartDashboard.putNumber("Gyro", drivetrain.getYaw());
        drivetrain.robotDrive.tankDrive(-output/1.4, output/1.4);
    }
    @Override
    protected boolean isFinished() {
        double error = getPIDController().getError();
        return (error < THRESHOLD) && (error > -THRESHOLD);
    }
    @Override
    protected void interrupted() {
        super.interrupted();
        drivetrain.kill();
    }
    @Override
    protected void end() {
        super.end();
    }
}