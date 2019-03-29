package frc.robot.cargointake.cargocommands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.cargointake.CargoIntake;

public class ResetArmEncoder extends InstantCommand {

    private CargoIntake cargoIntake = CargoIntake.getInstance();

    public ResetArmEncoder() {
        requires(cargoIntake);
    }
    @Override
    protected void initialize() {
        super.initialize();
    }
    @Override
    protected void execute() {
        cargoIntake.setArmEncoder(0);
    }
    @Override
    protected void end(){
        super.end();
    }
}