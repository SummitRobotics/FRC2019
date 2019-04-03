package frc.robot.drivetrain.drivetraincommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.robotcore.userinput.OI;
import frc.robot.drivetrain.Drivetrain;

public class ArcadeDrive extends Command{
    Drivetrain drivetrain = Drivetrain.getInstance();
    OI gamepad = OI.getInstance();

    public ArcadeDrive(){
        setInterruptible(true);
        requires(drivetrain);
    }
    @Override
    protected void initialize() {
        super.initialize();
    }
    @Override
    protected void execute() {
        /*double xSpeed = gamepad.driver1.getForwardPower();
        double zRotation = gamepad.driver1.getRotationalPower();*/
        double xSpeed = gamepad.fwdDrive();
        double zRotation = gamepad.turnDrive();
        drivetrain.robotDrive.arcadeDrive(xSpeed, zRotation);
    }
    @Override
    protected boolean isFinished() {
        return false;
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