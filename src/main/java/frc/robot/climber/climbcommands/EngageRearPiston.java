package frc.robot.climber.climbcommands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.climber.Climb;

public class EngageRearPiston extends InstantCommand{
    private Climb climb = Climb.getInstance();

    private Value direction;
    
    public EngageRearPiston (Value direction){
        requires(climb);
        this.direction = direction;
    }

    @Override
    protected void initialize() {
        super.initialize();
    }
    @Override
    protected void execute() {
        climb.engageRearPiston(direction);
    }

}