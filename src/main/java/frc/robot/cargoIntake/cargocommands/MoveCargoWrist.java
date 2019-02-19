package frc.robot.cargointake.cargocommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.cargointake.Intake;
import frc.robot.robotcore.OI;

public class MoveCargoWrist extends Command{
    private Intake intake = Intake.getInstance();
    //private OI gamepad = OI.getInstance();
    private final double POWER = 0.3;

    public MoveCargoWrist(){
        requires(intake);
    }
    @Override
    protected void initialize() {
        super.initialize();
    }
    @Override
    protected void execute() {
        if(Robot.gamepad.isDpadUp()){
            intake.moveIntakeArm(POWER);
        }
        else if(Robot.gamepad.isDpadDown()){
            intake.moveIntakeArm(-POWER);
        }
        else{
            intake.moveIntakeArm(0);
        }
    }
    @Override
    protected boolean isFinished() {
        return false;
    }
    @Override
    protected void end() {
        super.end();
        intake.moveIntakeArm(0);
    }
}