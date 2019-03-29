package frc.robot.panelclaw.clawcommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.panelclaw.Claw;
import frc.robot.robotcore.RobotConstants;

public class RaiseClaw extends Command{
    private Claw claw = Claw.getInstance();
    private Claw.ClawArmState position;

    private boolean isDone = false;


    public RaiseClaw(Claw.ClawArmState position){
        requires(claw);
        this.position = position;
    }
    @Override
    protected void initialize() {
        super.initialize();
    }
    @Override
    protected void execute() {
        isDone = claw.setArm(position.value);
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