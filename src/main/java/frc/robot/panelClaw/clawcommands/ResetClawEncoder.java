package frc.robot.panelclaw.clawcommands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.panelclaw.Claw;

public class ResetClawEncoder extends InstantCommand {

    Claw claw = Claw.getInstance();

    public ResetClawEncoder() {
        requires(claw);
    }

    @Override
    protected void initialize(){
        super.initialize();
    }
    @Override
    protected void execute(){
        //claw.setArmEncoder(0);
    }
    @Override
    protected void end(){
        super.end();
    }
}