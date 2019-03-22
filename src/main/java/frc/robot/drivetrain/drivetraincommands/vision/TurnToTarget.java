package frc.robot.drivetrain.drivetraincommands.vision;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.devices.Limelight;
import frc.robot.drivetrain.Drivetrain;

public class TurnToTarget extends Command {

    private Drivetrain drivetrain = Drivetrain.getInstance();
    private Limelight lemonlight = Limelight.getInstance();

    private double POWER = 0.375;
    
    public TurnToTarget(){
        setInterruptible(true);
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
    protected void end() {
        super.end();
        drivetrain.stopRobot();
    }
}