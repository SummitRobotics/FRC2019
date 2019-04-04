package frc.robot.robotcore;

import frc.robot.panelclaw.Claw;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.cargointake.CargoIntake;
import frc.robot.chair.Chair;
import frc.robot.devices.Limelight;
import frc.robot.devices.PressureSensor;
import frc.robot.devices.USBDriverCamera;
import frc.robot.drivetrain.Drivetrain;
import frc.robot.mast.Mast;

public class RobotBuilder{
    public Drivetrain drivetrain;
    public Claw claw;
    public Chair chair;
    public Mast mast;
    public CargoIntake cargoIntake;

    public Limelight lemonlight;
    public PressureSensor pressureSensor;
    public USBDriverCamera driverFeed;

    private static RobotBuilder instance;
    private RobotBuilder(){
        drivetrain = Drivetrain.getInstance();
        claw = Claw.getInstance();
        chair = Chair.getInstance();
        cargoIntake = CargoIntake.getInstance();
        mast = Mast.getInstance();

        driverFeed = new USBDriverCamera();
        lemonlight = new Limelight();
        pressureSensor = new PressureSensor();
    }
    public static RobotBuilder getInstance() {
        if (instance == null) {
            instance = new RobotBuilder();
        }
        return instance;
    }
    public void init(){
        driverFeed.init();
        drivetrain.resetGyro();
        drivetrain.setDrivetrainEncoders(0);
        cargoIntake.setArmEncoder(0);
        lemonlight.enableLights();
        claw.setArmEncoder(0);
        mast.setEncoderPos(0);
    }
    public void matchInit(){
        chair.setPeg(Chair.PegState.UP);
        cargoIntake.setRollers(CargoIntake.RollerState.OFF);
        claw.setArm(Claw.ClawArmState.UP.value);
        drivetrain.shiftGear(Drivetrain.GearState.HIGH);
        chair.setChair(Chair.PneumaticState.OUT);
        chair.setBop(Chair.PneumaticState.IN);
        claw.setClaw(Claw.ClawState.CLOSE);
        drivetrain.resetGyro();
        cargoIntake.setArmEncoder(0);
    }
    
    public void run(){

    }
    public void dashboard(){
        SmartDashboard.putNumber("Current PSI", pressureSensor.getPressure());
        SmartDashboard.putNumber("Voltage", RobotController.getBatteryVoltage());
        SmartDashboard.putNumber("Intake Arm Encoder",cargoIntake.getIntakeArmEncoder());
        SmartDashboard.putBoolean("Break 1", cargoIntake.isBallDetected());
        SmartDashboard.putBoolean("Break 2", cargoIntake.isBallConsumed());
        SmartDashboard.putBoolean("Claw Limit", claw.getClawLimit());
        SmartDashboard.putBoolean("Intake Limit", cargoIntake.getCargoLimit());
        SmartDashboard.putBoolean("Mast Limit", mast.getMastLowLimit());
        SmartDashboard.putBoolean("Panel Detector", claw.isPanelPresent());
        SmartDashboard.putNumber("Claw Arm Encoder", claw.getArmEncoder());
        SmartDashboard.putNumber("Target", lemonlight.getTarget());

        /*claw.panelSensor.read();
        SmartDashboard.putNumber("Red", claw.panelSensor.red);
        SmartDashboard.putNumber("Green", claw.panelSensor.green);
        SmartDashboard.putNumber("Blue", claw.panelSensor.blue);*/

        /*Shuffleboard.getTab("Main").add("Current PSI", pressureSensor.getPressure());
        Shuffleboard.getTab("Main").add("Voltage", RobotController.getBatteryVoltage());*/

    }
}