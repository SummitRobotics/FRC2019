package frc.robot.lift.liftcommands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.lift.Lift.LiftState;
import frc.robot.lift.Lift;

public class MoveMast extends InstantCommand{
    private Lift lift = Lift.getInstance();
    private LiftState position;

    private boolean isDone = false;

    public MoveMast (LiftState position){
        setInterruptible(true);
        requires(lift);
        this.position = position;
    }
    @Override
    protected void initialize() {
        super.initialize();
    }
    @Override
    protected void execute() {
        lift.setMast(position);
    }
    
    @Override
    protected boolean isFinished() {
        return false;
    }
    @Override
    protected void interrupted() {
        super.interrupted();
        lift.kill();
    }
    @Override
    protected void end() {
        super.end();
    }

}