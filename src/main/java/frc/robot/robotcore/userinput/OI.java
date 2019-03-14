package frc.robot.robotcore.userinput;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.Button;
import frc.robot.cargointake.cargoautomation.LoadCargoFromGround;
import frc.robot.cargointake.cargoautomation.LoadFromCargoStation;
import frc.robot.cargointake.cargocommands.EnableRollers;
import frc.robot.panelclaw.chairautomation.*;
import frc.robot.drivetrain.drivetraincommands.Climb;
import frc.robot.drivetrain.drivetraincommands.Shift;
import frc.robot.drivetrain.drivetraincommands.vision.TargetAlignment;
import frc.robot.lift.Lift;
import frc.robot.lift.Lift.LiftState;
import frc.robot.lift.liftcommands.MoveMast;
import frc.robot.panelclaw.Claw;
import frc.robot.panelclaw.clawcommands.ActuateClaw;
import frc.robot.panelclaw.clawcommands.RaiseClaw;
import frc.robot.panelclaw.pegcommands.ActuateChair;
import frc.robot.panelclaw.pegcommands.ActuatePeg;
import frc.robot.panelclaw.pegcommands.BopIt;
import frc.robot.robotcore.universalcommands.lifteject;


public class OI {

    //ButtonDashboard dashboard = new ButtonDashboard();
    public DriverController driver1 = new DriverController(DriverController.Driver.DRIVER_1);

    private final double DEADZONE = 0.10;
    private static OI instance;

    private OI(){

        /*dashboard.CargoGround.whenPressed(new LoadCargoFromGround());
        //dashboard.CargoLoadStation.whenPressed(new LoadFromCargoStation());
        dashboard.MastHigh.whenPressed(new MoveMast(Lift.LiftState.HIGH));
        dashboard.MastMid.whenPressed(new MoveMast(Lift.LiftState.MID));
        dashboard.MastLow.whenPressed(new MoveMast(Lift.LiftState.LOW));
        dashboard.PanelGround.whenPressed(new FloorIntakePanel());
        dashboard.PanelLoad.whenPressed(new EjectPanel());
        dashboard.climb.whenPressed(new Climb());*/

        driver1.AButtonCmd.whileHeld(new TargetAlignment());
        driver1.BButtonCmd.whenPressed(new EjectPanel());
        driver1.XButtonCmd.whenPressed(new PinPanel());
        driver1.YButtonCmd.whenPressed(new PunchCargo());
        driver1.leftBumperCmd.whenPressed(new Shift().new ToggleShift());
        driver1.rightBumperCmd.whenPressed(new EnableRollers().new ToggleRollers());
        driver1.BackButtonCmd.whenPressed(new ActuatePeg().new TogglePeg());       
    }

    
    public static OI getInstance(){
        if(instance == null){
            instance = new OI();
        }
        return instance;
    }

    //"Driver" methods
    public double mastDrive(){
        return deadzone(driver1.getRightJoystickY());
    }
    public double fwdDrive(){
        return deadzone(driver1.getForwardPower());
    }
    public double turnDrive(){
        return deadzone(driver1.getRotationalPower());
    }
    public double cargoArmDrive(){
        double POWER = 0.35;
        if(driver1.isDpadUp()){
            return POWER;
        }
        else if(driver1.isDpadDown()){
            return -POWER;
        }
        else{
            return 0;
        }
    }
    public double clawArmDrive(){
        double POWER = 0.35;
        if(driver1.isDpadUp()){
            return POWER;
        }
        else if(driver1.isDpadDown()){
            return -POWER;
        }
        else{
            return 0;
        }
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
}