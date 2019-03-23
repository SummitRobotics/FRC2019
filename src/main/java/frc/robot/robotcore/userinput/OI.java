package frc.robot.robotcore.userinput;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.Button;
import frc.robot.cargointake.CargoIntake;
import frc.robot.cargointake.cargoautomation.EjectCargoToRocket;
import frc.robot.cargointake.cargoautomation.IntakeCargo;
import frc.robot.cargointake.cargoautomation.LoadCargoFromGround;
import frc.robot.cargointake.cargoautomation.LoadFromCargoStation;
import frc.robot.cargointake.cargocommands.DetectCargo;
import frc.robot.cargointake.cargocommands.EnableRollers;
import frc.robot.cargointake.cargocommands.SetCargoArm;
import frc.robot.panelclaw.chairautomation.*;
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
import frc.robot.robotcore.RobotConstants;
import frc.robot.robotcore.universalcommands.KillCommands;
import frc.robot.robotcore.universalcommands.LiftEject;


public class OI {

    //ButtonDashboard dashboard = new ButtonDashboard();
    public DriverController driver1 = new DriverController(RobotConstants.Ports.DRIVER_1_PORT);

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
        driver1.YButtonCmd.whenPressed(new KillCommands());
        driver1.rightBumperCmd.whenPressed(new LoadCargoFromGround());
        //driver1.rightBumperCmd.whenPressed(new EnableRollers().new ToggleRollers());
        driver1.leftBumperCmd.whenPressed(new Shift().new ToggleShift());
        driver1.BackButtonCmd.whenPressed(new ActuatePeg().new TogglePeg());  
        //driver1.YButtonCmd.whenPressed(new EjectCargoToRocket());    
        
    }

    public static OI getInstance(){
        if(instance == null){
            instance = new OI();
        }
        return instance;
    }

    //"Driver" methods
    public double mastDrive(){
        return driver1.getRightJoystickY();
    }
    public double fwdDrive(){
        return driver1.getForwardPower();
    }
    public double turnDrive(){
        return driver1.getRotationalPower();
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

    public double truncatePower(double input){
        if(input > 0){
            if(input < DEADZONE){
                return 0;
            }
            else if(input >= 1){
                return 1;
            }
            return input;
        }
        else if(input < 0){
            if(input > -DEADZONE){
                return 0;
            }
            else if(input <= -1){
                return -1;
            }
            return input;
        }
        else{
            return input;
        }
    }
}