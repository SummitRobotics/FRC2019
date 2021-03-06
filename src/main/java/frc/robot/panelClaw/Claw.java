package frc.robot.panelclaw;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.buttons.Button;
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
        //TODO - Change cargo down to 20
        DOWN(30);

        public final double value;
        ClawArmState(double value){
            this.value = value;
        }
    }

    public enum ClawSpeed{
        FORWARD(.1),
        REVERSE(-.1);

        public final double value;
        ClawSpeed(double value){
            this.value = value;
        }
    }

    public DoubleSolenoid claw;

    private TalonSRX clawArm;
    private VictorSPX clawArmFollow;

    public ColorSensor panelSensor;
    private DigitalInput isClawUp;

    private static Claw instance;
    private ClawState clawState;
    private ClawArmState clawArmState;

    public Button isClawUpButton;

    private Claw() {
        clawArm = new TalonSRX(RobotConstants.Ports.CLAW_MOVEMENT);
        ClawArmConfig.configTalon(clawArm);
        clawArmState = ClawArmState.UP;

        clawArmFollow = new VictorSPX(RobotConstants.Ports.CLAW_MOVEMENT_FOLLOW);
        clawArmFollow.follow(clawArm);
        clawArmFollow.setInverted(true);

        claw = new DoubleSolenoid(RobotConstants.Ports.PCM_1, RobotConstants.Ports.CLAW_SOLENOID_OPEN, RobotConstants.Ports.CLAW_SOLENOID_CLOSE);

        panelSensor = new ColorSensor(I2C.Port.kOnboard);
        isClawUp = new DigitalInput(RobotConstants.Ports.CLAW_LIMIT_SWITCH);

        isClawUpButton = new Button(){

            @Override
            public boolean get(){
                return !isClawUp.get();
            }
        };
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
                return clawPos;
            }
            if(!isClawOpen()){
                clawPos = ClawState.OPEN;
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
        return !isClawUp.get();
    }
    public boolean isPanelPresent(){
        return panelSensor.isActive();
    }
    public void runArm(double power){
        if (getClawLimit()){
            Math.min(power,0);
        }
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
        //TODO - test threshold
        final double THRESHOLD = 75;
        double target = (angle/360) * RobotConstants.TALON_TICKS_PER_ROT;
        clawArm.set(ControlMode.Position, target);
        SmartDashboard.putNumber("Closed Loop Error", clawArm.getClosedLoopError());
        SmartDashboard.putNumber("Arm Encoder Val", clawArm.getSelectedSensorPosition());
        return (clawArm.getClosedLoopError() > -THRESHOLD) && (clawArm.getClosedLoopError() < THRESHOLD);
    }

    public void setArmEncoder(int position){
        clawArm.setSelectedSensorPosition(position);
    }

    public void kill(){
        clawArm.set(ControlMode.PercentOutput, 0);
    }
}