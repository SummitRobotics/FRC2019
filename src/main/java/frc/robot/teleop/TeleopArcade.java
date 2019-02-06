package frc.robot.teleop;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.RobotBuilder;
import frc.robot.subsystems.Drivetrain;


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
        
        if(robot.oi.isButtonA()){
            robot.drivetrain.gearShifter.set(true);
        }

        SmartDashboard.putNumber("Left Encoder", robot.drivetrain.getLeftEncoderPos());
        SmartDashboard.putNumber("Right Encoder", robot.drivetrain.getRightEncoderPos());
        
    
    }
}