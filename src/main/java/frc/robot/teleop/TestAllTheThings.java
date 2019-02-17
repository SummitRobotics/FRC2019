package frc.robot.teleop;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
        double xSpeed = gamepad.getForwardPower();
        double zRotation = gamepad.getRotationalPower();
        robot.drivetrain.robotDrive.arcadeDrive(xSpeed, zRotation);
        robot.claw.runArm(gamepad.getRightJoystickX());
        robot.intake.moveIntakeArm(gamepad.getRightJoystickY());

        if(gamepad.isButtonA()){
            robot.claw.setClaw(ClawState.CLOSE);
        }
        if(gamepad.isButtonB()){
            robot.intake.intake(1);
        }
        if(gamepad.isButtonY()){
            robot.peg.setPeg(PegState.UP);
        }
        if(gamepad.isButtonX()){
            robot.peg.setPeg(PegState.DOWN);
        }

        SmartDashboard.putBoolean("Break 1", robot.intake.getBreak1());
        SmartDashboard.putBoolean("Break 2", robot.intake.getBreak2());
        SmartDashboard.putBoolean("Mast Limit", robot.lift.getLowLimit());
        SmartDashboard.putBoolean("Cargo Limit", robot.intake.getCargoLimit());
        SmartDashboard.putBoolean("Claw Limit", robot.claw.getClawLimit());

    }
}