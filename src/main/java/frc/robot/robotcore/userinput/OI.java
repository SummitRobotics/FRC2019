package frc.robot.robotcore.userinput;

import frc.robot.cargointake.CargoIntake;
import frc.robot.cargointake.cargoautomation.LoadCargoFromGround;
import frc.robot.cargointake.cargocommands.EnableRollers;
import frc.robot.cargointake.cargocommands.ResetCargoArm;
import frc.robot.chair.chairautomation.EjectPanel;
import frc.robot.chair.chairautomation.FloorIntakePanel;
import frc.robot.chair.chairautomation.PinPanel;
import frc.robot.chair.chairautomation.PunchCargo;
import frc.robot.climber.climbautomation.Level2Climb;
import frc.robot.drivetrain.drivetraincommands.Shift;
import frc.robot.drivetrain.drivetraincommands.drivetrainautomation.DriveToPos1;
import frc.robot.drivetrain.drivetraincommands.vision.TargetAlignment;
import frc.robot.mast.Mast;
import frc.robot.mast.mastcommands.MastAutomation;
import frc.robot.mast.mastcommands.ResetMastEncoder;
import frc.robot.panelclaw.clawcommands.ActuateClaw;
import frc.robot.robotcore.RobotConstants;
import frc.robot.robotcore.universalcommands.KillCommands;


public class OI {

    private ButtonDashboard dashboard = new ButtonDashboard();
    public DriverController driver1 = new DriverController(RobotConstants.Ports.DRIVER_1_PORT);

    private static OI instance;

    private OI(){


        CargoIntake.getInstance().isUpButton.whenPressed(new ResetCargoArm());
        //robot.claw.isClawUpButton.whenPressed(new ResetClawEncoder());
        Mast.getInstance().mastLowLimitButton.whenPressed(new ResetMastEncoder());

        dashboard.pos1.whenPressed(new DriveToPos1(dashboard.sideAndHeight.get()));
        /*dashboard.pos2.whenPressed(new DriveToPos2(dashboard.sideAndHeight.get()));
        dashboard.pos3.whenPressed(new DriveToPos3(dashboard.sideAndHeight.get()));
        dashboard.pos4.whenPressed(new DriveToPos4(dashboard.sideAndHeight.get()));
        */
        //dashboard.pos1.whenPressed(new EncoderDrive(10));
        //dashboard.pos1.whenPressed(new PowerDrive(-0.7, false, 2));
        //dashboard.pos2.whenPressed(new PowerMoveClawWrist(2, Claw.ClawSpeed.REVERSE));
        dashboard.cargoGround.whenPressed(new LoadCargoFromGround());
        dashboard.bop.whenPressed(new PunchCargo());
        dashboard.mastHigh.whenPressed(new MastAutomation(Mast.MastState.HIGH));
        dashboard.mastMid.whenPressed(new MastAutomation(Mast.MastState.MID));
        dashboard.mastLow.whenPressed(new MastAutomation(Mast.MastState.LOW));
        dashboard.panelGround.whenPressed(new FloorIntakePanel());
        dashboard.climb.whenPressed(new Level2Climb());

        driver1.AButtonCmd.whileHeld(new TargetAlignment());
        driver1.BButtonCmd.whenPressed(new EjectPanel());
        driver1.XButtonCmd.whenPressed(new PinPanel());
        driver1.YButtonCmd.whenPressed(new KillCommands());
        driver1.leftBumperCmd.whenPressed(new Shift().new ToggleShift());
        //driver1.YButtonCmd.whenPressed(new EjectCargoToRocket());

        driver1.StartButtonCmd.whenPressed(new EnableRollers().new ToggleRollers());
        driver1.backButtonCmd.whenPressed(new ActuateClaw().new ToggleClaw());
        //driver1.backButtonCmd.whenPressed(new RaiseClaw(ClawArmState.DOWN));
        //driver1.StartButtonCmd.whenPressed(new RaiseClaw(ClawArmState.UP));
    }

    public static OI getInstance(){
        if(instance == null){
            instance = new OI();
        }
        return instance;
    }

    //"Driver" methods
    public double mastDrive(){
        return -driver1.getRightJoystickPower();
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
            return -POWER;
        }
        else if(driver1.isDpadLeft()){
            return POWER;
        }
        else{
            return 0;
        }
    }

    /*
    public static double truncatePower(double input){
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
    */
}