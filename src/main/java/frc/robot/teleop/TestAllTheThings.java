package frc.robot.teleop;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.panelclaw.Peg;
import frc.robot.panelclaw.Claw.ClawState;
import frc.robot.robotcore.OI;
import frc.robot.robotcore.RobotBuilder;

public class TestAllTheThings{
    RobotBuilder robot = RobotBuilder.getInstance();
    OI gamepad = OI.getInstance();

    public void init(){
        robot.claw.setClaw(ClawState.OPEN);
        robot.peg.setChair(Peg.PneumaticState.IN);
        robot.intake.intake(0);
    }

    public void run(){
        robot.lift.runLiftManual(-OI.getInstance().getRightJoystickY());
        /*double xSpeed = gamepad.getForwardPower();
        double zRotation = gamepad.getRotationalPower();
        robot.drivetrain.robotDrive.arcadeDrive(xSpeed, zRotation);
        robot.claw.runArm(gamepad.getRightJoystickX() * 0.45);
        robot.intake.moveIntakeArm(gamepad.getRightJoystickY() * 0.30);

        if(gamepad.isButtonA()){
            robot.claw.setClaw(ClawState.CLOSE);
        }
        if(gamepad.isButtonY()){
            robot.peg.setPeg(PegState.UP);
        }
        if(gamepad.isButtonX()){
            robot.peg.setPeg(PegState.DOWN);
        }*/
        SmartDashboard.putBoolean("Break 1", robot.intake.isBallDetected());
        SmartDashboard.putBoolean("Break 2", robot.intake.isBallPresent());
        SmartDashboard.putBoolean("Claw Limit", robot.claw.getClawLimit());
        SmartDashboard.putBoolean("Intake Limit", robot.intake.getCargoLimit());
        SmartDashboard.putBoolean("Mast Limit", robot.lift.getLowLimit());
        SmartDashboard.putBoolean("Panel Detector", robot.claw.isPanelPresent());
    }
}