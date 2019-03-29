package frc.robot.lift;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.robotcore.RobotConstants;

public class Lift extends Subsystem{
    public enum LiftState{
        LOW(0),
        MID(28),
        HIGH(56);

        public final int value;
        LiftState(int value){
            this.value = value;
        }
    }

    private CANSparkMax mastDriver;
    private CANEncoder mastEncoder;

    private DigitalInput liftLowLimit;

    public Button liftLowLimitButton;

    private static Lift instance;

    private Lift(){
        mastDriver = new CANSparkMax(RobotConstants.Ports.MAST_DRIVER, MotorType.kBrushless);
        //LiftConfig.configTalon(mastDriver);  

        mastEncoder = new CANEncoder(mastDriver);

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
        if (getLowLimit()){
            Math.min(power,0);
        }
        mastDriver.set(power);
    }

    public boolean setMast(LiftState liftPos){
        /*double setpoint = RobotConstants.TALON_INCHES_TO_TICKS(liftPos.value);
        mastDriver.set(ControlMode.Position, setpoint);
        return mastDriver.getClosedLoopError() == 0;*/
        return false;    }

    @Override
    protected void initDefaultCommand() {
        //setDefaultCommand(new MoveMastManual());
    }

    public boolean getLowLimit() {
        return !liftLowLimit.get();
    }

    public void kill() {
        mastDriver.set(0);
    }
}