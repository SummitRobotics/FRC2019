package frc.robot.panelclaw.pegcommands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.panelclaw.Peg;
import frc.robot.panelclaw.Peg.PanelOuttakeState;

public class OuttakePanel extends InstantCommand{
    private Peg peg = Peg.getInstance();
    private PanelOuttakeState pegPosition;
    
    public OuttakePanel(PanelOuttakeState pegPosition){
        this.pegPosition = pegPosition;

    }
    @Override
    protected void initialize() {
        super.initialize();
    }
    @Override
    protected void execute() {
        peg.setPanel(pegPosition);
    }
    @Override
    protected void end() {
        super.end();
    }
}