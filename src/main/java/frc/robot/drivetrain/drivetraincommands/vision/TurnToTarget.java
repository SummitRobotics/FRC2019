package frc.robot.drivetrain.drivetraincommands.vision;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.devices.Limelight;
import frc.robot.drivetrain.Drivetrain;
import frc.robot.robotcore.RobotBuilder;

public class TurnToTarget extends Command {

    private Drivetrain drivetrain = Drivetrain.getInstance();
    private Limelight lemonlight = RobotBuilder.getInstance().lemonlight;

    private double POWER = 0.375;
    
    public TurnToTarget(){
        setInterruptible(true);
        requires(drivetrain);
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
        if(!lemonlight.isTarget()){
            drivetrain.robotDrive.tankDrive(POWER, -POWER);
        }
    }

    @Override
    protected boolean isFinished() {
        return lemonlight.isTarget();
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