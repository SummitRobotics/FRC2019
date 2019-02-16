package frc.robot.panelclaw;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.robotcore.RobotConstants;
import frc.robot.devices.ColorSensor;
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

    public enum ClawArmState{
        //values in degrees
        UP(0),
        DOWN(90);

        public final double value;
        ClawArmState(double value){
            this.value = value;
        }
    }

    private DoubleSolenoid claw;

    private TalonSRX clawArm;
    private ClawArmState clawArmState;

    private ColorSensor panelSensor;
    private DigitalInput limit;

    private static Claw instance;
    private ClawState clawState;

    private Claw() {
        clawArm = new TalonSRX(RobotConstants.Ports.CLAW_ARM);
        clawArm.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);

        claw = new DoubleSolenoid(0, 1);

        panelSensor = new ColorSensor(I2C.Port.kOnboard);
        limit = new DigitalInput(RobotConstants.Ports.CLAW_LIMIT_SWITCH);
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

    public void setClaw(ClawState clawPosition){
        claw.set(clawPosition.value);
        clawState = clawPosition;
    }

    public void toggleClaw() {
        
        if (clawState == ClawState.OPEN) {

            clawState = ClawState.CLOSE;
        } else {
            
            clawState = ClawState.OPEN;
        }

        claw.set(clawState.value);
    }

    public void runArm(double power){

        clawArm.set(ControlMode.PercentOutput, power);
    }
    public double getArmEncoder(){
        return clawArm.getSelectedSensorPosition();
    }
    public double getClawArmState(){
        return clawArmState.value;
    }

    public void resetArmEncoder(){
        clawArm.setSelectedSensorPosition(0);
    }

    public boolean isPanelPresent(){
        return panelSensor.isActive();
    }
    public boolean isAtLimit(){
        return !limit.get();
    }

    public boolean isClawOpen(){
        if(clawState.value == Value.kForward){
            return true;
        }
        else{
            return false;
        }
    }

}