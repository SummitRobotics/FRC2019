package frc.robot.panelClaw;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotConstants;

public class RaiseClaw extends Command{
    private Claw claw = Claw.getIntance();

    private double direction;
    private boolean isDone = false;

    private final double ROTATIONS = 2/Math.PI;
    private final double POWER = 0.20;

    public RaiseClaw(double direction){
        this.direction = Math.copySign(1,direction);
    }
    @Override
    protected void initialize() {
        super.initialize();
    }
    @Override
    protected void execute() {
        while(claw.getArmDistance() * direction < (2/Math.PI * RobotConstants.TALON_TICKS_PER_ROT)){
            claw.runArm(0.20 * direction);
        }
        isDone = true;
    }
    @Override
    protected boolean isFinished() {
        return isDone;
    }
    @Override
    protected void end() {
        super.end();
    }

}