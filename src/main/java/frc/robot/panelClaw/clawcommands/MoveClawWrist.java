package frc.robot.panelclaw.clawcommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.panelclaw.Claw;
import frc.robot.panelclaw.Peg;
import frc.robot.robotcore.userinput.OI;

public class MoveClawWrist extends Command{
    private Claw claw = Claw.getInstance();
    private OI gamepad = OI.getInstance();
    private final double POWER = 0.30;

    public MoveClawWrist(){
        requires(claw);
    }

    @Override
    protected void initialize() {
        super.initialize();
    }
    @Override
    protected void execute() {
        claw.runArm(gamepad.clawArmDrive());
    }
    @Override
    protected boolean isFinished() {
        //return claw.getClawLimit();
        return false;
    }
    @Override
    protected void end() {
        super.end();
        //claw.runArm(0);
        /*Peg.getInstance().setPeg(Peg.PegState.UP);
        claw.setClaw(Claw.ClawState.OPEN);*/
    }
}