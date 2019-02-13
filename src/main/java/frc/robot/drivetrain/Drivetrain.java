package frc.robot.drivetrain;

import com.ctre.phoenix.sensors.PigeonIMU;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.drivetrain.drivetraincommands.ArcadeDrive;
import frc.robot.robotcore.RobotConstants;

public class Drivetrain extends Subsystem{

    public enum GearState{
        LOW(Value.kForward),
        HIGH(Value.kReverse);

        public final Value value;
        GearState(Value value){
            this.value = value;
        }
    }

    public enum PTOState{
        ENGAGED(Value.kForward),
        DISENGAGED(Value.kReverse);

        public final Value value;
        PTOState(Value value){
            this.value = value;
        }
    }

    public enum Blinkin{
        RAINBOW_PALETTE(-0.99),
        RAINBOW_PARTY(-0.97),
        RAINBOW_OCEAN(-0.95),
        LARSON_SCANNER_RED(-0.35),
        COLOR_1_SLOW(0.03),
        COLOR_1_MED(0.05),
        COLOR_1_FAST(0.07),
        COLOR_1_2(0.53),
        BLUE(0.87),
        BLUE_VIOLET(0.89),
        VIOLET(0.91);

        public final double value;

        Blinkin(double value){
            this.value = value;
        }
    }
    private CANSparkMax leftDrive0, leftDrive1, leftDrive2, rightDrive0, rightDrive1, rightDrive2;
    private SpeedControllerGroup leftDrive, rightDrive;
    public DifferentialDrive robotDrive;
    private CANEncoder leftEncoder, rightEncoder;

    private DoubleSolenoid gearShifter;
    private GearState gearState;

    private DoubleSolenoid PTOshifter;
    private PTOState ptoState;

    private Spark blinkin;
    private Blinkin RGBState;

    private PigeonIMU gyro;

    private static Drivetrain instance;
    
    private Drivetrain(){
        leftDrive0 = new CANSparkMax(RobotConstants.Ports.LEFT_DRIVE_MAIN, MotorType.kBrushless);
        leftDrive1 = new CANSparkMax(RobotConstants.Ports.LEFT_DRIVE_1, MotorType.kBrushless);
        leftDrive1.follow(leftDrive0);
        leftDrive2 = new CANSparkMax(RobotConstants.Ports.LEFT_DRIVE_2, MotorType.kBrushless);
        leftDrive2.follow(leftDrive0);
        leftEncoder = new CANEncoder(leftDrive0);

        rightDrive0 = new CANSparkMax(RobotConstants.Ports.RIGHT_DRIVE_MAIN, MotorType.kBrushless);
        rightDrive1 = new CANSparkMax(RobotConstants.Ports.RIGHT_DRIVE_1, MotorType.kBrushless);
        rightDrive1.follow(rightDrive0);
        rightDrive2 = new CANSparkMax(RobotConstants.Ports.RIGHT_DRIVE_2, MotorType.kBrushless);
        rightDrive2.follow(rightDrive0);
        rightEncoder = new CANEncoder(rightDrive0);

        leftDrive = new SpeedControllerGroup(leftDrive0, leftDrive1, leftDrive2);
        rightDrive = new SpeedControllerGroup(rightDrive0, rightDrive1, rightDrive2);
        robotDrive = new DifferentialDrive(leftDrive, rightDrive);
        robotDrive.setSafetyEnabled(false);

        blinkin = new Spark(RobotConstants.Ports.BLINKIN_LED);

        gyro = new PigeonIMU(RobotConstants.Ports.GYRO);

        gearShifter = new DoubleSolenoid(RobotConstants.Ports.DRIVE_SOLENOID_OPEN, RobotConstants.Ports.DRIVE_SOLENOID_CLOSE);
    }

    public static Drivetrain getInstance(){
        if(instance == null){
            instance = new Drivetrain();
        }
        return instance;
    }

    //TODO - better sequencing
    @Override
    protected void initDefaultCommand() {
       setDefaultCommand(new ArcadeDrive());
    }

    public void stopRobot(){
        robotDrive.tankDrive(0, 0);
    }

    public double getLeftEncoderPos(){
        return leftEncoder.getPosition();
    }
    public double getRightEncoderPos(){
        return rightEncoder.getPosition();
    }

    public void resetGyro(){
        
    }

    public void shiftGear(GearState gearValue){
        gearShifter.set(gearValue.value);
        gearState = gearValue;
    }

    public void togglePTO(PTOState ptoValue){
        PTOshifter.set(ptoValue.value);
        ptoState = ptoValue;
    }
    public void setLEDColor(Blinkin blinkinValue){
        blinkin.set(blinkinValue.value);
        SmartDashboard.putNumber("value", blinkinValue.value);
        RGBState = blinkinValue;
    }
}