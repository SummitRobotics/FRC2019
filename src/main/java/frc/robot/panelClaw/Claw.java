package frc.robot.panelClaw;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotConstants;
import frc.robot.sensors.ColorSensor;
import edu.wpi.first.wpilibj.I2C;

public class Claw extends Subsystem {

    public enum ClawState{
        OPEN(Value.kForward),
        CLOSE(Value.kReverse);

        public final Value value;

        ClawState(Value value){
            this.value = value;
        }

    }

    private DoubleSolenoid claw;

    private TalonSRX clawArm;
    private boolean isClawArmRaised;

    private ColorSensor panelSensor;

    private static Claw instance;
    private ClawState clawState;

    private Claw() {
        clawArm = new TalonSRX(RobotConstants.Ports.CLAW_ARM);
        clawArm.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        isClawArmRaised = true;

        claw = new DoubleSolenoid(0, 1);

        panelSensor = new ColorSensor(I2C.Port.kOnboard);
    }

    public static Claw getIntance(){
        if(instance == null){
            instance = new Claw();
        }
        return instance;
    }

    @Override
    protected void initDefaultCommand() {
        
    }

    public void setClaw(ClawState clawValue){
            claw.set(clawValue.value);
            clawState = clawValue;
    }

    public void runArm(double power){
        clawArm.set(ControlMode.PercentOutput, power);
    }
    public double getArmDistance(){
        return clawArm.getSelectedSensorPosition();
    }

    public boolean isPanelPresent(){
        return panelSensor.detectPanel(panelSensor.red, panelSensor.green, panelSensor.blue);
    }

    public boolean isClawOpen(){
        if(clawState.value == Value.kForward){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean isClawArmRaised(){
        return isClawArmRaised;
    }

}