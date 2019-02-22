package frc.robot.devices;

import edu.wpi.first.wpilibj.AnalogInput;
import frc.robot.robotcore.RobotConstants;

public class PressureSensor{
    AnalogInput pressureSensor;

    private static PressureSensor instance;
    private PressureSensor(){
        pressureSensor = new AnalogInput(RobotConstants.Ports.PRESSURE_READER);
    }
    public static PressureSensor getInstance(){
        if(instance == null){
            instance = new PressureSensor();
        }
        return instance;
    }
    public double getPressure(){
        return (250 * (pressureSensor.getAverageVoltage() / 5)) - 25;
    }
}