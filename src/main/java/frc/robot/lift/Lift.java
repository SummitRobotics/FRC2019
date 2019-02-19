package frc.robot.lift;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
//import com.ctre.phoenix.motorcontrol.can.VictorSPX;

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
    //private VictorSPX mastFollower;
    private DigitalInput lowLimit, highLimit;

    private static Lift instance;

    private Lift(){
        mastDriver = new TalonSRX(RobotConstants.Ports.MAST_DRIVER);
        //mastFollower = new VictorSPX(RobotConstants.Ports.MAST_FOLLOWER);
        //mastFollower.follow(mastDriver);
        mastDriver.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        //state implementation
        lowLimit = new DigitalInput(RobotConstants.Ports.LOW_LIMIT_SWITCH);
        //highLimit = new DigitalInput(RobotConstants.Ports.HIGH_LIMIT_SWITCH);
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

    public void runLiftManual(double power){
        if(!lowLimit.get()){
            mastDriver.set(ControlMode.PercentOutput, power);
        }
    }

    public boolean getLowLimit(){
        return !lowLimit.get();
    }
    public boolean getHighLimit(){
        return !highLimit.get();
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new MoveMastManual());
    }
}