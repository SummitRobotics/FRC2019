package frc.robot.drivetrain.drivetraincommands.vision;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.devices.Limelight;
import frc.robot.drivetrain.Drivetrain;

public class TargetDrive extends Command {

    private Drivetrain drivetrain = Drivetrain.getInstance();
    private Limelight lemonlight = Limelight.getInstance();

    private static final double
        TURN_P = 0.01,
        TURN_I = 0.0,
        TURN_D = 0.0,
        TURN_F = 0.0;
    private static final double
        FWD_P = 0.01,
        FWD_I = 0.0,
        FWD_D = 0.0,
        FWD_F = 0.0;
    private double leftFwd, rightFwd;
    private PIDController fwdPID, turnPID;
    private PIDSource fwdSource, turnSource;
    private PIDOutput fwdOutput, turnOutput;


    public TargetDrive(){
        fwdSource = new PIDSource(){
        
            @Override
            public void setPIDSourceType(PIDSourceType pidSource) {
                setPIDSourceType(PIDSourceType.kDisplacement);
            }
        
            @Override
            public double pidGet() {
                return lemonlight.getAreaError();
            }
        
            @Override
            public PIDSourceType getPIDSourceType() {
                return getPIDSourceType();
            }
        };

        turnSource = new PIDSource(){
        
            @Override
            public void setPIDSourceType(PIDSourceType pidSource) {
                setPIDSourceType(PIDSourceType.kDisplacement);
            }
        
            @Override
            public double pidGet() {
                return lemonlight.getX();
            }
        
            @Override
            public PIDSourceType getPIDSourceType() {
                return getPIDSourceType();
            }
        };
        
        fwdPID = new PIDController(FWD_P, FWD_I, FWD_D, FWD_F, fwdSource, fwdOutput);
        turnPID = new PIDController(TURN_P, TURN_I, TURN_D, TURN_F, turnSource, turnOutput);

        fwdOutput = new PIDOutput(){
        
            @Override
            public void pidWrite(double output) {
                fwdPID.get();
            }
        };

        turnOutput = new PIDOutput(){
        
            @Override
            public void pidWrite(double output) {
                output = turnPID.get();
            }
        };
    }

    @Override
    protected void initialize() {
        super.initialize();
        lemonlight.enableLights();
        fwdPID.setSetpoint(0);
        turnPID.setSetpoint(0);
    }
    @Override
    protected void execute() {
        double fwdAdjust = 0, turnAdjust = 0;
        
        if(lemonlight.isTarget()){
            fwdAdjust = fwdPID.getError();
            turnAdjust = fwdPID.getError();
        }
        drivetrain.robotDrive.arcadeDrive(fwdAdjust, turnAdjust);
    }
    /*@Override
    protected void usePIDOutput(double output) {
        double steeringAdjust = 0;
        leftFwd = power;
        rightFwd = power;

        SmartDashboard.putNumber("Target", lemonlight.getTarget());

        if(lemonlight.getTarget() != 1){
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
        
    }*/
    @Override
    protected boolean isFinished() {
        return false;
    }
    @Override
    protected void end() {
        super.end();
        drivetrain.stopRobot();
    }
}