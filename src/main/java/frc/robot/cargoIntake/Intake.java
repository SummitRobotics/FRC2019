package frc.robot.cargoIntake;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotConstants;

public class Intake extends Subsystem{
    private TalonSRX arm, rollers;
    private final double ROTATION_DEG = 120;
    private final double ROTATION_RAD = Math.toRadians(ROTATION_DEG);
    private boolean isIntakeRaised;
    
    public Intake(){
        arm = new TalonSRX(RobotConstants.Ports.INTAKE_ARM);
        arm.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);

        rollers = new TalonSRX(RobotConstants.Ports.INTAKE_ROLLER);
    }
    @Override
    protected void initDefaultCommand() {
        
    }

    public void intake(int direction){
        rollers.set(ControlMode.PercentOutput, 1 * direction);
    }

    public void raiseIntake(){
        if(!isIntakeRaised){
            while(arm.getSelectedSensorPosition() < (ROTATION_RAD * RobotConstants.TALON_TICKS_PER_ROT)){
                arm.set(ControlMode.PercentOutput, 0.2);
            }
        }
    }
}