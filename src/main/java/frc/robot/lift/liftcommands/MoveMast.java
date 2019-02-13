package frc.robot.lift.liftcommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.robotcore.RobotConstants;
import frc.robot.lift.Lift;

public class MoveMast extends Command{
    private Lift lift = Lift.getInstance();
    private Lift.LiftState position;
    private double power;
    private final double THRESHOLD  = 10;
    private boolean isDone = false;

    public MoveMast (Lift.LiftState position, double power){
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
                runToPosition(Lift.LiftState.LOW, power);
                break;
            case MID: 
                runToPosition(Lift.LiftState.MID, power);
                break;
            case HIGH:
                runToPosition(Lift.LiftState.HIGH, power); 
                break;
        }
        isDone = true;
    }
    
    public void runToPosition(Lift.LiftState position, double power){
        double target = RobotConstants.NEO_INCHES_TO_TICKS(position.value);
        double error =  target - lift.getEncoderPos();
        double direction = Math.copySign(1, error);
        while(!(error > -THRESHOLD) && (error < THRESHOLD)){
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