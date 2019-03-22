package frc.robot.drivetrain.drivetraincommands;

import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.robot.drivetrain.Drivetrain;

public class GyroTurn extends PIDCommand{

    private Drivetrain drivetrain = Drivetrain.getInstance();

    private double angle, target;
    private final double THRESHOLD = 1;
    private static final double
    P = 0.05,
    I = 0.005,
    D = 0.06;

    public GyroTurn(double angle){
        setInterruptible(true);
        super(P, I, D, Drivetrain.getInstance());
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
        drivetrain.robotDrive.tankDrive(output, -output);
    }

    @Override
    protected boolean isFinished() {
        double error = getPIDController().getError();
        return (error < THRESHOLD) && (error > -THRESHOLD);
    }

    @Override
    protected void end() {
        super.end();
    }
}