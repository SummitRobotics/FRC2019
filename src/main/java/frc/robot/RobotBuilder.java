package frc.robot;

import frc.robot.subsystems.Claw;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Peg;

public class RobotBuilder{
    public Drivetrain drivetrain;
    public Claw claw;
    public Peg peg;
    public OI oi;
    private static RobotBuilder instance;

    private RobotBuilder(){
        drivetrain = new Drivetrain();
        claw = new Claw();
        oi = new OI();
        peg = new Peg();
    }
    public static RobotBuilder getInstance() {
        if (instance == null) {
            instance = new RobotBuilder();
        }
        return instance;
    }
}