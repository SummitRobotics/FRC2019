package frc.robot.cargointake.cargocommands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.cargointake.CargoIntake;

public class ResetCargoArm extends InstantCommand {

    private CargoIntake cargoIntake = CargoIntake.getInstance();

    public ResetCargoArm() {
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