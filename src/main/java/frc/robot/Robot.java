/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.autonomous.LeftCargoShip;
import frc.robot.autonomous.LeftCargoShipPower;
import frc.robot.autonomous.RightCargoShip;
import frc.robot.autonomous.RocketLeft;
import frc.robot.autonomous.RocketRight;
import frc.robot.autonomous.Yeet;
import frc.robot.robotcore.userinput.OI;
import frc.robot.robotcore.RobotBuilder;


public class Robot extends TimedRobot {
  public RobotBuilder robot;
  public static OI gamepad;

  private Command auto;
  private SendableChooser<Command> autoChooser = new SendableChooser<>();

  public Robot(){
    super(0.020);
  }

  @Override
  public void robotInit() {
    robot = RobotBuilder.getInstance();
    robot.init();
    robot.matchInit();

    autoChooser.setDefaultOption("No Auto", null);
    autoChooser.addOption("Yeet", new Yeet());
    autoChooser.addOption("Left Cargo Ship", new LeftCargoShip());
    autoChooser.addOption("Right Cargo Ship", new RightCargoShip());
    autoChooser.addOption("Left Rocket", new RocketLeft());
    autoChooser.addOption("Power Cargo", new LeftCargoShipPower());
    autoChooser.addOption("Right Rocket", new RocketRight());
    SmartDashboard.putData("Select Auto", autoChooser);

    //THIS MUST OCCUR AFTER ROBOT INIT
    gamepad = OI.getInstance();
  }

  @Override
  public void robotPeriodic() {
    robot.run();
    //todo - fix dashboard outputs
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    //todo - see if disabling scheduler breaks everything
  }

  @Override
  public void autonomousInit() {
    auto = autoChooser.getSelected();
    robot.init();
    
    if(auto != null){
      auto.start();
    }
    robot.matchInit();
  }

  @Override
  public void autonomousPeriodic() {
    SmartDashboard.putNumber("Gyro Angle Yeet", robot.drivetrain.getYaw());
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    if (auto != null) {
      auto.cancel();
    }

    /* ----- COMMENT THIS OUT WHEN ON FIELD: ***ONLY USE WHEN IN PRACTICE*** ----- */
    /*robot.init();
    robot.matchInit();*/
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    robot.dashboard();
  }

  @Override
  public void testInit() {

  }

  @Override
  public void testPeriodic() {

  }
}
