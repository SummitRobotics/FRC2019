package frc.robot.teleop;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.robotcore.Robot;
import frc.robot.robotcore.RobotBuilder;

public class TeleopArcade {
    RobotBuilder robot = RobotBuilder.getInstance();

    double xSpeed, zRotation;

    public void init(){
    
    }

    public void run(){

        xSpeed = Robot.DriverProfileChooser.getSelected().getForwardPower();
        zRotation = Robot.DriverProfileChooser.getSelected().getRotationalPower();

        //Potentially implement curvatureDrive in the future?
        robot.drivetrain.robotDrive.arcadeDrive(xSpeed, -zRotation);
        
        SmartDashboard.putNumber("Left Encoder", robot.drivetrain.getLeftEncoderPos());
        SmartDashboard.putNumber("Right Encoder", robot.drivetrain.getRightEncoderPos());
    
    }

}