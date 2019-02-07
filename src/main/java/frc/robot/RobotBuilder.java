package frc.robot;

import frc.robot.panelClaw.Claw;
import frc.robot.drivetrain.Drivetrain;
import frc.robot.panelClaw.Peg;

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