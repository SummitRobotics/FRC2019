package frc.robot.lift;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.lift.Lift.LiftState;

public class MoveMast extends Command{
    private Lift lift = Lift.getInstance();
    private LiftState position;

    public MoveMast (LiftState position){
        requires(lift);
    }
    @Override
    protected void initialize() {
        super.initialize();
    }
    @Override
    protected void execute() {
        switch(position){
            case LOW:  
                break;
            case MID: 
                break;
            case HIGH: 
                break;
        }
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