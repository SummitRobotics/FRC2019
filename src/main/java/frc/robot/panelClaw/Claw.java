package frc.robot.panelclaw;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.robotcore.IterativeSubsystem;
import frc.robot.robotcore.RobotConstants;
import frc.robot.devices.ColorSensor;
import edu.wpi.first.wpilibj.I2C;
import frc.robot.panelclaw.clawcommands.MoveClawWrist;

public class Claw extends Subsystem implements IterativeSubsystem{

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
        UP(0 / 360),
        DOWN(90 / 360);

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
        configArmPID();

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
    

    private void configArmPID(){
        clawArm.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);

        clawArm.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        clawArm.setSensorPhase(RobotConstants.Claw_PID.isPhaseInverted);
        clawArm.setInverted(RobotConstants.Claw_PID.isInverted);

        clawArm.configNominalOutputForward(RobotConstants.Claw_PID.ARM_NOMINAL_FORWARD);
        clawArm.configNominalOutputReverse(RobotConstants.Claw_PID.ARM_NOMINAL_REVERSE);
        clawArm.configPeakOutputForward(RobotConstants.Claw_PID.ARM_PEAK_FORWARD);
        clawArm.configPeakOutputReverse(RobotConstants.Claw_PID.ARM_PEAK_REVERSE);

        clawArm.configPeakCurrentLimit((int)RobotConstants.Claw_PID.ARM_PEAK_CURRENT);
        clawArm.configContinuousCurrentLimit((int)RobotConstants.Claw_PID.ARM_CONST_CURRENT);

        clawArm.configAllowableClosedloopError(0, (int)RobotConstants.Claw_PID.CLOSED_LOOP_ERROR);

        clawArm.config_kF(0, RobotConstants.Claw_PID.ARM_F);
        clawArm.config_kP(0, RobotConstants.Claw_PID.ARM_P);
        clawArm.config_kI(0, RobotConstants.Claw_PID.ARM_I);
        clawArm.config_kD(0, RobotConstants.Claw_PID.ARM_D);
    }

    public int getAbsoluteResetPosition(){
        int absolutePosition = clawArm.getSensorCollection().getPulseWidthPosition();

        absolutePosition &= 0xFFF;
        if(RobotConstants.Claw_PID.isInverted){
            absolutePosition *= -1;
        }
        if(RobotConstants.Claw_PID.isPhaseInverted){
            absolutePosition *= 1;
        }
        return absolutePosition;
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
    public boolean setArm(ClawArmState clawArmPos){
        double target = clawArmPos.value * RobotConstants.TALON_TICKS_PER_ROT;
        clawArm.set(ControlMode.Position, target);
        return clawArm.getClosedLoopError() == 0;
    }
    public void resetArmEncoder(){
        clawArm.setSelectedSensorPosition(0);
    }

    /* ----- RUN METHODS ----- */
    @Override
    public void run(){
        checkForLimit();
    }
    public void checkForLimit(){
        if(getClawLimit()){
            resetArmEncoder();
            
        }
    }

}