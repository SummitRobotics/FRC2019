package frc.robot.lift;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

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

    private TalonSRX mastDriver;
    private DigitalInput lowLimit;

    private static Lift instance;

    private Lift(){
        mastDriver = new TalonSRX(RobotConstants.Ports.MAST_DRIVER);
        LiftConfig.configTalon(mastDriver);  

        lowLimit = new DigitalInput(RobotConstants.Ports.LOW_LIMIT_SWITCH);
    }
    public static Lift getInstance(){
        if(instance == null){
            instance = new Lift();
        }
        return instance;
    }

    public double getEncoderPos(){
        return mastDriver.getSelectedSensorPosition();
    }

    public void setEncoderPos(int pos){
        mastDriver.setSelectedSensorPosition(pos);
    }

    public void runLiftManual(double power){
        mastDriver.set(ControlMode.PercentOutput, power);
    }

    public boolean setMast(LiftState liftPos){
        double setpoint = RobotConstants.TALON_INCHES_TO_TICKS(liftPos.value);
        mastDriver.set(ControlMode.Position, setpoint);
        return mastDriver.getClosedLoopError() == 0;
    }

    public boolean getLowLimit(){
        return !lowLimit.get();
    }

    @Override
    protected void initDefaultCommand() {
        //setDefaultCommand(new MoveMastManual());
    }

    public void kill() {
        mastDriver.set(ControlMode.PercentOutput, 0);
    }
}