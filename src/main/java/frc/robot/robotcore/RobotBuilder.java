package frc.robot.robotcore;

import frc.robot.panelclaw.Claw;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.cargointake.Intake;
import frc.robot.devices.Limelight;
import frc.robot.devices.PressureSensor;
import frc.robot.devices.REVdisplay;
import frc.robot.drivetrain.Drivetrain;
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
    public PressureSensor pressureSensor;
    private static RobotBuilder instance;

    private RobotBuilder(){
        drivetrain = Drivetrain.getInstance();
        claw = Claw.getInstance();
        peg = Peg.getInstance();
        intake = Intake.getInstance();
        lift = Lift.getInstance();
        lemonlight = Limelight.getInstance();
        display = REVdisplay.getInstance();
        pressureSensor = PressureSensor.getInstance();
    }
    public static RobotBuilder getInstance() {
        if (instance == null) {
            instance = new RobotBuilder();
        }
        return instance;
    }
    public void init(){
        drivetrain.resetGyro();
        drivetrain.shiftGear(Drivetrain.GearState.HIGH);
        intake.resetArmEncoder();
        intake.setIntakeSpin(Intake.IntakeSpinState.OFF);
        peg.setPeg(Peg.PegState.UP);
        peg.setChair(Peg.ChairState.IN);
        lemonlight.disableLights();
        claw.setClaw(Claw.ClawState.OPEN);
    }
    
    public void run(){
        claw.run();
        display.run();
    }
    public void dashboard(){
        SmartDashboard.putNumber("Current PSI", pressureSensor.getPressure());
        SmartDashboard.putNumber("Intake Arm Encoder",intake.getIntakeArmEncoder());
        SmartDashboard.putBoolean("Break 1", intake.isBallDetected());
        SmartDashboard.putBoolean("Break 2", intake.isBallPresent());
        SmartDashboard.putBoolean("Claw Limit", claw.getClawLimit());
        SmartDashboard.putBoolean("Intake Limit", intake.getCargoLimit());
        SmartDashboard.putBoolean("Mast Limit", lift.getLowLimit());
        SmartDashboard.putBoolean("Panel Detector", claw.isPanelPresent());
        SmartDashboard.putString("Claw State", claw.getClawState().toString());

        claw.panelSensor.read();
        SmartDashboard.putNumber("Red", claw.panelSensor.red);
        SmartDashboard.putNumber("Green", claw.panelSensor.green);
        SmartDashboard.putNumber("Blue", claw.panelSensor.blue);
    }
}