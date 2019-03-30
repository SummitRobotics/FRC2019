package frc.robot.robotcore.userinput;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.GenericHID;

public class DriverController{

    private XboxController controller;

    final double SPEED_DEADZONE = 0.1;
    final double SPEED_EXPONENT = 2.0;

    final double TURN_DEADZONE = 0.1;
    final double TURN_EXPONENT = 2.0;

    //Command Schedulers

    Button StartButtonCmd = new Button(){
    
        @Override
        public boolean get() {
            //return controller.getStartButton();
            return controller.getRawButton(8);
        }
    };
    Button backButtonCmd = new Button(){
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
            return isButtonA();
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

    public DriverController(int port){
        controller = new XboxController(port);
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
    /*
    public double makeFwdCurve(double input){
        return (Math.pow(OI.truncatePower(input), 1.5) -1);
    }
    public double makeTurnCurve(double input){
        return(Math.pow(OI.truncatePower(input), 1.8));
    }
    public double getForwardPower(){
        return makeFwdCurve(getLeftTrigger()) - makeFwdCurve(getRightTrigger());
    }
    //todo - power curving for rotation
    public double getRotationalPower(){
        return Math.copySign(makeTurnCurve(Math.abs(getLeftJoystickX())), -getLeftJoystickX());
    }
    */

    public double makeFwdCurve(double input){
        //makes sure input is within bounds, adjusts linear slope to start at deadzone and end at 1, applies exponent to adjust sensitivity
        //because of how the deadzone and exponents are being calculated, the value will never exceed 1 if it starts equal to or less than 1,
        //which is why the clamp is the inner most method
        return -toExponential(deadzone(clamp(input, -1, 1), SPEED_DEADZONE), SPEED_EXPONENT);
    }
    
    public double makeTurnCurve(double input){
        return -toExponential(deadzone(clamp(input, -1, 1), TURN_DEADZONE), TURN_EXPONENT);
    }
    
    //it will probably drive backwards with this, but humor me and lets test it before adjusting?
    public double getForwardPower(){
        return (makeFwdCurve(getRightTrigger()) - makeFwdCurve(getLeftTrigger()));
    }
    
    public double getRotationalPower(){
        return makeTurnCurve(getLeftJoystickX());
    }    

    public double getRightJoystickPower(){
        return deadzone(getRightJoystickY(),.15);
    }

    //just general clamp function, useful for other things as well besides drive is why i make it flexible
    //ex: say you want to limit the speed that the elevator motor decends, but not raises (with manual control). bam clamp
    public static double clamp(double value, double min, double max){
        if (value < min){
            value = min;
        }

        if (value > max){
            value = max;
        }

        return value;
    }

    //this makes it so that if your deadzone is say 0.2, you can still input a value of say 0.15 with the controller, just further from center
    //the way I was taught and the way we've been doing it sorta sucks (TIL), because the value will jump from 0 straight to whatever your deadzone is
    public static double deadzone(double value, double deadzone){
        if (Math.abs(value) < deadzone){
            return 0.0;
        }
        return (Math.abs(value) - deadzone) / (1 - deadzone) * Math.signum(value); //adjusts slope to go through the points: (deadzone, 0) & (1,1)
    }

    //cleeeeaaaan math :insert ok hand: i just learned this too and feel stupid for not thinking of it
    public static double toExponential(double value, double power){
        return Math.abs(Math.pow(value, power - 1)) * value; //this is just fancy math that makes it so you don't need signum() or copysign(), also learned that
    }
}