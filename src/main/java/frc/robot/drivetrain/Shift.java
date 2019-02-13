package frc.robot.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.drivetrain.Drivetrain.GearState;

public class Shift extends InstantCommand{
    private boolean isDone;

    private Drivetrain drivetrain = Drivetrain.getInstance();
    private GearState gearValue;

    public Shift(GearState gearValue){
        requires(drivetrain);
        setInterruptible(false);
        this.gearValue = gearValue;
    }

    @Override
    protected void initialize() {
        super.initialize();
    }
    @Override
    protected void execute() {
        drivetrain.shiftGear(gearValue);
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