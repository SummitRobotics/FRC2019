package frc.robot;

import frc.robot.subsystems.Claw;
import frc.robot.subsystems.Drivetrain;

public class RobotBuilder{
    public Drivetrain drivetrain;
    public Claw claw;
    public OI oi;
    private static RobotBuilder instance;

    private RobotBuilder(){
        drivetrain = new Drivetrain();
        claw = new Claw();
        oi = new OI();

    }
    public static RobotBuilder getInstance() {
        if (instance == null) {
            instance = new RobotBuilder();
        }
        return instance;
    }
}