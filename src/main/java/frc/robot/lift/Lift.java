package frc.robot.lift;

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
    private CANEncoder encoder;
    private DigitalInput lowLimit, highLimit;

    private static Lift instance;

    private Lift(){
        mastDriver = new CANSparkMax(RobotConstants.Ports.MAST_DRIVER, MotorType.kBrushless);
        encoder = new CANEncoder(mastDriver);
        //state implementation
        lowLimit = new DigitalInput(RobotConstants.Ports.LOW_LIMIT_SWITCH);
        highLimit = new DigitalInput(RobotConstants.Ports.HIGH_LIMIT_SWITCH);
    }
    public static Lift getInstance(){
        if(instance == null){
            instance = new Lift();
        }
        return instance;
    }

    public double getEncoderPos(){
        return encoder.getPosition();
    }

    public void runLiftManual(double power){
        if(!lowLimit.get() && !highLimit.get()){
            mastDriver.set(power);
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
        setDefaultCommand(new TrimMast());
    }
}