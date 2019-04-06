package frc.robot.cargointake.cargocommands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.cargointake.CargoIntake;
import frc.robot.robotcore.universalcommands.Wait;

public class StupidEnableRollersEncapsulation extends CommandGroup{

    public StupidEnableRollersEncapsulation(){
        addSequential(new EnableRollers().new SetRollers(CargoIntake.RollerState.ON));
        addSequential(new Wait(2));
        addSequential(new EnableRollers().new SetRollers(CargoIntake.RollerState.OFF));
    }
}