package frc.robot.panelclaw.clawcommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.panelclaw.Claw;

public class LimitClaw extends Command {

    double power;
    Claw claw = Claw.getInstance();

    public LimitClaw(double power){
        requires(claw);
        this.power = power;
    }
    @Override
    protected void execute() {
        super.execute();
        claw.runArm(power);
    }
    @Override
    protected boolean isFinished() {
        return claw.getClawLimit();
    }
    @Override
    protected void interrupted() {
        super.interrupted();
    }
    @Override
    protected void end() {
        super.end();
        claw.kill();
    }
}