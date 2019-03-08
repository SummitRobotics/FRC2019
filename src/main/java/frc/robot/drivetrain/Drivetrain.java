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
import frc.robot.drivetrain.drivetraincommands.ArcadeDrive;
import frc.robot.robotcore.RobotConstants;

public class Drivetrain extends Subsystem{

    //Enum which indicatese wether we're in high gear or low. Mainly for readability. 
    public enum GearState{
        LOW(Value.kForward),
        HIGH(Value.kReverse);

        public final Value value;
        GearState(Value value){
            this.value = value;
        }
    }

    //Indicates wether PTO is engaged or not. Mainly for readability. 
    /*public enum PTOState{
        ENGAGED(Value.kForward),
        DISENGAGED(Value.kReverse);

        public final Value value;
        PTOState(Value value){
            this.value = value;
        }
    }*/


/* ----- Instance Members ----- */

    private CANSparkMax leftDrive0, leftDrive1, leftDrive2, rightDrive0, rightDrive1, rightDrive2;
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

/* ----- INSTANTIATION METHODS ----- */

    private static Drivetrain instance;
    
    private Drivetrain(){
        leftDrive0 = new CANSparkMax(RobotConstants.Ports.LEFT_DRIVE_0, MotorType.kBrushless);
        leftDrive1 = new CANSparkMax(RobotConstants.Ports.LEFT_DRIVE_MAIN, MotorType.kBrushless);
        leftDrive1.follow(leftDrive0);
        leftDrive2 = new CANSparkMax(RobotConstants.Ports.LEFT_DRIVE_1, MotorType.kBrushless);
        leftDrive2.follow(leftDrive0);
        leftEncoder = new CANEncoder(leftDrive0);
        leftPID = new CANPIDController(leftDrive1);
        DrivetrainConfig.configMotorController(leftDrive1);

        rightDrive0 = new CANSparkMax(RobotConstants.Ports.RIGHT_DRIVE_0, MotorType.kBrushless);
        rightDrive1 = new CANSparkMax(RobotConstants.Ports.RIGHT_DRIVE_MAIN, MotorType.kBrushless);
        rightDrive1.follow(rightDrive0);
        rightDrive2 = new CANSparkMax(RobotConstants.Ports.RIGHT_DRIVE_1, MotorType.kBrushless);
        rightDrive2.follow(rightDrive0);
        rightEncoder = new CANEncoder(rightDrive0);
        rightPID = new CANPIDController(rightDrive1);
        DrivetrainConfig.configMotorController(rightDrive1);

        leftDrive = new SpeedControllerGroup(leftDrive0, leftDrive1, leftDrive2);
        rightDrive = new SpeedControllerGroup(rightDrive0, rightDrive1, rightDrive2);
        robotDrive = new DifferentialDrive(leftDrive, rightDrive);
        robotDrive.setSafetyEnabled(false);

        gyro = new PigeonIMU(RobotConstants.Ports.GYRO);

        gearShifter = new DoubleSolenoid(RobotConstants.Ports.DRIVE_SOLENOID_OPEN, RobotConstants.Ports.DRIVE_SOLENOID_CLOSE);

        compressor = new Compressor(0);
        compressor.setClosedLoopControl(true);

        //PTOshifter = new DoubleSolenoid(RobotConstants.Ports.PTO_SOLENOID_OPEN, RobotConstants.Ports.PTO_SOLENOID_CLOSE);
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

/* ----- FEEDBACK DEVICES ----- */

    public double getLeftEncoderPos(){
        return leftEncoder.getPosition();
    }
    public double getRightEncoderPos(){
        return rightEncoder.getPosition();
    }

    public double getYaw(){
        double[] ypr = new double[2];
        gyro.getYawPitchRoll(ypr);
        return ypr[0];
    }
    public double getPitch(){
        double[] ypr = new double[2];
        gyro.getYawPitchRoll(ypr);
        return ypr[1];
    }
    public double getRoll(){
        double[] ypr = new double[2];
        gyro.getYawPitchRoll(ypr);
        return ypr[2];
    }

    //Feedback Config
    public void setDrivetrainEncoders(double value){
        leftEncoder.setPosition(value);
        rightEncoder.setPosition(value);
    }
    public void resetGyro(){
        gyro.setYaw(0);
        gyro.setAccumZAngle(0);
    }

/* ----- DRIVETRAIN METHODS ----- */
    public void stopRobot(){
        robotDrive.tankDrive(0, 0);
    }

    public void leftClosedLoop(double input){
        //In RPM
        double setpoint = input * RobotConstants.MAX_DRIVETRAIN_RPM;
        leftPID.setReference(setpoint, ControlType.kSmartVelocity);
    }
    public void rightClosedLoop(double input){
        //In RPM
        double setpoint = input * RobotConstants.MAX_DRIVETRAIN_RPM;
        rightPID.setReference(setpoint, ControlType.kSmartVelocity);
    }
    
    public boolean toPosition(double setpoint){
        //SETPOINTS MUST BE IN ROTATIONS
        leftPID.setReference(setpoint, ControlType.kPosition);
        rightPID.setReference(setpoint, ControlType.kPosition);
        return (setpoint - leftEncoder.getPosition() == 0) || (setpoint - rightEncoder.getPosition() == 0);
    }



/* ----- CLIMB PTO ----- */

    /*public void setPTO(PTOState ptoValue){
        PTOshifter.set(ptoValue.value);
        ptoState = ptoValue;
    }*/


/* ----- GEAR SHIFTING ----- */

    public void shiftGear(GearState gearValue){
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
}