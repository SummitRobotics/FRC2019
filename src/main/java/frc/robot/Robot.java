/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.teleop.TeleopArcade;


public class Robot extends TimedRobot {
  public RobotBuilder robot;
  public static TeleopArcade teleop;

  @Override
  public void robotInit() {
    robot = RobotBuilder.getInstance();
    teleop = new TeleopArcade();

  }

  @Override
  public void robotPeriodic() {
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
    /*if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }*/
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
