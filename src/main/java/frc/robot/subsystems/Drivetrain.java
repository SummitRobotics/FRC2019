package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotConstants;
import frc.robot.commands.ArcadeDrive;

public class Drivetrain extends Subsystem{
    public CANSparkMax leftDrive0, leftDrive1, leftDrive2, rightDrive0, rightDrive1, rightDrive2;
    public SpeedControllerGroup leftDrive, rightDrive;
    public DifferentialDrive robotDrive;
    
    public Drivetrain(){
        leftDrive0 = new CANSparkMax(RobotConstants.Ports.LEFT_DRIVE_MAIN, MotorType.kBrushless);
        leftDrive1 = new CANSparkMax(RobotConstants.Ports.LEFT_DRIVE_1, MotorType.kBrushless);
        leftDrive1.follow(leftDrive0);
        leftDrive2 = new CANSparkMax(RobotConstants.Ports.LEFT_DRIVE_2, MotorType.kBrushless);
        leftDrive2.follow(leftDrive0);

        rightDrive0 = new CANSparkMax(RobotConstants.Ports.RIGHT_DRIVE_MAIN, MotorType.kBrushless);
        rightDrive1 = new CANSparkMax(RobotConstants.Ports.RIGHT_DRIVE_1, MotorType.kBrushless);
        rightDrive1.follow(rightDrive0);
        rightDrive2 = new CANSparkMax(RobotConstants.Ports.RIGHT_DRIVE_2, MotorType.kBrushless);
        rightDrive2.follow(rightDrive0);

        leftDrive = new SpeedControllerGroup(leftDrive0, leftDrive1, leftDrive2);
        rightDrive = new SpeedControllerGroup(rightDrive0, rightDrive1, rightDrive2);
        robotDrive = new DifferentialDrive(leftDrive, rightDrive);
    }

    //TODO - better sequencing
    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new ArcadeDrive());
    }

}