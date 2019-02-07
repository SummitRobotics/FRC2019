package frc.robot;

import frc.robot.subsystems.Claw;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.I2C;
import frc.robot.sensors.*;

public class RobotBuilder{
    public Drivetrain drivetrain;
    public Claw claw;
    public OI oi;
    public ColorSensor colorSensor;
    private static RobotBuilder instance;

    private RobotBuilder(){
        drivetrain = new Drivetrain();
        claw = new Claw();
        oi = new OI();
        colorSensor = new ColorSensor(I2C.Port.kOnboard);
    }
    public static RobotBuilder getInstance() {
        if (instance == null) {
            instance = new RobotBuilder();
        }
        return instance;
    }
}