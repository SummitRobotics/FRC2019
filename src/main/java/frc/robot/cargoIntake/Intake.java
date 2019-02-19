package frc.robot.cargointake;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.cargointake.cargocommands.MoveCargoWrist;
import frc.robot.robotcore.RobotConstants;

public class Intake extends Subsystem {
    public enum IntakeState {
        UP(0 / 360),
        INTAKE_LOWER(-95 / 360),
        DOWN(-115 / 360);

        public final double value;
        IntakeState(double value) {
            this.value = value;
        }
    }

    public enum IntakeSpinState{
        ON(1),
        SLOW(0.5),
        OFF(0),
        REVERSE(-1);

        public final double value;
        IntakeSpinState(double value){
            this.value = value;
        }
    }

    public enum CargoPosition{
        STAGE_1(0),
        STAGE_2(1);

        public final int value;
        CargoPosition(int value){
            this.value = value;
        }
    }
    private TalonSRX arm;
    private VictorSPX rollers;
    private IntakeState intakeState;
    private IntakeSpinState intakeSpinState;

    private DigitalInput isUp;
    private DigitalInput break1, break2;

    private static Intake instance;
    
    /* ---- INITIALIZATION METHODS ----- */
    private Intake(){
        arm = new TalonSRX(RobotConstants.Ports.INTAKE_MOVEMENT);
        configArmPID();
        configMotionProfile();

        rollers = new VictorSPX(RobotConstants.Ports.INTAKE_ROLLER);

        isUp = new DigitalInput(RobotConstants.Ports.CARGO_LIMIT_SWITCH);
        break1 = new DigitalInput(RobotConstants.Ports.CARGO_BREAK_1);
        break2 = new DigitalInput(RobotConstants.Ports.CARGO_BREAK_2);
    }
    public static Intake getInstance(){
        if(instance == null){
            instance = new Intake();
        }
        return instance;
    }

    private void configArmPID(){
        arm.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        arm.setSensorPhase(RobotConstants.Cargo_PID.isPhaseInverted);
        arm.setInverted(RobotConstants.Cargo_PID.isInverted);

        arm.configNominalOutputForward(RobotConstants.Cargo_PID.ARM_NOMINAL_FORWARD);
        arm.configNominalOutputReverse(RobotConstants.Cargo_PID.ARM_NOMINAL_REVERSE);
        arm.configPeakOutputForward(RobotConstants.Cargo_PID.ARM_PEAK_FORWARD);
        arm.configPeakOutputReverse(RobotConstants.Cargo_PID.ARM_PEAK_REVERSE);

        arm.configPeakCurrentLimit((int)RobotConstants.Cargo_PID.ARM_PEAK_CURRENT);
        arm.configContinuousCurrentLimit((int)RobotConstants.Cargo_PID.ARM_CONST_CURRENT);

        arm.configAllowableClosedloopError(0, (int)RobotConstants.Cargo_PID.CLOSED_LOOP_ERROR);

        arm.config_kF(0, RobotConstants.Cargo_PID.ARM_F);
        arm.config_kP(0, RobotConstants.Cargo_PID.ARM_P);
        arm.config_kI(0, RobotConstants.Cargo_PID.ARM_I);
        arm.config_kD(0, RobotConstants.Cargo_PID.ARM_D);

        setArmEncoder(getAbsoluteResetPosition());
    }

    private void configMotionProfile(){
        
    }
    public int getAbsoluteResetPosition(){
        int absolutePosition = arm.getSensorCollection().getPulseWidthPosition();

        absolutePosition &= 0xFFF;
        if(arm.getInverted()){
            absolutePosition *= -1;
        }
        if(RobotConstants.Cargo_PID.isPhaseInverted){
            absolutePosition *= 1;
        }
        return absolutePosition;
    }
    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new MoveCargoWrist());
    }

    public boolean getCargoLimit(){
        return !isUp.get();
    }
    public boolean isBallDetected(){
        return break1.get();
    }
    public boolean isBallPresent(){
        return break2.get();
    }
    public IntakeState getIntakeState(){
        return intakeState;
    }
    public IntakeSpinState getIntakeSpinState(){
        return intakeSpinState;
    }
    public double getIntakeArmEncoder(){
        return arm.getSelectedSensorPosition();
    }
    public void setArmEncoder(int position){
        arm.setSelectedSensorPosition(position);
    }

    public void intake(int power){
            rollers.set(ControlMode.PercentOutput, power);
    }
    
    public void moveIntakeArm(double power){
        arm.set(ControlMode.PercentOutput, power);
    }

    public boolean setIntakeArm(IntakeState intakePosition){
            double target = intakePosition.value * RobotConstants.TALON_TICKS_PER_ROT;
            arm.set(ControlMode.Position, target); 
            return arm.getClosedLoopError() == 0;
    }
    public void setIntakeSpin(IntakeSpinState intakeSpinPosition){
        intake((int)intakeSpinPosition.value);
        intakeSpinState = intakeSpinPosition;
    }
    public IntakeSpinState toggleIntakeSpin(){
        IntakeSpinState intakeSpin = intakeSpinState;
        if(isSpin()){
            intakeSpin = IntakeSpinState.OFF;
            return intakeSpin;
        }
        if(!isSpin()){
            intakeSpin = IntakeSpinState.ON;
            return intakeSpin;
        }
        return intakeSpin;

    }
    public boolean isSpin(){
        if(intakeSpinState == IntakeSpinState.OFF){
            return false;
        }
        else if(intakeSpinState == IntakeSpinState.ON){
            return true;
        }
        else{
            return false;
        }
    }
}