package frc.robot.drivetrain.drivetraincommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.drivetrain.Drivetrain;

public class PowerDrive extends Command{
    private Drivetrain drivetrain = Drivetrain.getInstance();
    private double power;
    
    public PowerDrive(double power, double timeout){
        setInterruptible(true);
        requires(drivetrain);

        this.power = power;
        setTimeout(timeout);
    }

    @Override
    protected void initialize() {
        super.initialize();
    }
    @Override
    protected void execute() {
        drivetrain.robotDrive.tankDrive(power, power);
    }
    @Override
    protected boolean isFinished() {
        return isTimedOut();
    }
    @Override
    protected void interrupted() {
        super.interrupted();
        drivetrain.kill();
    }
    @Override
    protected void end() {
        super.end();
        drivetrain.kill();
    }
}