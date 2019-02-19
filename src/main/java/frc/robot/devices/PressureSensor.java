package frc.robot.devices;

import edu.wpi.first.wpilibj.AnalogInput;
import frc.robot.robotcore.RobotConstants;

public class PressureSensor{
    AnalogInput pressureSensor;

    public PressureSensor(){
        pressureSensor = new AnalogInput(RobotConstants.Ports.PRESSURE_READER);
    }
    public double getPressure(){
        return (250 * (pressureSensor.getAverageVoltage() / 5)) - 25;
    }
}