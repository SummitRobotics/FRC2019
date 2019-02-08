package frc.robot.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.drivetrain.Drivetrain.GearState;

public class Shift extends Command{
    private boolean isDone;

    private Drivetrain drivetrain = Drivetrain.getInstance();
    private GearState gearState;

    public Shift(GearState gearState){
        requires(drivetrain);
        setInterruptible(false);
        this.gearState = gearState;
    }

    @Override
    protected void initialize() {
        super.initialize();
    }
    @Override
    protected void execute() {
        super.execute();
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