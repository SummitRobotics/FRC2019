package frc.robot.panelclaw.teleopcommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.panelclaw.Claw;

public class ToggleClaw extends Command{
    private Claw claw = Claw.getInstance();
    private Claw.ClawState clawPos;

    public ToggleClaw(){
        requires(claw);
    }

    @Override
    protected void initialize() {
        super.initialize();
    }
    @Override
    protected void execute() {
        clawPos = claw.toggleClaw();
        claw.setClaw(clawPos);
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