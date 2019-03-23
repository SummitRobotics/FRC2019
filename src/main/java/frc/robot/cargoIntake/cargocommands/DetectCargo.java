package frc.robot.cargointake.cargocommands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.cargointake.CargoIntake;

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
        SmartDashboard.putBoolean("Is Finished ", cargoIntake.isBallDetected());
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
    }
}
