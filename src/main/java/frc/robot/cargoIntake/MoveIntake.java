package frc.robot.cargoIntake;

import edu.wpi.first.wpilibj.command.Command;

public class MoveIntake extends Command{
    private Intake intake = Intake.getInstance();

    public MoveIntake(){
        requires(intake);

    }
    @Override
    protected void initialize() {
        super.initialize();
    }
    @Override
    protected void execute() {
        
    }
    @Override
    protected boolean isFinished() {
        return false;
    }
    @Override
    protected void end() {
        super.end();
    }
}