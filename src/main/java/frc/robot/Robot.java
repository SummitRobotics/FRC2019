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
import frc.robot.autonomous.RightCargoShip;
import frc.robot.autonomous.RocketLeft;
import frc.robot.autonomous.RocketRight;
import frc.robot.autonomous.Yeet;
import frc.robot.robotcore.userinput.OI;
import frc.robot.robotcore.RobotBuilder;
import frc.robot.teleop.TeleopArcade;
import frc.robot.teleop.TestAllTheThings;


public class Robot extends TimedRobot {
  public RobotBuilder robot;
  public static TeleopArcade teleop;
  public static OI gamepad;
  private TestAllTheThings test;

  //public static SendableChooser<OI.Driver_Profile> DriverProfileChooser = new SendableChooser<>();
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
    teleop = new TeleopArcade();

    autoChooser.setDefaultOption("Jump From HAB", new Yeet());
    autoChooser.addOption("Left Cargo Ship", new LeftCargoShip());
    autoChooser.addOption("Right Cargo Ship", new RightCargoShip());
    autoChooser.addOption("Left Rocket", new RocketLeft());
    autoChooser.addOption("Right Rocket", new RocketRight());
    SmartDashboard.putData("Select Auto", autoChooser);

    //THIS MUST OCCUR AFTER ROBOT INIT
    gamepad = OI.getInstance();
  }

  @Override
  public void robotPeriodic() {
    robot.run();
    robot.dashboard();
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
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
    Scheduler.getInstance().run();

  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (auto != null) {
      auto.cancel();
    }
    //teleop.init();
    robot.init();
    /*test = new TestAllTheThings();
    test.init();*/
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    //test.run();
    //teleop.run();
  }

  @Override
  public void testInit() {
    test = new TestAllTheThings();
    test.init();
  }

  @Override
  public void testPeriodic() {
    test.run();
  }
}
