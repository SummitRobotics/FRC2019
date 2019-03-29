package frc.robot.robotcore.userinput;

import frc.robot.cargointake.cargoautomation.LoadCargoFromGround;
import frc.robot.cargointake.cargocommands.EnableRollers;
import frc.robot.cargointake.cargocommands.ResetArmEncoder;
import frc.robot.panelclaw.chairautomation.*;
import frc.robot.drivetrain.drivetraincommands.Shift;
import frc.robot.drivetrain.drivetraincommands.vision.TargetAlignment;
import frc.robot.lift.Lift;
import frc.robot.lift.liftcommands.MoveMast;
import frc.robot.lift.liftcommands.ResetMastEncoder;
import frc.robot.panelclaw.clawcommands.ActuateClaw;
import frc.robot.panelclaw.clawcommands.ResetClawEncoder;
import frc.robot.panelclaw.pegcommands.ActuatePeg;
import frc.robot.robotcore.RobotBuilder;
import frc.robot.robotcore.RobotConstants;
import frc.robot.robotcore.universalcommands.KillCommands;


public class OI {

    private ButtonDashboard dashboard = new ButtonDashboard();
    public DriverController driver1 = new DriverController(RobotConstants.Ports.DRIVER_1_PORT);
    private RobotBuilder robot;

    private final double DEADZONE = 0.10;
    private static OI instance;

    private OI(){

        robot = RobotBuilder.getInstance();

        robot.cargoIntake.isUpButton.whenPressed(new ResetArmEncoder());
        robot.claw.isClawUpButton.whenPressed(new ResetClawEncoder());
        robot.lift.liftLowLimitButton.whenPressed(new ResetMastEncoder());

        /*
        dashboard.pos1.whenPressed(new DriveToPos1(dashboard.sideAndHeight.get()));
        dashboard.pos2.whenPressed(new DriveToPos2(dashboard.sideAndHeight.get()));
        dashboard.pos3.whenPressed(new DriveToPos3(dashboard.sideAndHeight.get()));
        dashboard.pos4.whenPressed(new DriveToPos4(dashboard.sideAndHeight.get()));
        */
        dashboard.cargoGround.whenPressed(new LoadCargoFromGround());
        dashboard.bop.whenPressed(new PunchCargo());
        dashboard.mastHigh.whenPressed(new MoveMast(Lift.LiftState.HIGH));
        dashboard.mastMid.whenPressed(new MoveMast(Lift.LiftState.MID));
        dashboard.mastLow.whenPressed(new MoveMast(Lift.LiftState.LOW));
        dashboard.panelGround.whenPressed(new FloorIntakePanel());

        driver1.AButtonCmd.whileHeld(new TargetAlignment());
        driver1.BButtonCmd.whenPressed(new EjectPanel());
        driver1.XButtonCmd.whenPressed(new PinPanel());
        driver1.YButtonCmd.whenPressed(new KillCommands());
        driver1.leftBumperCmd.whenPressed(new Shift().new ToggleShift());
        driver1.backButtonCmd.whenPressed(new ActuatePeg().new TogglePeg());
        //driver1.YButtonCmd.whenPressed(new EjectCargoToRocket());

        driver1.StartButtonCmd.whenPressed(new EnableRollers().new ToggleRollers());
        driver1.backButtonCmd.whenPressed(new ActuateClaw().new ToggleClaw());
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
        if(driver1.isDpadRight()){
            return POWER;
        }
        else if(driver1.isDpadLeft()){
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