package frc.robot.panelclaw.clawcommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.panelclaw.Claw;
import frc.robot.robotcore.RobotConstants;

public class RaiseClaw extends Command{
    private Claw claw = Claw.getInstance();
    private Claw.ClawArmState position;

    private double direction, error, target; 
    //TODO - pid
    private final double POWER = 0.20;
    private final double THRESHOLD = 10;

    public RaiseClaw(Claw.ClawArmState position){
        requires(claw);
        this.position = position;
    }
    @Override
    protected void initialize() {
        target = RobotConstants.TALON_TICKS_PER_ROT * (position.value / 360);
        error = target - claw.getArmEncoder();
    }
    @Override
    protected void execute() {
        direction = Math.copySign(1, error);
        if((direction != 1) && (!claw.getClawLimit())){
            claw.runArm(POWER * direction);
            error = target - claw.getArmEncoder();
        }
    }
    @Override
    protected boolean isFinished() {
        return ((error > -THRESHOLD) && (error < THRESHOLD)) || ((direction != 1) && (!claw.getClawLimit()));
    }
    @Override
    protected void end() {
        super.end();
        claw.runArm(0);
        if((claw.getClawLimit()) && (claw.getClawArmState() == Claw.ClawArmState.UP)){
            claw.resetArmEncoder();
        }
    }

}