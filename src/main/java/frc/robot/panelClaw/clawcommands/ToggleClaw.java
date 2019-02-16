package frc.robot.panelclaw.clawcommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.panelclaw.Claw;
import frc.robot.panelclaw.Claw.ClawState;

public class ToggleClaw extends Command{

    private Claw claw = Claw.getIntance();
    private boolean isComplete = false;

    public ToggleClaw(){
        requires(claw);
    }
    @Override
    protected void initialize() {
    }
    @Override
    protected void execute() {
        claw.toggleClaw();
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