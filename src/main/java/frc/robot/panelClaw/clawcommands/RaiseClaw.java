package frc.robot.panelclaw.clawcommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.panelclaw.Claw;
import frc.robot.robotcore.RobotConstants;

public class RaiseClaw extends Command{
    private Claw claw = Claw.getIntance();
    private Claw.ClawArmState position;

    private double direction, error; 
    //TODO - pid
    private final double POWER = 0.20;
    private final double THRESHOLD = 10;

    public RaiseClaw(Claw.ClawArmState position){
        this.position = position;
    }
    @Override
    protected void initialize() {
        error = RobotConstants.TALON_TICKS_PER_ROT * (position.value / 360);
    }
    @Override
    protected void execute() {
        direction = Math.copySign(1, error);
        if((direction != 1) && (!claw.isAtLimit())){
            claw.runArm(POWER * direction);
            error = RobotConstants.TALON_TICKS_PER_ROT * (position.value / 360);
        }
    }
    @Override
    protected boolean isFinished() {
        return ((error > -THRESHOLD) && (error < THRESHOLD)) || ((direction != 1) && (!claw.isAtLimit()));
    }
    @Override
    protected void end() {
        super.end();
        claw.runArm(0);
        if((claw.isAtLimit()) && (claw.getClawArmState() == 0)){
            claw.resetArmEncoder();
        }
    }

}