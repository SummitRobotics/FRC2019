package frc.robot.teleop;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.robotcore.OI;
import frc.robot.robotcore.RobotBuilder;

public class TeleopArcade {
    RobotBuilder robot = RobotBuilder.getInstance();
    OI gamepad = OI.getInstance();

    double xSpeed, zRotation;
    boolean Bprevstate, Bcurrstate;

    public void init(){
    
    }

    public void run(){

        xSpeed = gamepad.getForwardPower();
        zRotation = gamepad.getRotationalPower();

        Bprevstate = Bcurrstate;
        Bcurrstate = gamepad.isButtonB();
        if(Bcurrstate && !Bprevstate) {
            String clawState = robot.claw.toggleClaw().toString();
            SmartDashboard.putString("Claw State", clawState);
        }

        //Potentially implement curvatureDrive in the future?
        robot.drivetrain.robotDrive.arcadeDrive(xSpeed, -zRotation);
        
        SmartDashboard.putNumber("Left Encoder", robot.drivetrain.getLeftEncoderPos());
        SmartDashboard.putNumber("Right Encoder", robot.drivetrain.getRightEncoderPos());
        SmartDashboard.putNumber("Fwd Power", xSpeed);
        SmartDashboard.putNumber("Gyro Angle", robot.drivetrain.getYaw());
    
    }

}