package frc.robot.drivetrain.drivetraincommands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.robotcore.userinput.OI;
import frc.robot.drivetrain.Drivetrain;

public class ArcadeDrive extends Command{
    Drivetrain drivetrain = Drivetrain.getInstance();
    OI gamepad = OI.getInstance();

    public ArcadeDrive(){
        requires(drivetrain);
        setInterruptible(true);
    }
    @Override
    protected void initialize() {
        super.initialize();
    }
    @Override
    protected void execute() {
        SmartDashboard.putNumber("Driving", 1);
        double xSpeed = gamepad.driver1.getForwardPower();
        double zRotation = gamepad.driver1.getRotationalPower();
        SmartDashboard.putNumber("Xspeed", xSpeed);
        SmartDashboard.putNumber("Zrotation", zRotation);
        drivetrain.robotDrive.arcadeDrive(xSpeed, zRotation);
    }
    @Override
    protected boolean isFinished() {
        return false;
    }
    @Override
    protected void end() {
        SmartDashboard.putNumber("Driving", 0);
        super.end();
    }
}