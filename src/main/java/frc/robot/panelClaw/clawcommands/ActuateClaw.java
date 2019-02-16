package frc.robot.panelclaw.clawcommands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.panelclaw.Claw;
import frc.robot.panelclaw.Claw.ClawState;

public class ActuateClaw extends InstantCommand{

    private Claw claw = Claw.getIntance();
    private ClawState clawPosition;

    public ActuateClaw(ClawState clawPosition){
        requires(claw);
        this.clawPosition = clawPosition;
    }
    @Override
    protected void initialize() {
    }
    @Override
    protected void execute() {
        claw.setClaw(clawPosition);
    }
    @Override
    protected void end() {
        super.end();
    }
}