package frc.robot.lift;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.lift.Lift.LiftState;

public class TrimMast extends Command{
    private Lift lift = Lift.getInstance();

    public TrimMast (){
        requires(lift);
    }
    @Override
    protected void initialize() {
        super.initialize();
    }
    @Override
    protected void execute() {
        lift.runLiftManual(Robot.DriverProfileChooser.getSelected().getTrimPower());
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