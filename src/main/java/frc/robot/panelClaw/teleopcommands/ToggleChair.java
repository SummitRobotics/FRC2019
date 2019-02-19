package frc.robot.panelclaw.teleopcommands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.panelclaw.Peg;

public class ToggleChair extends InstantCommand{
    private Peg peg = Peg.getInstance();
    private Peg.ChairState panelPosition;
    
    public ToggleChair(){
        requires(peg);
    }
    @Override
    protected void initialize() {
        super.initialize();
    }
    @Override
    protected void execute() {
        this.panelPosition = peg.toggleChair();
        peg.setChair(panelPosition);
    }
    @Override
    protected void end() {
        super.end();
    }
}