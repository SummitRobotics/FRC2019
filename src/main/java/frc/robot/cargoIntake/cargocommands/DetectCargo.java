package frc.robot.cargointake.cargocommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.cargointake.CargoIntake;
import frc.robot.robotcore.userinput.OI;

public class DetectCargo extends Command{
    private CargoIntake cargoIntake = CargoIntake.getInstance();
    private CargoIntake.CargoPosition detectionLevel;

    public DetectCargo(CargoIntake.CargoPosition detectionLevel){
        setInterruptible(true);
        requires(cargoIntake);

        this.detectionLevel = detectionLevel;
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
        if(detectionLevel == CargoIntake.CargoPosition.DETECTED){
            return cargoIntake.isBallDetected();
        }
        else if(detectionLevel == CargoIntake.CargoPosition.CONSUMED){
            return cargoIntake.isBallConsumed();
        }
        else{
            return true;
        }
    }
    @Override
    protected void interrupted() {
        super.interrupted();
        cargoIntake.kill();
    }
    @Override
    protected void end() {
        super.end();
        System.out.println("Detection Sequence Finished");
    }
}
