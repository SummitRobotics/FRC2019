package frc.robot.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ArcadeDrive extends Command{
    Drivetrain drivetrain = Drivetrain.getInstance();

    public ArcadeDrive(){

    }
    @Override
    protected void initialize() {
        super.initialize();
    }
    @Override
    protected void execute() {
        drivetrain.robotDrive.arcadeDrive(Robot.DriverProfileChooser.getSelected().getForwardPower(), Robot.DriverProfileChooser.getSelected().getRotationalPower());
    }
    @Override
    protected boolean isFinished() {
        return false;
    }
    @Override
    protected void end() {
        super.end();
    }
}