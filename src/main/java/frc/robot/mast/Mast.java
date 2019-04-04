package frc.robot.mast;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.mast.mastcommands.MoveMastManual;
import frc.robot.robotcore.RobotConstants;

public class Mast extends Subsystem{
    public enum MastState{
        LOW(0),
        MID(20.2),
        HIGH(38);

        public final double value;
        MastState(double value){
            this.value = value;
        }
    }

    private final double THRESHOLD = 1;

    private CANSparkMax mastDriver;
    private CANEncoder mastEncoder;
    private CANPIDController mastPID;

    private DigitalInput mastLowLimit;

    public Button mastLowLimitButton;


    private static Mast instance;

    private Mast(){
        mastDriver = new CANSparkMax(RobotConstants.Ports.MAST_DRIVER, MotorType.kBrushless);
        //LiftConfig.configTalon(mastDriver);  

        mastEncoder = new CANEncoder(mastDriver);
        mastPID = new CANPIDController(mastDriver);
        MastConfig.configMotorController(mastPID);
        mastDriver.setInverted(true);



        mastLowLimit = new DigitalInput(RobotConstants.Ports.MAST_LIMIT_SWITCH);
        mastLowLimitButton = new Button(){

            @Override
            public boolean get(){
                return getMastLowLimit();
            }
        };
    }
    public static Mast getInstance(){
        if(instance == null){
            instance = new Mast();
        }
        return instance;
    }

    @Override
    protected void initDefaultCommand() {
        //setDefaultCommand(new MoveMastManual());
    }

    public double getEncoderPos(){
        return mastEncoder.getPosition();
    }

    public void setEncoderPos(int pos){
        mastEncoder.setPosition(pos);
    }

    public void runMastManual(double power){
        if(power != 0){
            mastPID.setReference(power, ControlType.kDutyCycle);
        }
    }

    public void setMast(MastState mastPos){
        double setpoint = mastPos.value;
        mastPID.setReference(setpoint, ControlType.kPosition);
    }

    public boolean getMastLowLimit() {
        return !mastLowLimit.get();
    }

    public boolean withinThreshold(double target) {
        double error = target - getEncoderPos();
        return (error > -THRESHOLD) && (error < THRESHOLD);
    }

    public void kill() {
        mastDriver.set(0);
    }
}