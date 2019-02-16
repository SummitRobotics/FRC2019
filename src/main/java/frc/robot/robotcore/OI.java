package frc.robot.robotcore;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.Button;
import frc.robot.drivetrain.Drivetrain;
import frc.robot.drivetrain.drivetraincommands.Shift;

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
        rightBumperCmd.whileHeld(new Shift(Drivetrain.GearState.HIGH));
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

    /*//Magic abstractation. Do not touch. 
    public abstract class Driver_Profile{
        public Driver_Profile(){

        }
        public abstract double getForwardPower();
        public abstract double getRotationalPower();
        public abstract double getTrimPower();
    }



    public class Alex_Driver extends Driver_Profile{
        public Alex_Driver(){
            rightBumperCmd.whenPressed(new Shift(Drivetrain.GearState.HIGH));
            YButtonCmd.whenPressed(new IntakePanel());
        }
        @Override
        public double getForwardPower(){
            return makeCurve(getRightTrigger()) - makeCurve(getLeftTrigger());
        }
        @Override
        public double getRotationalPower(){
            return Math.copySign(makeCurve(Math.abs(getLeftJoystickX())), getLeftJoystickX());
        }
        @Override
        public double getTrimPower(){
            return getRightJoystickY();
        }
    }


    public class Colin_Driver extends Driver_Profile{
        public Colin_Driver(){
            rightBumperCmd.whileHeld(new Shift(Drivetrain.GearState.HIGH));
        }
        @Override
        public double getForwardPower(){
            return makeCurve(getRightTrigger()) - makeCurve(getLeftTrigger());
        }
        @Override
        public double getRotationalPower(){
            return Math.copySign(makeCurve(Math.abs(getLeftJoystickX())), getLeftJoystickX());
        }
        @Override
        public double getTrimPower(){
            return getRightJoystickY();
        }
    }

    
    public class Jake_Driver extends Driver_Profile{
        public Jake_Driver(){
            rightBumperCmd.whenPressed(new Shift(Drivetrain.GearState.HIGH));
            leftBumperCmd.whenPressed(new Shift(Drivetrain.GearState.LOW));
        }
        @Override
        public double getForwardPower(){
            return makeCurve(getRightTrigger()) - makeCurve(getLeftTrigger());
        }
        @Override
        public double getRotationalPower(){
            return Math.copySign(makeCurve(Math.abs(getLeftJoystickX())), getLeftJoystickX());
        }
        @Override
        public double getTrimPower(){
            return getRightJoystickY();
        }
    }*/
}
    
