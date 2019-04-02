package frc.robot.lift;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.lift.liftcommands.MoveMastManual;
import frc.robot.robotcore.RobotConstants;

public class Lift extends Subsystem{
    public enum LiftState{
        LOW(0),
        MID(21),
        HIGH(43);

        public final double value;
        LiftState(double value){
            this.value = value;
        }
    }

    private CANSparkMax mastDriver;
    private CANEncoder mastEncoder;
    private CANPIDController mastPID;

    private DigitalInput liftLowLimit;

    public Button liftLowLimitButton;

    private static Lift instance;

    private Lift(){
        mastDriver = new CANSparkMax(RobotConstants.Ports.MAST_DRIVER, MotorType.kBrushless);
        //LiftConfig.configTalon(mastDriver);  

        mastEncoder = new CANEncoder(mastDriver);
        mastPID = new CANPIDController(mastDriver);
        LiftConfig.configMotorController(mastPID);
        mastDriver.setInverted(true);



        liftLowLimit = new DigitalInput(RobotConstants.Ports.MAST_LIMIT_SWITCH);

        liftLowLimitButton = new Button(){

            @Override
            public boolean get(){
                return getLowLimit();
            }
        };
    }
    public static Lift getInstance(){
        if(instance == null){
            instance = new Lift();
        }
        return instance;
    }

    public double getEncoderPos(){
        return mastEncoder.getPosition();
    }

    public void setEncoderPos(int pos){
        mastEncoder.setPosition(pos);
    }

    public void runLiftManual(double power){
        if(power != 0){
            mastPID.setReference(power, ControlType.kDutyCycle);
            SmartDashboard.putNumber("Power to lift", power);
        }
    }

    public void setMast(LiftState liftPos){
        double setpoint = RobotConstants.NEO_INCHES_TO_TICKS(liftPos.value);
        mastPID.setReference(liftPos.value, ControlType.kPosition);
        SmartDashboard.putNumber("Setpoint for Lift", setpoint);
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new MoveMastManual());
    }

    public boolean getLowLimit() {
        return !liftLowLimit.get();
    }

    public void kill() {
        //mastDriver.set(0);
    }
}