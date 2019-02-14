package frc.robot.cargoIntake;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotConstants;

public class Intake extends Subsystem {
    public enum IntakeState {
        UP(90),
        DOWN(-90);

        public final double value;
        IntakeState(double value){
            this.value = value;
        }
    }
    private TalonSRX arm, rollers;
    private IntakeState intakeState;

    private DigitalInput isUp;

    private static Intake instance;
    
    private Intake(){
        arm = new TalonSRX(RobotConstants.Ports.INTAKE_ARM);
        arm.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);

        rollers = new TalonSRX(RobotConstants.Ports.INTAKE_ROLLER);

        isUp = new DigitalInput(RobotConstants.Ports.CARGO_LIMIT_SWITCH);
    }

    public static Intake getInstance(){
        if(instance == null){
            instance = new Intake();
        }
        return instance;
    }
    @Override
    protected void initDefaultCommand() {
        
    }

    public boolean getCargoLimit(){
        return !isUp.get();
    }
    public IntakeState getIntakeState(){
        return intakeState;
    }
    public double getIntakeArmPosition(){
        return arm.getSelectedSensorPosition();
    }

    public void intake(int direction){
        rollers.set(ControlMode.PercentOutput, 1 * direction);
    }
    public void moveIntakeArm(double power){
        arm.set(ControlMode.PercentOutput, power);
    }

    public void setIntakeArm(IntakeState intakePosition, double power){
        if(intakePosition != intakeState){
            if(intakePosition == IntakeState.UP){
                arm.set(ControlMode.PercentOutput, power);
            }
            else if(intakePosition == IntakeState.DOWN){
                arm.set(ControlMode.PercentOutput, -power);
            }
        }
    }
}