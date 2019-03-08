package frc.robot.panelclaw;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.robotcore.RobotConstants;
import frc.robot.devices.ColorSensor;
import edu.wpi.first.wpilibj.I2C;
import frc.robot.panelclaw.clawcommands.MoveClawWrist;

public class Claw extends Subsystem{

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
        CARGO_DOWN(10),
        DOWN(50);

        public final double value;
        ClawArmState(double value){
            this.value = value;
        }
    }

    public DoubleSolenoid claw;

    private TalonSRX clawArm;

    public ColorSensor panelSensor;
    private DigitalInput limit;

    private static Claw instance;
    private ClawState clawState;
    private ClawArmState clawArmState;

    private Claw() {
        clawArm = new TalonSRX(RobotConstants.Ports.CLAW_MOVEMENT);
        ClawArmConfig.configTalon(clawArm);
        clawArmState = ClawArmState.UP;

        claw = new DoubleSolenoid(RobotConstants.Ports.CLAW_SOLENOID_OPEN, RobotConstants.Ports.CLAW_SOLENOID_CLOSE);

        panelSensor = new ColorSensor(I2C.Port.kOnboard);
        limit = new DigitalInput(RobotConstants.Ports.CLAW_LIMIT_SWITCH);

    }
    public static Claw getInstance(){
        if(instance == null){
            instance = new Claw();
        }
        return instance;
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new MoveClawWrist());
    }


    /* ----- METHODS FOR CLAW ----- */
    public void setClaw(ClawState clawPosition){
            claw.set(clawPosition.value);
            clawState = clawPosition;
    }
    public boolean isClawOpen(){
        if(clawState == ClawState.CLOSE){
            return false;
        }
        if(clawState == ClawState.OPEN){
            return true;
        }
        else{
            return false;
        }
    }
    public ClawState toggleClaw(){
        ClawState clawPos = clawState;
            if(isClawOpen()){
                clawPos = ClawState.CLOSE;
                SmartDashboard.putString("Claw", clawPos.toString());
                return clawPos;
            }
            if(!isClawOpen()){
                clawPos = ClawState.OPEN;
                SmartDashboard.putString("Claw", clawPos.toString());
                return clawPos;
            }
        return clawPos;
    }
    public ClawState getClawState(){
        return clawState;
    }

    
    /* ----- METHODS FOR WRIST ----- */
    public double getArmEncoder(){
        return clawArm.getSelectedSensorPosition();
    }
    public ClawArmState getClawArmState(){
        return clawArmState;
    }
    public boolean getClawLimit(){
        return !limit.get();
    }
    public boolean isPanelPresent(){
        return panelSensor.isActive();
    }
    public void runArm(double power){
        clawArm.set(ControlMode.PercentOutput, power);
    }
    /*public boolean setArm(ClawArmState clawArmPos){
        double target = clawArmPos.value * RobotConstants.TALON_TICKS_PER_ROT;
        SmartDashboard.putNumber("Target", target);
        clawArm.set(ControlMode.Position, target);
        clawArmState = clawArmPos;
        return clawArm.getClosedLoopError() == 0;
    }*/

    public boolean setArm(double angle){
        double target = (angle/360) * 4096;
        SmartDashboard.putNumber("Target", target);
        clawArm.set(ControlMode.Position, target);
        return clawArm.getClosedLoopError() == 0;
    }

    public void setArmEncoder(int position){
        clawArm.setSelectedSensorPosition(position);
    }
}