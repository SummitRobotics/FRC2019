package frc.robot.panelClaw;

import edu.wpi.first.wpilibj.command.Command;

public class ActuateClaw extends Command{

    private Claw claw = Claw.getIntance();

    public ActuateClaw(){
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
        return false;
    }
    @Override
    protected void end() {
        super.end();
    }
}