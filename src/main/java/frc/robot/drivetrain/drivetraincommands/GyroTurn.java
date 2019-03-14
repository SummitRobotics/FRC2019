package frc.robot.drivetrain.drivetraincommands;

import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.robot.drivetrain.Drivetrain;

public class GyroTurn extends PIDCommand{

    private Drivetrain drivetrain = Drivetrain.getInstance();

    private double angle, target, error;
    private static final double
    PERCENT_TOLERANCE = 5,
    THRESHOLD = 3;
    private static final double
    P = 0.11,
    I = 0,
    D = 0.001;

    public GyroTurn(double angle){
        super(P, I, D);
        this.angle = angle;
        getPIDController().setPercentTolerance(PERCENT_TOLERANCE);
    }
    /*public GyroTurn(double angle, double radius, double power){
        
    }*/
    @Override
    protected void initialize() {
        target = angle + drivetrain.getYaw();
        error = target - drivetrain.getYaw();
        setSetpoint(0);
    }
    @Override
    protected double returnPIDInput() {
        return drivetrain.getYaw();
    }
    @Override
    protected void usePIDOutput(double output) {
        error = target - drivetrain.getYaw();
        drivetrain.robotDrive.tankDrive(error * output, -error * output);
    }

    @Override
    protected boolean isFinished() {
        //return (error < THRESHOLD) && (error > -THRESHOLD);
        return getPIDController().getError()  == 0;
    }

    @Override
    protected void end() {
        super.end();
        drivetrain.robotDrive.tankDrive(0,0);
    }

}