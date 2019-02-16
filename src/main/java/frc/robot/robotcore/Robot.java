/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.robotcore;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.autonomous.TestAuto;
import frc.robot.autonomous.Yeet;
import frc.robot.devices.REVdisplay;
import frc.robot.teleop.TeleopArcade;


public class Robot extends TimedRobot {
  public RobotBuilder robot;
  public static TeleopArcade teleop;
  public OI gamepad;

  public static SendableChooser<OI.Driver_Profile> DriverProfileChooser = new SendableChooser<>();
  private Command auto;
  private SendableChooser<Command> autoChooser = new SendableChooser<>();

  @Override
  public void robotInit() {
    robot = RobotBuilder.getInstance();
    teleop = new TeleopArcade();
    gamepad = OI.getInstance();

    DriverProfileChooser.setDefaultOption("Alex Driver Profile", gamepad.new Alex_Driver());
    DriverProfileChooser.addOption("Colin Driver Profile", gamepad.new Colin_Driver());
    //DriverProfileChooser.addOption("Jake Driver Profile", gamepad.new Jake_Driver());

    autoChooser.setDefaultOption("Jump From HAB", new Yeet());
    autoChooser.addOption("Test Auto", new TestAuto());
    robot.init();

  }

  @Override
  public void robotPeriodic() {
    REVdisplay.getInstance().run();
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
    robot.drivetrain.resetGyro();
    
    if(auto != null){
      auto.start();
    }
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
    teleop.init();
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    teleop.run();
  }

  @Override
  public void testPeriodic() {
  }
}
