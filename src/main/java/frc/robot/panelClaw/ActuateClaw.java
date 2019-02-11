package frc.robot.panelClaw;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.panelClaw.Claw.ClawState;

public class ActuateClaw extends Command{

    private Claw claw = Claw.getIntance();
    private ClawState clawValue;
    private boolean isComplete = false;

    public ActuateClaw(ClawState clawValue){
        requires(claw);
        this.clawValue = clawValue;
    }
    @Override
    protected void initialize() {
    }
    @Override
    protected void execute() {
        claw.setClaw(clawValue);
        isComplete = true;
    }
    @Override
    protected boolean isFinished() {
        return isComplete;
    }
    @Override
    protected void end() {
        super.end();
    }
}