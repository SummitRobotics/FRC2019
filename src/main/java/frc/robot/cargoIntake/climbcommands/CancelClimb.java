package frc.robot.cargointake.climbcommands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.cargointake.CargoIntake;
import frc.robot.drivetrain.Drivetrain;

public class CancelClimb extends InstantCommand{

    private CargoIntake cargointake = CargoIntake.getInstance();

    public CancelClimb(){

    }

    @Override
    protected void initialize() {

    }
    @Override
    protected void execute() {
        cargointake.dontClimbLevel2();
    }
    
    @Override
    protected boolean isFinished() {
        return true;
    }
    @Override
    protected void end() {
        super.end();
    }
    
}