package frc.robot.robotcore;

import frc.robot.panelclaw.Claw;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.cargointake.CargoIntake;
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
        drivetrain.shiftGear(Drivetrain.GearState.HIGH);
        //todo - pwm absolute shit
        cargoIntake.setArmEncoder(0);
        cargoIntake.setRollers(CargoIntake.RollerState.OFF);
        peg.setPeg(Peg.PegState.UP);
        peg.setChair(Peg.PneumaticState.IN);
        peg.setBop(Peg.PneumaticState.IN);
        lemonlight.disableLights();
        claw.setClaw(Claw.ClawState.OPEN);
    }
    
    public void run(){
        claw.run();
        display.run();
    }
    public void dashboard(){
        SmartDashboard.putNumber("Current PSI", pressureSensor.getPressure());
        /*SmartDashboard.putNumber("Intake Arm Encoder",cargoIntake.getIntakeArmEncoder());
        SmartDashboard.putBoolean("Break 1", cargoIntake.isBallDetected());
        SmartDashboard.putBoolean("Break 2", cargoIntake.isBallConsumed());
        SmartDashboard.putBoolean("Claw Limit", claw.getClawLimit());
        SmartDashboard.putBoolean("Intake Limit", cargoIntake.getCargoLimit());
        SmartDashboard.putBoolean("Mast Limit", lift.getLowLimit());
        SmartDashboard.putBoolean("Panel Detector", claw.isPanelPresent());*/
        SmartDashboard.putString("Claw State", claw.getClawState().toString());
        SmartDashboard.putNumber("Claw Arm Encoder", claw.getArmEncoder());

        SmartDashboard.putString("Claw Arm State", claw.getClawArmState().toString());

        claw.panelSensor.read();
        SmartDashboard.putNumber("Red", claw.panelSensor.red);
        SmartDashboard.putNumber("Green", claw.panelSensor.green);
        SmartDashboard.putNumber("Blue", claw.panelSensor.blue);
    }
}