package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotConstants;

public class Claw extends Subsystem{

    private DoubleSolenoid claw;
    private boolean clawState;

    public Claw(){
        claw = new DoubleSolenoid(RobotConstants.Ports.CLAW_SOLENOID_OPEN, RobotConstants.Ports.CLAW_SOLENOID_CLOSE);
    }

    @Override
    protected void initDefaultCommand() {
        //nah
    }

    public void openClaw(){
        claw.set(Value.kForward);
        clawState = true;
    }

    public void closeClaw(){
        claw.set(Value.kReverse);
        clawState = false;
    }

}