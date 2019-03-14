package frc.robot.teleop;

import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.robotcore.RobotBuilder;
import frc.robot.robotcore.userinput.OI;

public class TeleopArcade {
    RobotBuilder robot = RobotBuilder.getInstance();
    OI gamepad = OI.getInstance();

    double xSpeed, zRotation;
    boolean Bprevstate, Bcurrstate;

    public void init(){
    
    }

    public void run(){

        xSpeed = gamepad.fwdDrive();
        zRotation = gamepad.turnDrive();

        //Potentially implement curvatureDrive in the future?
        robot.drivetrain.robotDrive.arcadeDrive(xSpeed, -zRotation);
        
        //Scheduler.getInstance().run();
        
        /*SmartDashboard.putNumber("Left Encoder", robot.drivetrain.getLeftEncoderPos());
        SmartDashboard.putNumber("Right Encoder", robot.drivetrain.getRightEncoderPos());
        SmartDashboard.putNumber("Fwd Power", xSpeed);
        SmartDashboard.putNumber("Gyro Angle", robot.drivetrain.getYaw());*/
    
    }

}