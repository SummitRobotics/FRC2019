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
        UP(0),
        INTAKE_LOWER(-95),
        DOWN(-115);

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
    
    private Intake(){
        arm = new TalonSRX(RobotConstants.Ports.INTAKE_MOVEMENT);
        arm.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);

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
    public double getIntakeArmEncoder(){
        return arm.getSelectedSensorPosition();
    }
    public void resetArmEncoder(){
        arm.setSelectedSensorPosition(0);
    }

    public void intake(int power){
        while(true){
            rollers.set(ControlMode.PercentOutput, power);
        }
    }
    public void moveIntakeArm(double power){
        arm.set(ControlMode.PercentOutput, power);
    }

    public void setIntakeArm(IntakeState intakePosition, double power){
        if(intakePosition != intakeState){
            arm.set(ControlMode.PercentOutput, power);
        }
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