package frc.robot.sensors;

import edu.wpi.first.wpilibj.DigitalInput;

public class DIOSensor {

    DigitalInput breakbeam;

    public DIOSensor(int channel) {

        breakbeam = new DigitalInput(channel);
    }

    public boolean isActive() {

        return breakbeam.get();
    }
}