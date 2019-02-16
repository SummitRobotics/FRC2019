package frc.robot.teleop;

import frc.robot.panelclaw.Claw.ClawState;
import frc.robot.panelclaw.Peg.PanelOuttakeState;
import frc.robot.panelclaw.Peg.PegState;
import frc.robot.robotcore.OI;
import frc.robot.robotcore.RobotBuilder;

public class TestAllTheThings{
    RobotBuilder robot = RobotBuilder.getInstance();
    OI gamepad = OI.getInstance();

    public void init(){
        robot.claw.setClaw(ClawState.OPEN);
        robot.peg.setPanel(PanelOuttakeState.IN);
        robot.intake.intake(0);
    }

    public void run(){
        robot.drivetrain.rightDrive1.set(gamepad.getForwardPower());
        robot.claw.runArm(gamepad.getLeftJoystickY());
        robot.intake.moveIntakeArm(gamepad.getRightJoystickY());

        if(gamepad.isButtonA()){
            robot.claw.setClaw(ClawState.CLOSE);
        }
        if(gamepad.isButtonB()){
            robot.intake.intake(1);
        }
        if(gamepad.isButtonY()){
            robot.peg.setPanel(PanelOuttakeState.OUT);
        }
        if(gamepad.isButtonX()){
            robot.peg.setPeg(PegState.UP);
        }
    }
}