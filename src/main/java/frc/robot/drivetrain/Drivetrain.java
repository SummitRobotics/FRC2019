package frc.robot.drivetrain;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotConstants;

public class Drivetrain extends Subsystem{
    public CANSparkMax leftDrive0, leftDrive1, leftDrive2, rightDrive0, rightDrive1, rightDrive2;
    public SpeedControllerGroup leftDrive, rightDrive;
    public DifferentialDrive robotDrive;
    public CANEncoder leftEncoder, rightEncoder;

    public DoubleSolenoid gearShifter;
    private boolean gearState;

    public DoubleSolenoid PTOshifter;
    private boolean PTOstate;
    
    public Drivetrain(){
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

        gearShifter = new DoubleSolenoid(RobotConstants.Ports.DRIVE_SOLENOID_OPEN, RobotConstants.Ports.DRIVE_SOLENOID_CLOSE);
        
    }

    //TODO - better sequencing
    @Override
    protected void initDefaultCommand() {
       
    }

    public double getLeftEncoderPos(){
        return leftEncoder.getPosition();
    }
    public double getRightEncoderPos(){
        return rightEncoder.getPosition();
    }

    public void engageHighGear(){
        gearShifter.set(Value.kForward);
        gearState = true;
    }
    public void engageLowGear(){
        gearShifter.set(Value.kReverse);
        gearState = false;
    }

    public void togglePTO(){
        /*OWEN DO THE TOGGLE THINGY*/
    }

}