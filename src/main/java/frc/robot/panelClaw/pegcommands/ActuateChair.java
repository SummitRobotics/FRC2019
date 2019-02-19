package frc.robot.panelclaw.pegcommands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.panelclaw.Peg;

public class ActuateChair extends InstantCommand{
    private Peg peg = Peg.getInstance();
    private Peg.ChairState chairPos ;

    public ActuateChair(Peg.ChairState chairPos){
        requires(peg);
        this.chairPos = chairPos;
    }
    @Override
    protected void initialize() {
        super.initialize();
    }
    @Override
    protected void execute() {
        peg.setChair(chairPos);
    }
    @Override
    protected void end() {
        super.end();
    }
}