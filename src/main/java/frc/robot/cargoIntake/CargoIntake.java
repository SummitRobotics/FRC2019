package frc.robot.cargointake;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.cargointake.cargocommands.TrimCargoArm;
import frc.robot.robotcore.RobotConstants;

public class CargoIntake extends Subsystem {

    //List of states intake arm can "servo" to. Values given in angle / 360
    public enum IntakeArmState {
        UP(0),
        INTAKE_LOWER(-108),
        DOWN(-120);

        public final double value;
        IntakeArmState(double value) {
            this.value = value;
        }
    }

    //List of states for rollers. Values given in power [-1, 1]
    public enum RollerState{
        ON(0.55),
        SLOW(0.25),
        OFF(0),
        REVERSE(-0.55);

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
    private VictorSPX armFollow;
    private VictorSPX rollers;

    private IntakeArmState intakeArmState;
    private RollerState rollerState;

    private DigitalInput isUp;
    private DigitalInput break1, break2;

    public Button isUpButton;

    private DoubleSolenoid gasStrutRelease;
    private DoubleSolenoid pistonRelease;
    
    /* ---- INITIALIZATION METHODS ----- */

    private static CargoIntake instance;

    private CargoIntake(){

        arm = new TalonSRX(RobotConstants.Ports.INTAKE_MOVEMENT);
        CargoArmConfig.configTalon(arm);
        arm.setSelectedSensorPosition(0);
        armFollow = new VictorSPX(RobotConstants.Ports.INTAKE_MOVEMENT_FOLLOW);
        armFollow.follow(arm);
        armFollow.setInverted(true);

        rollers = new VictorSPX(RobotConstants.Ports.INTAKE_ROLLER);
        rollers.setInverted(true);

        isUp = new DigitalInput(RobotConstants.Ports.CARGO_LIMIT_SWITCH);
        break1 = new DigitalInput(RobotConstants.Ports.CARGO_BREAK_1);
        break2 = new DigitalInput(RobotConstants.Ports.CARGO_BREAK_2);

        gasStrutRelease = new DoubleSolenoid(RobotConstants.Ports.PCM_2, RobotConstants.Ports.GASSTRUT_RELEASE_OPEN, RobotConstants.Ports.GASSTRUT_RELEASE_CLOSE);
        pistonRelease = new DoubleSolenoid(RobotConstants.Ports.PCM_2, RobotConstants.Ports.PNEUMATIC_RELEASE_OPEN, RobotConstants.Ports.PNEUMATIC_RELEASE_CLOSE);

        isUpButton = new Button(){

            @Override
            public boolean get(){
                return getCargoLimit();
            }
        };
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

    public void intake(double power){
        rollers.set(ControlMode.PercentOutput, power);
    }
    public void setRollers(RollerState intakeSpinPosition){
        intake(intakeSpinPosition.value);
        SmartDashboard.putNumber("current roller power", intakeSpinPosition.value);
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
        if (getCargoLimit()) {
            Math.min(power,0);
        }
        arm.set(ControlMode.PercentOutput, power);
    }

    //Servos the intake arm to a given position
    public boolean setIntakeArm(double intakePosition){
        double target = (intakePosition/360) * RobotConstants.TALON_TICKS_PER_ROT;
        double THRESHOLD = 75;
        //adjust for sprocket ratio
        target *= 3;            
        arm.set(ControlMode.Position, target); 
        SmartDashboard.putNumber("Closed Loop Error for Arm", arm.getClosedLoopError());
        return (arm.getClosedLoopError() < THRESHOLD) && (arm.getClosedLoopError() > -THRESHOLD);
    }

    /* ----- CLIMB ----- */
    public void climbLevel2(){
        pistonRelease.set(DoubleSolenoid.Value.kReverse);
    }

    public void kill() {
        arm.set(ControlMode.PercentOutput, 0);
        rollers.set(ControlMode.PercentOutput, 0);
    }
}