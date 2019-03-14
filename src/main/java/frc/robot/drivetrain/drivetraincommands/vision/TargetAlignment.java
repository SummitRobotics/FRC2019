package frc.robot.drivetrain.drivetraincommands.vision;

import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.robot.robotcore.userinput.OI;
import frc.robot.devices.Limelight;
import frc.robot.drivetrain.Drivetrain;

public class TargetAlignment extends PIDCommand {

    private Drivetrain drivetrain = Drivetrain.getInstance();
    private Limelight lemonlight = Limelight.getInstance();

    private static final double
        P = 0.01,
        I = 0.0,
        D = 0.001;
    //private final double kP = 0.0265;
    private double min_command = 0.0;
    private double leftFwd, rightFwd, power;
    private static final double POWER = 0.5;


    public TargetAlignment(){
        super("TargetAlignment", P, I, D, Drivetrain.getInstance());    
        setSetpoint(0);
        getPIDController().setPercentTolerance(5);
        this.power = POWER;
    }

    @Override
    protected void initialize() {
        super.initialize();
        lemonlight.enableLights();
    }
    @Override
    protected void execute() {
    }
    @Override
    protected void usePIDOutput(double output) {
        double steeringAdjust = 0;
        leftFwd = -power;
        rightFwd = -power;

        if(lemonlight.getTarget() == 0){
            drivetrain.robotDrive.tankDrive(0, 0);
            end();
        }
        else if(lemonlight.getX() > 1.0){
            steeringAdjust = output - min_command;
            leftFwd += steeringAdjust;
            rightFwd -= steeringAdjust;
            drivetrain.robotDrive.tankDrive(leftFwd, rightFwd);
        }
        else if(lemonlight.getX() < 1.0){
            steeringAdjust = output + min_command;
            leftFwd += steeringAdjust;
            rightFwd -= steeringAdjust;
            drivetrain.robotDrive.tankDrive(leftFwd, rightFwd);
        }
    }
    @Override
    protected boolean isFinished() {
        return lemonlight.getTarget() == 0;
    }
    @Override
    protected void end() {
        super.end();
        drivetrain.stopRobot();
    }
    @Override
    protected double returnPIDInput() {
        return lemonlight.getX();
    }
}