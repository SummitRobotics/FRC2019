package frc.robot.lift.liftcommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.robotcore.OI;
import frc.robot.lift.Lift;

public class MoveMastManual extends Command{
    private Lift lift = Lift.getInstance();
    public MoveMastManual(){
        requires(lift);
    }
    @Override
    protected void initialize() {
        super.initialize();
    }
    @Override
    protected void execute() {
        lift.runLiftManual(OI.getInstance().getLeftJoystickY());
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