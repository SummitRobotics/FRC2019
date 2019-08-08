package frc.robot.drivetrain;

import com.ctre.phoenix.sensors.PigeonIMU;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.devices.BlinkinRGB;
import frc.robot.drivetrain.drivetraincommands.ArcadeDrive;
import frc.robot.robotcore.RobotConstants;

public class Drivetrain extends Subsystem{

    //Enum which indicatese whether we're in high gear or low. Mainly for readability. 
    public enum GearState{
        LOW(Value.kForward),
        HIGH(Value.kReverse);

        public final Value value;
        GearState(Value value){
            this.value = value;
        }
    }

/* ----- Instance Members ----- */

    public CANSparkMax leftDrive0, leftDrive1, leftDrive2, rightDrive0, rightDrive1, rightDrive2;
    private CANPIDController leftPID, rightPID;
    private SpeedControllerGroup leftDrive, rightDrive;
    public DifferentialDrive robotDrive;
    private CANEncoder leftEncoder, rightEncoder;

    private DoubleSolenoid gearShifter;
    private GearState gearState;

    /*private DoubleSolenoid PTOshifter;
    private PTOState ptoState;*/

    private PigeonIMU gyro;

    private Compressor compressor;

    private double[] ypr;

    private final double THRESHOLD = 2;

/* ----- INSTANTIATION METHODS ----- */

    private static Drivetrain instance;
    
    private Drivetrain(){
        leftDrive0 = new CANSparkMax(RobotConstants.Ports.LEFT_DRIVE_0, MotorType.kBrushless);
        leftDrive0.setInverted(true);
        leftDrive1 = new CANSparkMax(RobotConstants.Ports.LEFT_DRIVE_MAIN, MotorType.kBrushless);
        //leftDrive1.setInverted(true);
        leftDrive1.follow(leftDrive0);
        leftDrive2 = new CANSparkMax(RobotConstants.Ports.LEFT_DRIVE_1, MotorType.kBrushless);
        //leftDrive2.setInverted(true);
        leftDrive2.follow(leftDrive0);
        setCurrentLimits(leftDrive0, leftDrive1, leftDrive2, 30, 40);
        setOpenRampRates(leftDrive0, leftDrive1, leftDrive2, 0.0);

        leftEncoder = new CANEncoder(leftDrive0);
        leftPID = new CANPIDController(leftDrive0);
        DrivetrainConfig.configMotorController(leftPID);

        rightDrive0 = new CANSparkMax(RobotConstants.Ports.RIGHT_DRIVE_0, MotorType.kBrushless);
       //rightDrive0.setInverted(true);
        rightDrive1 = new CANSparkMax(RobotConstants.Ports.RIGHT_DRIVE_MAIN, MotorType.kBrushless);

        //rightDrive1.setInverted(true);
        rightDrive1.follow(rightDrive0);
        rightDrive2 = new CANSparkMax(RobotConstants.Ports.RIGHT_DRIVE_1, MotorType.kBrushless);
        //rightDrive2.setInverted(true);
        rightDrive2.follow(rightDrive0);
        setCurrentLimits(rightDrive0, rightDrive1, rightDrive2, 30, 40);
        setOpenRampRates(rightDrive0, rightDrive1, rightDrive2, 0.0);

        rightEncoder = new CANEncoder(rightDrive0);
        rightPID = new CANPIDController(rightDrive0);
        DrivetrainConfig.configMotorController(rightPID);

        leftDrive = new SpeedControllerGroup(leftDrive0, leftDrive1, leftDrive2);
        rightDrive = new SpeedControllerGroup(rightDrive0, rightDrive1, rightDrive2);
        robotDrive = new DifferentialDrive(leftDrive, rightDrive);
        robotDrive.setSafetyEnabled(false);
        robotDrive.setRightSideInverted(false);

        gyro = new PigeonIMU(RobotConstants.Ports.GYRO);

        gearShifter = new DoubleSolenoid(RobotConstants.Ports.PCM_1, RobotConstants.Ports.DRIVE_SOLENOID_OPEN, RobotConstants.Ports.DRIVE_SOLENOID_CLOSE);

        compressor = new Compressor(RobotConstants.Ports.PCM_1);
        compressor.setClosedLoopControl(true);

    }

    public static Drivetrain getInstance(){
        if(instance == null){
            instance = new Drivetrain();
        }
        return instance;
    }

