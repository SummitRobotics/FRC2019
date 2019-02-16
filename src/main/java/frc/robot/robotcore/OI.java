package frc.robot.robotcore;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.drivetrain.*;
import frc.robot.drivetrain.Drivetrain.GearState;
import frc.robot.drivetrain.drivetraincommands.*;
import frc.robot.panelclaw.clawcommands.ActuateClaw;
import frc.robot.panelclaw.clawcommands.IntakePanel;
import frc.robot.panelclaw.clawcommands.ToggleClaw;

public class OI {

    XboxController controller = new XboxController(0);

    Button YButtonCmd = new Button(){
    
        @Override
        public boolean get() {
            return controller.getYButton();
        }
    };

    Button rightBumperCmd = new Button(){
    
        @Override
        public boolean get() {
            return controller.getBumper(Hand.kRight);
        }
    };

    Button leftBumperCmd = new Button(){
    
        @Override
        public boolean get() {
            return controller.getBumper(Hand.kLeft);
        }
    };

    private static OI instance;

    private OI(){
        leftBumperCmd.whenActive(new Shift(GearState.HIGH));
        
    }
    public static OI getInstance(){
        if(instance == null){
            instance = new OI();
        }
        return instance;
    }


    //Now here's a bunch of methods for calling all these values.
    public double getLeftJoystickX() {
        return controller.getX(GenericHID.Hand.kLeft);
    }
    public double getLeftJoystickY() {
        return controller.getY(GenericHID.Hand.kLeft);
    }
    public double getRightJoystickX() {
        return controller.getX(GenericHID.Hand.kRight);
    }
    public double getRightJoystickY() {
        return controller.getY(GenericHID.Hand.kRight);
    }

    public boolean isButtonA() {
        return controller.getAButton();
    }
    public boolean isButtonB() {
        return controller.getBButton();
    }
    public boolean isButtonX() {
        return controller.getXButton();
    }
    public boolean isButtonY() {
        return controller.getYButton();
    }
    public boolean isLeftBumper(){
        return controller.getBumper(Hand.kLeft);
    }
    public boolean isRightBumper(){
        return controller.getBumper(Hand.kRight);
    }

    public double getLeftTrigger() {
        return controller.getTriggerAxis(GenericHID.Hand.kLeft);
    }
    public double getRightTrigger() {
        return controller.getTriggerAxis(GenericHID.Hand.kRight);
    }

    public double makeCurve(double input){
        return (Math.pow(2, input) -1);
    }
    
    public double getForwardPower(){
        return makeCurve(getRightTrigger()) - makeCurve(getLeftTrigger());
    }
    public double getRotationalPower(){
        return Math.copySign(makeCurve(Math.abs(getLeftJoystickX())), getLeftJoystickX());
    }

    public class DriverProfiles {

        int[] keybinds;
        Command[] analogActions = new Command[] {new ToggleShift(), new ToggleClaw(), new };

        public DriverProfiles(int[] keybinds) {

            this.keybinds = keybinds;
        }

        public double getForwardPower() {
            
            
        }
    }
}
    
