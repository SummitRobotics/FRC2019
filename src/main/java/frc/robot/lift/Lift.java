package frc.robot.lift;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.robotcore.RobotConstants;
import frc.robot.lift.liftcommands.*;

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
    private DigitalInput lowLimit;

    private static Lift instance;

    private Lift(){
        mastDriver = new CANSparkMax(RobotConstants.Ports.MAST_DRIVER, MotorType.kBrushless);
        //LiftConfig.configTalon(mastDriver);  

        mastEncoder = new CANEncoder(mastDriver);

        lowLimit = new DigitalInput(RobotConstants.Ports.LOW_LIMIT_SWITCH);
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
        mastDriver.set(power);
    }

    public boolean setMast(LiftState liftPos){
        /*double setpoint = RobotConstants.TALON_INCHES_TO_TICKS(liftPos.value);
        mastDriver.set(ControlMode.Position, setpoint);
        return mastDriver.getClosedLoopError() == 0;*/
        return false;    }

    public boolean getLowLimit(){
        return !lowLimit.get();
    }

    @Override
    protected void initDefaultCommand() {
        //setDefaultCommand(new MoveMastManual());
    }
}