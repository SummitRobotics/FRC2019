package frc.robot.cargointake.cargocommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.cargointake.Intake;

public class DetectCargo extends Command{
    private Intake intake = Intake.getInstance();
    private Intake.CargoPosition detectPosition;

    public DetectCargo(Intake.CargoPosition detectPosition){
        requires(intake);
        this.detectPosition = detectPosition;
    }
    @Override
    protected void initialize() {
        super.initialize();
    }
    @Override
    protected void execute() {
        super.execute();
    }
    @Override
    protected boolean isFinished() {
        if(detectPosition == Intake.CargoPosition.STAGE_1){
            return intake.isBallDetected();
        }
        else if(detectPosition == Intake.CargoPosition.STAGE_2){
            return intake.isBallPresent();
        }
        else{
            return true;
        }
    }
    @Override
    protected void end() {
        super.end();
    }
}
