package frc.robot.robotcore;

import frc.robot.panelclaw.Claw;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.cargointake.Intake;
import frc.robot.devices.Limelight;
import frc.robot.devices.REVdisplay;
import frc.robot.drivetrain.Drivetrain;
import frc.robot.drivetrain.Drivetrain.GearState;
import frc.robot.lift.Lift;
import frc.robot.panelclaw.Peg;

public class RobotBuilder{
    public Drivetrain drivetrain;
    public Claw claw;
    public Peg peg;
    public Lift lift;
    public Intake intake;
    public Limelight lemonlight;
    public REVdisplay display;
    private static RobotBuilder instance;

    private RobotBuilder(){
        drivetrain = Drivetrain.getInstance();
        claw = Claw.getIntance();
        peg = Peg.getInstance();
        intake = Intake.getInstance();
        lift = Lift.getInstance();
        lemonlight = Limelight.getInstance();
        display = REVdisplay.getInstance();
    }
    public static RobotBuilder getInstance() {
        if (instance == null) {
            instance = new RobotBuilder();
        }
        return instance;
    }
    public void init(){
        drivetrain.resetGyro();
        drivetrain.shiftGear(GearState.LOW);
        lemonlight.disableLights();
    }
    
    public void run(){
        SmartDashboard.putNumber("Intake Arm Encoder",intake.getIntakeArmEncoder());
    }
}