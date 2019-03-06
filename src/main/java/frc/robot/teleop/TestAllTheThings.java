package frc.robot.teleop;

import frc.robot.panelclaw.Peg;
import frc.robot.panelclaw.Claw.ClawState;
import frc.robot.robotcore.userinput.OI;
import frc.robot.robotcore.RobotBuilder;

public class TestAllTheThings{
    RobotBuilder robot = RobotBuilder.getInstance();
    OI gamepad = OI.getInstance();

    public void init(){
        robot.claw.setClaw(ClawState.OPEN);
        robot.peg.setChair(Peg.PneumaticState.IN);
        robot.cargoIntake.intake(0);
    }

    public void run(){
        //robot.lift.runLiftManual(-OI.getInstance().getRightJoystickY());
        //robot.claw.runArm(-OI.getInstance().getLeftJoystickX());
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
    }
}