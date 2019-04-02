package frc.robot.drivetrain.drivetraincommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.drivetrain.Drivetrain;

public class PowerDrive extends Command{
    private Drivetrain drivetrain = Drivetrain.getInstance();
    private double power;
    private boolean isTurn;
    
    public PowerDrive(double power, double timeout){
        setInterruptible(true);
        requires(drivetrain);

        this.power = -power;
        setTimeout(timeout);
        this.isTurn = false;
    }
    public PowerDrive(double power, boolean isTurn, double timeout){
        setInterruptible(true);
        requires(drivetrain);
        
        setTimeout(timeout);
        this.power = -power;
        this.isTurn = isTurn;
    
    }
    @Override
    protected void initialize() {
        super.initialize();
    }
    @Override
    protected void execute() {
        if(isTurn){
            drivetrain.robotDrive.tankDrive(-power, power);
        }
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
        drivetrain.stopRobot();
    }
}