package frc.robot.lift;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotConstants;
import frc.robot.lift.Lift.LiftState;

public class MoveMast extends Command{
    private Lift lift = Lift.getInstance();
    private LiftState position;
    private double power;
    private final double THRESHOLD  = 10;
    private boolean isDone = false;

    public MoveMast (LiftState position, double power){
        requires(lift);
        this.position = position;
        this.power = power;
    }
    @Override
    protected void initialize() {
        super.initialize();
    }
    @Override
    protected void execute() {
        switch(position){
            case LOW:
                 runToPosition(LiftState.LOW, power);
                break;
            case MID: 
                runToPosition(LiftState.MID, power);
                break;
            case HIGH:
                runToPosition(LiftState.HIGH, power); 
                break;
        }
        isDone = true;
    }
    
    public void runToPosition(LiftState position, double power){
        double target = lift.getEncoderPos() + RobotConstants.NEO_INCHES_TO_TICKS(position.value);
        double error =  target - lift.getEncoderPos();
        double direction = Math.copySign(1, error);
        while(!(error > -THRESHOLD) && !(error < THRESHOLD)){
            if((direction == -1) && (lift.getLowLimit())){
                break;
            }
            else if((direction == 1) && (lift.getHighLimit())){
                break;
            }
            lift.runLiftManual(power * direction);
            error =  target - lift.getEncoderPos();
        }
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