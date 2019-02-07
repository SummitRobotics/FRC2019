package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotConstants;

public class Claw extends Subsystem {

    private DoubleSolenoid claw;
    private boolean clawState;

    public Claw() {

        clawState = false;
        claw = new DoubleSolenoid(RobotConstants.Ports.CLAW_SOLENOID_OPEN, RobotConstants.Ports.CLAW_SOLENOID_CLOSE);
        openClaw();
    }

    @Override
    protected void initDefaultCommand() {
        
    }

    public void openClaw() {

        if (!clawState) {

            claw.set(Value.kForward);
            clawState = true;
        }
    }

    public void closeClaw() {

        if (clawState) {

            claw.set(Value.kReverse);
            clawState = false;
        }
    }
}