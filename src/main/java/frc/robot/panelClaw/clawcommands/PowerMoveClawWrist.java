package frc.robot.panelclaw.clawcommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.panelclaw.Claw;

public class PowerMoveClawWrist extends Command {

    private double power;
    private Claw claw = Claw.getInstance();

    public PowerMoveClawWrist(double timeout, Claw.ClawSpeed clawSpeed) {
        setInterruptible(true);
        requires(claw);
        setTimeout(timeout);
        this.power = clawSpeed.value;
    }

    @Override
    protected void initialize() {
        super.initialize();
    }
    @Override
    protected void execute() {
        claw.runArm(power);
    }
    @Override
    protected boolean isFinished() {
        return isTimedOut();
    }
    @Override
    protected void interrupted() {
        super.interrupted();
        claw.kill();
    }
}