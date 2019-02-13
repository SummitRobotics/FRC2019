package frc.robot.drivetrain.drivetraincommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.drivetrain.Drivetrain;

public class Shift extends Command{
    private boolean isDone;

    private Drivetrain drivetrain = Drivetrain.getInstance();
    private Drivetrain.GearState gearValue;

    public Shift(Drivetrain.GearState gearValue){
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