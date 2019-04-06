package frc.robot.cargointake.cargocommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.cargointake.CargoIntake;

public class HoldEject extends Command {
    
    CargoIntake cargoIntake = CargoIntake.getInstance();

    public HoldEject(){
        requires(cargoIntake);
    }
    @Override
    protected void execute() {
        super.execute();
        cargoIntake.setRollers(CargoIntake.RollerState.REVERSE);
    }
    @Override
    protected boolean isFinished() {
        return false;
    }
    @Override
    protected void interrupted() {
        super.interrupted();
    }
    @Override
    protected void end() {
        super.end();
        cargoIntake.setRollers(CargoIntake.RollerState.OFF);
    }
}