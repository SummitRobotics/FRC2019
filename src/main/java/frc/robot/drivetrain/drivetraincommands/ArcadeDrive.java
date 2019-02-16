package frc.robot.drivetrain.drivetraincommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.robotcore.OI;
import frc.robot.Robot;
import frc.robot.drivetrain.Drivetrain;

public class ArcadeDrive extends Command{
    Drivetrain drivetrain = Drivetrain.getInstance();
    OI gamepad = OI.getInstance();

    public ArcadeDrive(){
        requires(drivetrain);
    }
    @Override
    protected void initialize() {
        super.initialize();
    }
    @Override
    protected void execute() {
        drivetrain.robotDrive.arcadeDrive(gamepad.getForwardPower(), gamepad.getRotationalPower());
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