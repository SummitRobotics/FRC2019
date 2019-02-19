package frc.robot.panelclaw.teleopcommands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.panelclaw.Peg;

public class TogglePeg extends InstantCommand{

    private Peg peg = Peg.getInstance();
    private Peg.PegState pegPosition;

    public TogglePeg(){
        requires(peg);
    }
    @Override
    protected void initialize() {
    }
    @Override
    protected void execute() {
        this.pegPosition = peg.togglePeg();
        peg.setPeg(pegPosition);
    }
    @Override
    protected void end() {
        super.end();
    }
}