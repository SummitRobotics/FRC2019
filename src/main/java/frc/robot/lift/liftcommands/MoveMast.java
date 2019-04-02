package frc.robot.lift.liftcommands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.lift.Lift.LiftState;
import frc.robot.lift.Lift;

public class MoveMast extends Command{
    private Lift lift = Lift.getInstance();
    private LiftState position;

    private static final double THRESHOLD = 1;

    private boolean isDone = false;

    public MoveMast (LiftState position){
        requires(lift);
        this.position = position;
    }
    @Override
    protected void initialize() {
        super.initialize();
        lift.setMast(position);
    }
    @Override
    protected void execute() {
    
    }
    @Override
    protected boolean isFinished() {
        return lift.withinThreshold(THRESHOLD, position.value);
    }
    @Override
    protected void end() {
        super.end();
        SmartDashboard.putBoolean("Move mast done", true);
    }
}