package frc.robot.climber.climbcommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.climber.Climb;

public class EngageClimb extends Command{
    private Climb climb = Climb.getInstance();
    
    public EngageClimb(){
        requires(climb);
    }

    @Override
    protected void initialize() {
        super.initialize();
    }
    @Override
    protected void execute() {
        climb.climbLevel2();
    }
    @Override
    protected boolean isFinished() {
        return false;
    }
    @Override
    protected void end() {
        super.end();
        climb.revertClimb();
    }
}