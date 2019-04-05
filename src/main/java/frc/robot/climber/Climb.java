package frc.robot.climber;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.robotcore.RobotConstants;

public class Climb extends Subsystem{

    private DoubleSolenoid climbPistons, backPistons;

    private static Climb instance;
    private Climb(){
        climbPistons = new DoubleSolenoid(RobotConstants.Ports.PCM_2, RobotConstants.Ports.CLIMB_RELEASE_OPEN, RobotConstants.Ports.CLIMB_RELEASE_CLOSE);
        backPistons = new DoubleSolenoid(RobotConstants.Ports.PCM_2, RobotConstants.Ports.CLIMB_RELEASE_BACK_OPEN, RobotConstants.Ports.CLIMB_RELEASE_BACK_CLOSE);
    }

    public static Climb getInstance(){
        if(instance == null){
            instance = new Climb();
        }
        return instance;
    }

    @Override
    protected void initDefaultCommand() {
        //No default command required
    }

    /* ----- CLIMB ACTIONS ----- */

    public void engageFrontPistons(Value direction){
        climbPistons.set(direction);
    }
    public void engageRearPiston(Value direction){
        backPistons.set(direction);
    }
}