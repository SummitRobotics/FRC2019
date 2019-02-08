package frc.robot.panelClaw;

import edu.wpi.first.wpilibj.command.Command;

public class DetectPanel extends Command{
    private Claw claw = Claw.getIntance();

    public DetectPanel(){
        requires(claw);
    }

    @Override
    protected void initialize() {
        super.initialize();
    }
    @Override
    protected void execute() {
        super.execute();
    }
    @Override
    protected boolean isFinished() {
        return claw.isPanelPresent();
    }
    @Override
    protected void end() {
        super.end();
    }
}