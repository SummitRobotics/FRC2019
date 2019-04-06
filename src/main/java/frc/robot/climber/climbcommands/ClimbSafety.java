package frc.robot.climber.climbcommands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.climber.Climb;

public class ClimbSafety extends InstantCommand{
    private Climb climb = Climb.getInstance();

    public ClimbSafety(){
        requires(climb);

    }
    @Override
    protected void initialize() {
        super.initialize();
    }
    @Override
    protected void execute() {
        climb.engageFrontPistons(Value.kReverse);
        climb.engageRearPiston(Value.kReverse);
    }
    @Override
    protected void end() {
        super.end();
    }
}