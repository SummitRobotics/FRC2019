package frc.robot.lift.liftcommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.robotcore.OI;
import frc.robot.lift.Lift;

public class MoveMastManual extends Command{
    private Lift lift = Lift.getInstance();
    public MoveMastManual(){
        requires(lift);
    }
    @Override
    protected void initialize() {
        super.initialize();
    }
    @Override
    protected void execute() {
        double input = OI.getInstance().deadzone(OI.getInstance().getLeftJoystickY());
        lift.runLiftManual(input);
        /*if(input < 0 && lift.getLowLimit()){
            lift.runLiftManual(0);
        }*/
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