package frc.robot.panelclaw.teleopcommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.panelclaw.Claw;
import frc.robot.robotcore.OI;

public class MoveClawWrist extends Command{
    private Claw claw = Claw.getInstance();
    //private OI gamepad = OI.getInstance();

    private final double POWER = 0.45;

    public MoveClawWrist(){
        requires(claw);
    }

    @Override
    protected void initialize() {
        super.initialize();
    }
    @Override
    protected void execute() {
        if(Robot.gamepad.isDpadLeft()){
            claw.runArm(POWER);
        }
        else if(Robot.gamepad.isDpadRight()){
            claw.runArm(-POWER);
        }
        else{
            claw.runArm(0);
        }
    }
    @Override
    protected boolean isFinished() {
        return false;
    }
    @Override
    protected void end() {
        super.end();
        claw.runArm(0);
    }
}