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
        drivetrain = Drivetrain.getInstance();
        claw = Claw.getIntance();
        oi = new OI();
        peg = Peg.getInstance();
    }
    public static RobotBuilder getInstance() {
        if (instance == null) {
            instance = new RobotBuilder();
        }
        return instance;
    }
}