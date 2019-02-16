package frc.robot.panelclaw.pegcommands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.panelclaw.Peg;

public class ActuatePeg extends InstantCommand{

    private Peg peg = Peg.getInstance();
    private Peg.PegState pegPosition;

    public ActuatePeg(Peg.PegState pegPosition){
        requires(peg);
        this.pegPosition = pegPosition;
    }
    @Override
    protected void initialize() {
    }
    @Override
    protected void execute() {
        peg.setPeg(pegPosition);
    }
    @Override
    protected void end() {
        super.end();
    }
}