package frc.robot.teleop;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotBuilder;

public class TeleopArcade {
    RobotBuilder robot = RobotBuilder.getInstance();

    double xSpeed, zRotation;

    public void init(){
    
    }

    public void run(){

        xSpeed = robot.oi.getForwardPower();
        zRotation = robot.oi.getRotationalPower();

        //Potentially implement curvatureDrive in the future?
        robot.drivetrain.robotDrive.arcadeDrive(xSpeed, -zRotation);
        
        SmartDashboard.putNumber("Left Encoder", robot.drivetrain.getLeftEncoderPos());
        SmartDashboard.putNumber("Right Encoder", robot.drivetrain.getRightEncoderPos());
    
    }

}