package frc.robot.robotcore;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.Button;
import frc.robot.cargointake.CargoIntake;
import frc.robot.cargointake.cargoautomation.LoadCargoFromGround;
import frc.robot.cargointake.cargocommands.EnableRollers;
import frc.robot.cargointake.cargocommands.SetCargoArm;
import frc.robot.drivetrain.drivetraincommands.Shift;
import frc.robot.lift.Lift.LiftState;
import frc.robot.lift.liftcommands.MoveMast;
import frc.robot.panelclaw.clawcommands.ActuateClaw;
import frc.robot.panelclaw.pegcommands.ActuateChair;
import frc.robot.panelclaw.pegcommands.ActuatePeg;
import frc.robot.robotcore.universalcommands.IntakePanel;
import frc.robot.robotcore.universalcommands.LoadFromCargoStation;

public class OI {

    XboxController controller = new XboxController(0);
    ButtonDashboard dashboard = new ButtonDashboard();

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
            return controller.getBumper(Hand.kRight);
        }
    };

    Button leftBumperCmd = new Button(){
    
        @Override
        public boolean get() {
            return controller.getBumper(Hand.kLeft);
        }
    };

    Button XButtonCmd = new Button(){
    
        @Override
        public boolean get() {
            return controller.getXButton();
        }
    };

    Button RightJoystickCmd = new Button(){
    
        @Override
        public boolean get() {
            return deadzone(getRightJoystickY()) != 0;
        }
    };

    Button DpadYCmd = new Button(){
    
        @Override
        public boolean get() {
            return isDpadLeft() || isDpadRight();
        }
    };

    Button DpadXCmd = new Button(){
    
        @Override
        public boolean get() {
            return isDpadUp() || isDpadDown();
        }
    };

    Button MastHigh = new Button(){
    
        @Override
        public boolean get() {
            return dashboard.getMastHigh();
        }
    };

    Button MastMid = new Button(){
    
        @Override
        public boolean get() {
            return dashboard.getMastMid();
        }
    };

    Button MastLow = new Button(){
    
        @Override
        public boolean get() {
            return dashboard.getMastLow();
        }
    };
    
    Button CargoLoadStation = new Button(){
    
        @Override
        public boolean get() {
            return dashboard.getCargoLoad();
        }
    };

    Button CargoGround = new Button(){
    
        @Override
        public boolean get() {
            return dashboard.getCargoGround();
        }
    };

    Button PanelLoad = new Button(){
    
        @Override
        public boolean get() {
            return dashboard.getPanelLoad();
        }
    };

    Button PanelGround = new Button(){
    
        @Override
        public boolean get() {
            return dashboard.getPanelGround();
        }
    };

    Button cancelAutomation = new Button(){
    
        @Override
        public boolean get() {
            return dashboard.getCancel();
        }
    };

    Button climb = new Button(){
    
        @Override
        public boolean get() {
            return dashboard.getClimb();
        }
    };

    private final double DEADZONE = 0.10;
    private static OI instance;

    private OI(){
        leftBumperCmd.whenActive(new Shift().new ToggleShift());
        rightBumperCmd.whenActive(new SetCargoArm(CargoIntake.IntakeArmState.UP));
        YButtonCmd.whenActive(new ActuatePeg().new TogglePeg());
        AButtonCmd.whenActive(new EnableRollers().new ToggleRollers());
        BButtonCmd.whenActive(new ActuateChair().new ToggleChair());
        XButtonCmd.whenActive(new ActuateClaw().new ToggleClaw());
        MastHigh.whenPressed(new MoveMast(LiftState.HIGH, 0.5));
        MastMid.whenPressed(new MoveMast(LiftState.MID, 0.5));
        MastLow.whenPressed(new MoveMast(LiftState.LOW, 0.5));
        CargoLoadStation.whenPressed(new LoadFromCargoStation());
        CargoGround.whenPressed(new LoadCargoFromGround());
        PanelGround.whenPressed(new IntakePanel());

    }
    public static OI getInstance(){
        if(instance == null){
            instance = new OI();
        }
        return instance;
    }

    public double deadzone(double input){
        if(input < DEADZONE){
            return 0;
        }
        else if(input > -DEADZONE){
            return 0;
        }
        return input;
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
        return makeCurve(getLeftTrigger()) - makeCurve(getRightTrigger());
    }
    public double getRotationalPower(){
        return Math.copySign(makeCurve(Math.abs(getLeftJoystickX())), -getLeftJoystickX());
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
    
