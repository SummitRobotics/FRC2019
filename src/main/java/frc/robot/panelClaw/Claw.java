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

    private DoubleSolenoid claw;
    private boolean isClawOpen;

    private TalonSRX clawArm;
    private boolean isClawArmRaised;

    private ColorSensor panelSensor;

    private static Claw instance;

    public Claw() {
        clawArm = new TalonSRX(RobotConstants.Ports.CLAW_ARM);
        clawArm.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        isClawArmRaised = true;

        isClawOpen = true;
        claw = new DoubleSolenoid(RobotConstants.Ports.CLAW_SOLENOID_OPEN, RobotConstants.Ports.CLAW_SOLENOID_CLOSE);
        openClaw();

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

    public void openClaw() {
        if (!isClawOpen) {
            claw.set(Value.kForward);
            isClawOpen = true;
        }
    }
    public void closeClaw() {
        if (isClawOpen) {
            claw.set(Value.kReverse);
            isClawOpen = false;
        }
    }

    //TODO - Command?
    public void raiseClaw(){
        if(!isClawArmRaised){
            while(clawArm.getSelectedSensorPosition() < (2/Math.PI * RobotConstants.TALON_TICKS_PER_ROT)){
                clawArm.set(ControlMode.PercentOutput, 0.2);
            }
            isClawArmRaised = true;
        }
    }
        //TODO - command?
        public void lowerClaw(){
            if(isClawArmRaised){
                while(clawArm.getSelectedSensorPosition() < (2/Math.PI * RobotConstants.TALON_TICKS_PER_ROT)){
                    clawArm.set(ControlMode.PercentOutput, 0.2);
                }
                isClawArmRaised = false;
            }
        }

    public boolean isPanelPresent(){
        return panelSensor.detectPanel(panelSensor.red, panelSensor.green, panelSensor.blue);
    }

    public boolean isClawOpen(){
        return isClawOpen;
    }
    public boolean isClawArmRaised(){
        return isClawArmRaised;
    }

}