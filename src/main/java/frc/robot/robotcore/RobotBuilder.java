package frc.robot.robotcore;

import frc.robot.panelclaw.Claw;
import frc.robot.drivetrain.Drivetrain;
import frc.robot.panelclaw.Peg;

public class RobotBuilder{
    public Drivetrain drivetrain;
    public Claw claw;
    public Peg peg;
    private static RobotBuilder instance;

    private RobotBuilder(){
        drivetrain = Drivetrain.getInstance();
        claw = Claw.getIntance();
        peg = Peg.getInstance();
    }
    public static RobotBuilder getInstance() {
        if (instance == null) {
            instance = new RobotBuilder();
        }
        return instance;
    }
    public void init(){
        drivetrain.resetGyro();
    }
}