    @Override
    protected void initDefaultCommand() {
       setDefaultCommand(new ArcadeDrive());
    }

    private void setOpenRampRates(CANSparkMax controller1, CANSparkMax controller2, CANSparkMax controller3, double rampRate){
        controller1.setOpenLoopRampRate(rampRate);
        controller2.setOpenLoopRampRate(rampRate);
        controller3.setOpenLoopRampRate(rampRate);
    }
   
    private void setCurrentLimits(CANSparkMax controller1, CANSparkMax controller2, CANSparkMax controller3, int stallCurrentLimit, int freeCurrentLimit, int stallRPM ){
        controller1.setSmartCurrentLimit(stallCurrentLimit, freeCurrentLimit, stallRPM);
        controller2.setSmartCurrentLimit(stallCurrentLimit, freeCurrentLimit, stallRPM);
        controller3.setSmartCurrentLimit(stallCurrentLimit, freeCurrentLimit, stallRPM);
    }

    private void setCurrentLimits(CANSparkMax controller1, CANSparkMax controller2, CANSparkMax controller3, int stallCurrentLimit, int freeCurrentLimit){
        controller1.setSmartCurrentLimit(stallCurrentLimit, freeCurrentLimit);
        controller2.setSmartCurrentLimit(stallCurrentLimit, freeCurrentLimit);
        controller3.setSmartCurrentLimit(stallCurrentLimit, freeCurrentLimit);
    }

/* ----- FEEDBACK DEVICES ----- */

    public double getYaw(){
        ypr = new double[3];
        gyro.getYawPitchRoll(ypr);
        return -ypr[0];
    }
    public double getPitch(){
        ypr = new double[2];
        gyro.getYawPitchRoll(ypr);
        return ypr[1];
    }
    /*public double getRoll(){
        double[] ypr = new double[2];
        gyro.getYawPitchRoll(ypr);
        return ypr[2];
    }*/

    //Feedback Config
    public void setDrivetrainEncoders(double value){
        leftEncoder.setPosition(value);
        rightEncoder.setPosition(value);
    }

    public double getLeftEncoder(){
        return leftEncoder.getPosition();
    }
    public double getRightEncoder(){
        return rightEncoder.getPosition();
    }

    public void resetGyro(){
        gyro.setYaw(0);
        gyro.setAccumZAngle(0);
    }

/* ----- DRIVETRAIN METHODS ----- */

    /*public void leftClosedLoop(double input){
        //In RPM
        double setpoint = input * RobotConstants.MAX_DRIVETRAIN_RPM;
        leftPID.setReference(setpoint, ControlType.kSmartVelocity);
    }
    public void rightClosedLoop(double input){
        //In RPM
        double setpoint = input * RobotConstants.MAX_DRIVETRAIN_RPM;
        rightPID.setReference(setpoint, ControlType.kSmartVelocity);
    }*/
    
    public void toPosition(double leftSetpoint, double rightSetpoint){
        //SETPOINTS MUST BE IN TICKS+
        leftPID.setReference(-leftSetpoint, ControlType.kPosition);
        rightPID.setReference(-rightSetpoint, ControlType.kPosition);
    }

    public boolean isInThreshold(double target){
        double leftError = target - getLeftEncoder();
        double rightError = target - getRightEncoder();

        
        boolean isLeftDone = (leftError > -THRESHOLD) && (leftError < THRESHOLD);
        boolean isRightDone = (rightError > -THRESHOLD) && (rightError < THRESHOLD);

        return isLeftDone && isRightDone;
    }

/* ----- GEAR SHIFTING ----- */

    public void shiftGear(GearState gearValue){
        BlinkinRGB.getInstance().shiftSetLEDState(gearValue);
        gearState = gearValue;
        gearShifter.set(gearValue.value);
    }

    public GearState toggleGear(){
        GearState gearPos = gearState;
            if(gearState == GearState.HIGH){
                gearPos = GearState.LOW;
                return gearPos;
            }
            if(gearState == GearState.LOW){
                gearPos = GearState.HIGH;
                return gearPos;
            }
            return gearPos;
    }

    /* ----- INTERRUPT ------ */
    public void kill() {
        robotDrive.tankDrive(0, 0);
    }

    public void setRampRate(double rate){
        setOpenRampRates(leftDrive0, leftDrive1, leftDrive2, rate);
        setOpenRampRates(rightDrive0, rightDrive1, rightDrive2, rate);
    }
}