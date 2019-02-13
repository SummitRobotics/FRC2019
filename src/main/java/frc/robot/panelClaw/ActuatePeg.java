package frc.robot.panelClaw;

import edu.wpi.first.wpilibj.command.Command;

public class ActuatePeg extends Command{

    private Peg peg = Peg.getInstance();
    private boolean isComplete = false;

    public ActuatePeg(){
        requires(peg);
    }
    @Override
    protected void initialize() {
    }
    @Override
    protected void execute() {
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