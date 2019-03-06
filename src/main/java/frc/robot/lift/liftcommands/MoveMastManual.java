package frc.robot.lift.liftcommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.robotcore.userinput.OI;
import frc.robot.lift.Lift;

public class MoveMastManual extends Command{
    private Lift lift = Lift.getInstance();
    private OI gamepad = OI.getInstance();

    public MoveMastManual(){
        requires(lift);
    }
    @Override
    protected void initialize() {
        super.initialize();
    }
    @Override
    protected void execute() {
        lift.runLiftManual(gamepad.mastDrive());
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