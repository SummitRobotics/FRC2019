package frc.robot.lift.liftcommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.lift.Lift.LiftState;
import frc.robot.lift.Lift;

public class MoveMast extends Command{
    private Lift lift = Lift.getInstance();
    private LiftState position;

    private boolean isDone = false;

    public MoveMast (LiftState position){
        requires(lift);
        this.position = position;
    }
    @Override
    protected void initialize() {
        super.initialize();
    }
    @Override
    protected void execute() {
        isDone = lift.setMast(position);
    }
    
    @Override
    protected boolean isFinished() {
        return isDone;
    }
    @Override
    protected void end() {
        super.end();
    }

}