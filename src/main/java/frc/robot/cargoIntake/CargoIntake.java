package frc.robot.cargointake;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.cargointake.cargocommands.TrimCargoArm;
import frc.robot.robotcore.RobotConstants;

public class CargoIntake extends Subsystem {

    //List of states intake arm can "servo" to. Values given in angle / 360
    public enum IntakeArmState {
        UP(0 / 360),
        INTAKE_LOWER(-95 / 360),
        DOWN(-115 / 360);

        public final double value;
        IntakeArmState(double value) {
            this.value = value;
        }
    }

    //List of states for rollers. Values given in power [-1, 1]
    public enum RollerState{
        ON(1),
        SLOW(0.5),
        OFF(0),
        REVERSE(-1);

        public final double value;
        RollerState(double value){
            this.value = value;
        }
    }

    //List of positions the cargo can be in, according to the IR break sensors.
    //Enum is superficial, just for improved readability.
    public enum CargoPosition{
        DETECTED(0),
        CONSUMED(1);

        public final int value;
        CargoPosition(int value){
            this.value = value;
        }
    }


    //Instance Members
    private TalonSRX arm;
    private VictorSPX rollers;

    private IntakeArmState intakeArmState;
    private RollerState rollerState;

    private DigitalInput isUp;
    private DigitalInput break1, break2;
    
    /* ---- INITIALIZATION METHODS ----- */

    private static CargoIntake instance;

    private CargoIntake(){
        arm = new TalonSRX(RobotConstants.Ports.INTAKE_MOVEMENT);
        CargoArmConfig.configTalon(arm);

        rollers = new VictorSPX(RobotConstants.Ports.INTAKE_ROLLER);

        isUp = new DigitalInput(RobotConstants.Ports.CARGO_LIMIT_SWITCH);
        break1 = new DigitalInput(RobotConstants.Ports.CARGO_BREAK_1);
        break2 = new DigitalInput(RobotConstants.Ports.CARGO_BREAK_2);
    }
    public static CargoIntake getInstance(){
        if(instance == null){
            instance = new CargoIntake();
        }
        return instance;
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new TrimCargoArm());
    }


    /* ----- GETTER METHODS ----- */

    //Returns if the cargo arm is in the upward position or not
    public boolean getCargoLimit(){
        return !isUp.get();
    }
    //Returns if the first break sensor is tripped (if a ball is ready to be intook)
    public boolean isBallDetected(){
        return !break1.get();
    }
    //Returns if a ball has been sucsessfully intook.
    public boolean isBallConsumed(){
        return !break2.get();
    }

    public IntakeArmState getIntakeArmState(){
        return intakeArmState;
    }
    public RollerState getRollerState(){
        return rollerState;
    }
    public double getIntakeArmEncoder(){
        return arm.getSelectedSensorPosition();
    }


    /* ----- ROLLERS ----- */

    public void intake(int power){
        rollers.set(ControlMode.PercentOutput, power);
    }
    public void setRollers(RollerState intakeSpinPosition){
        intake((int)intakeSpinPosition.value);
        rollerState = intakeSpinPosition;
    }

    //Toggle Logic
    public RollerState toggleRollers(){
        RollerState intakeSpin = rollerState;
        if(isSpinning()){
            intakeSpin = RollerState.OFF;
            return intakeSpin;
        }
        if(!isSpinning()){
            intakeSpin = RollerState.ON;
            return intakeSpin;
        }
        return intakeSpin;
    }
    public boolean isSpinning(){
        if(rollerState == RollerState.OFF){
            return false;
        }
        else if(rollerState == RollerState.ON){
            return true;
        }
        else{
            return false;
        }
    }


    /* ----- INTAKE ARM ----- */
    public void setArmEncoder(int position){
        arm.setSelectedSensorPosition(position);
    }

    //Open Loop Intake Arm control (do this better in the future)
    public void moveIntakeArm(double power){
        arm.set(ControlMode.PercentOutput, power);
    }

    //Servos the intake arm to a given position
    public boolean setIntakeArm(IntakeArmState intakePosition){
            double target = intakePosition.value * RobotConstants.TALON_TICKS_PER_ROT;
            arm.set(ControlMode.Position, target); 
            return arm.getClosedLoopError() == 0;
    }

}