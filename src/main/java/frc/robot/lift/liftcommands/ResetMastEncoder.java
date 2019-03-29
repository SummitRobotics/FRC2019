package frc.robot.lift.liftcommands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.lift.Lift;

public class ResetMastEncoder extends InstantCommand {

    Lift lift = Lift.getInstance();

    public ResetMastEncoder() {
        requires(lift);
    }

    @Override
    protected void initialize(){
        super.initialize();
    }
    @Override
    protected void execute() {
        lift.setEncoderPos(0);
    }
    @Override
    protected void end() {
        super.end();
    }
}