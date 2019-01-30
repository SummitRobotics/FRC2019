package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ArcadeDrive extends Command{

    public ArcadeDrive(){
        requires(Robot.drivetrain);
    }

    @Override
    protected void initialize() {
        super.initialize();
    }

    @Override
    protected void execute() {
        //TODO - Less statics pls after testing
        Robot.drivetrain.robotDrive.arcadeDrive(Robot.OI.getForwardInput(), Robot.OI.getRotationalInput());
    }
    @Override
    protected void end() {
        super.end();
    }
    @Override
    protected boolean isFinished(){
        return false;
    }
}