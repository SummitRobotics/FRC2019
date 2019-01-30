/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  public XboxController gamepad;

  public OI(){
    gamepad = new XboxController(RobotConstants.Ports.CONTROLLER_PORT);
  }

  public double getLeftTrigger(){
    return gamepad.getTriggerAxis(GenericHID.Hand.kLeft);
  }
  public double getRightTrigger(){
    return gamepad.getTriggerAxis(GenericHID.Hand.kRight);
  }
  public double getLeftJoystickX(){
    return gamepad.getX(GenericHID.Hand.kLeft);
  }


  public double getRotationalInput(){
    return makeCurve(getLeftJoystickX());
  }
  public double getForwardInput(){
    return makeCurve(getRightTrigger() - getLeftTrigger());
  }

  //TODO - Trig-based power curve?
  public double makeCurve(double input){
    return Math.pow(Math.cos((Math.PI/2)*(1-input)), 4);
  }


  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());
}
