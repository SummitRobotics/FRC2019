package frc.robot.climber.climbcommands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.climber.Climb;

public class EngageFrontPistons extends InstantCommand{
    private Climb climb = Climb.getInstance();

    private Value direction;
    
    public EngageFrontPistons (Value direction){
        requires(climb);
        this.direction = direction;
    }

    @Override
    protected void initialize() {
        super.initialize();
    }
    @Override
    protected void execute() {
        climb.engageFrontPistons(direction);
    }

}