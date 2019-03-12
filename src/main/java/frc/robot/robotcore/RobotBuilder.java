package frc.robot.robotcore;

import frc.robot.panelclaw.Claw;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardComponent;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.cargointake.CargoIntake;
import frc.robot.devices.Limelight;
import frc.robot.devices.PressureSensor;
import frc.robot.devices.REVdisplay;
import frc.robot.drivetrain.Drivetrain;
import frc.robot.lift.Lift;
import frc.robot.panelclaw.Peg;
import frc.robot.robotcore.userinput.OI;

public class RobotBuilder{
    public Drivetrain drivetrain;
    public Claw claw;
    public Peg peg;
    public Lift lift;
    public CargoIntake cargoIntake;
    public Limelight lemonlight;
    public REVdisplay display;
    public PressureSensor pressureSensor;
    private static RobotBuilder instance;

    private RobotBuilder(){
        drivetrain = Drivetrain.getInstance();
        claw = Claw.getInstance();
        peg = Peg.getInstance();
        cargoIntake = CargoIntake.getInstance();
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
        //todo - pwm absolute shit
        cargoIntake.setArmEncoder(0);
        lemonlight.enableLights();
    }
    public void matchInit(){
        peg.setPeg(Peg.PegState.UP);
        cargoIntake.setRollers(CargoIntake.RollerState.OFF);
        drivetrain.shiftGear(Drivetrain.GearState.HIGH);
        peg.setChair(Peg.PneumaticState.OUT);
        peg.setBop(Peg.PneumaticState.IN);
        claw.setClaw(Claw.ClawState.OPEN);
        drivetrain.resetGyro();
        cargoIntake.setArmEncoder(0);
        claw.setArmEncoder(0);

    }
    
    public void run(){
        //display.run();
    }
    public void dashboard(){
        /*SmartDashboard.putNumber("Current PSI", pressureSensor.getPressure());
        SmartDashboard.putNumber("Voltage", RobotController.getBatteryVoltage());
        /*SmartDashboard.putNumber("Intake Arm Encoder",cargoIntake.getIntakeArmEncoder());
        SmartDashboard.putBoolean("Break 1", cargoIntake.isBallDetected());
        SmartDashboard.putBoolean("Break 2", cargoIntake.isBallConsumed());
        SmartDashboard.putBoolean("Claw Limit", claw.getClawLimit());
        SmartDashboard.putBoolean("Intake Limit", cargoIntake.getCargoLimit());
        SmartDashboard.putBoolean("Mast Limit", lift.getLowLimit());*/
        //SmartDashboard.putBoolean("Panel Detector", claw.isPanelPresent());
        //SmartDashboard.putString("Claw State", claw.getClawState().toString());
        /*SmartDashboard.putNumber("Claw Arm Encoder", claw.getArmEncoder());

        SmartDashboard.putString("Claw Arm State", claw.getClawArmState().toString());
        SmartDashboard.putNumber("Target", lemonlight.getTarget());

        /*claw.panelSensor.read();
        SmartDashboard.putNumber("Red", claw.panelSensor.red);
        SmartDashboard.putNumber("Green", claw.panelSensor.green);
        SmartDashboard.putNumber("Blue", claw.panelSensor.blue);

        Shuffleboard.getTab("Main").add("Current PSI", pressureSensor.getPressure());
        Shuffleboard.getTab("Main").add("Voltage", RobotController.getBatteryVoltage());*/

        SmartDashboard.putNumber("turn Power", OI.getInstance().driver1.getRotationalPower());
        SmartDashboard.putNumber("Current 1", drivetrain.leftDrive0.getOutputCurrent());
        SmartDashboard.putNumber("Current 2", drivetrain.leftDrive1.getOutputCurrent());
        SmartDashboard.putNumber("Current 3", drivetrain.leftDrive2.getOutputCurrent());
        SmartDashboard.putNumber("Current 4", drivetrain.rightDrive0.getOutputCurrent());
        SmartDashboard.putNumber("Current 5", drivetrain.rightDrive1.getOutputCurrent());
        SmartDashboard.putNumber("Current 6", drivetrain.rightDrive2.getOutputCurrent());


    }
}