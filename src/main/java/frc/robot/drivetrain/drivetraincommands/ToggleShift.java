package frc.robot.drivetrain.drivetraincommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.drivetrain.Drivetrain;

public class ToggleShift extends Command{

    private Drivetrain drivetrain = Drivetrain.getInstance();

    public ToggleShift(){
        requires(drivetrain);
        setInterruptible(false);
    }

    @Override
    protected void initialize() {
        super.initialize();
    }
    @Override
    protected void execute() {
        drivetrain.toggleGear();
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