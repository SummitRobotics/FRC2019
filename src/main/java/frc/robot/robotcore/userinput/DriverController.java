package frc.robot.robotcore.userinput;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.GenericHID;
import frc.robot.robotcore.RobotConstants;

public class DriverController{

    public enum Driver{
        DRIVER_1(RobotConstants.Ports.DRIVER_1_PORT),
        DRIVER_2(RobotConstants.Ports.DRIVER_2_PORT);

        public final int value;
        Driver(int value){
            this.value = value;
        }
    }

    private XboxController controller;

    //Command Schedulers

    Button StartButtonCmd = new Button(){
    
        @Override
        public boolean get() {
            //return controller.getStartButton();
            return controller.getRawButton(8);
        }
    };
    Button BackButtonCmd = new Button(){
        @Override
        public boolean get(){
            return controller.getBackButton();
        }
    };
    Button YButtonCmd = new Button(){
    
        @Override
        public boolean get() {
            return controller.getYButton();
        }
    };

    Button AButtonCmd = new Button(){
    
        @Override
        public boolean get() {
            return controller.getAButton();
        }
    };

    Button BButtonCmd = new Button(){
    
        @Override
        public boolean get() {
            return controller.getBButton();
        }
    };

    Button rightBumperCmd = new Button(){
    
        @Override
        public boolean get() {
            return controller.getBumper(GenericHID.Hand.kRight);
        }
    };

    Button leftBumperCmd = new Button(){
    
        @Override
        public boolean get() {
            return controller.getBumper(GenericHID.Hand.kLeft);
        }
    };

    Button XButtonCmd = new Button(){
    
        @Override
        public boolean get() {
            return controller.getXButton();
        }
    };

    public DriverController(Driver driver){
        controller = new XboxController(driver.value);
    }

    //Joystick Getters
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

    //Trigger Getters
    public double getLeftTrigger() {
        return controller.getTriggerAxis(GenericHID.Hand.kLeft);
    }
    public double getRightTrigger() {
        return controller.getTriggerAxis(GenericHID.Hand.kRight);
    }


    //Button Getters
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
        return controller.getBumper(GenericHID.Hand.kLeft);
    }
    public boolean isRightBumper(){
        return controller.getBumper(GenericHID.Hand.kRight);
    }
    public boolean isDpadLeft(){
        return controller.getPOV() == 270;
    }
    public boolean isDpadRight(){
        return controller.getPOV() == 90;
    }
    public boolean isDpadUp(){
        return controller.getPOV() == 0;
    }
    public boolean isDpadDown(){
        return controller.getPOV() == 180;
    }


    //Math
    public double makeFwdCurve(double input){
        return (Math.pow(2, OI.getInstance().truncatePower(input)) -1);
    }
    public double makeTurnCurve(double input){
        return(Math.pow(OI.getInstance().truncatePower(input), 1.8));
    }
    public double getForwardPower(){
        return makeFwdCurve(getLeftTrigger()) - makeFwdCurve(getRightTrigger());
    }
    //todo - power curving for rotation
    public double getRotationalPower(){
        return Math.copySign(makeTurnCurve(Math.abs(getLeftJoystickX())), -getLeftJoystickX());
    }
}