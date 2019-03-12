package frc.robot.drivetrain.drivetraincommands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.drivetrain.Drivetrain;

public class RealGyroTurn extends Command{

    private Drivetrain drivetrain = Drivetrain.getInstance();

    private double angle, power, target, error;
    private static final double TOLERANCE = 1;

    public RealGyroTurn(double angle, double power){
        this.angle = angle;
        this.power = power;
       }
    /*public GyroTurn(double angle, double radius, double power){
        
    }*/
    
    @Override
    protected void initialize() {
        target = angle + drivetrain.getYaw();
    }

    @Override
    protected void execute() {
        error = target - drivetrain.getYaw();
        if(error > 0){
            drivetrain.robotDrive.tankDrive(power, -power);
        }
        if(error < 0){
            drivetrain.robotDrive.tankDrive(-power, power);
        }
    }

    @Override
    protected boolean isFinished() {
        return (error < TOLERANCE) && (error > -TOLERANCE);
    }

    @Override
    protected void end() {
        super.end();
        drivetrain.robotDrive.tankDrive(0,0);
    }

}