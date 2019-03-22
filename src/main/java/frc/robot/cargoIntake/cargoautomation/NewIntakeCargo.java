package frc.robot.cargointake.cargoautomation;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.cargointake.CargoIntake;
import frc.robot.cargointake.cargocommands.DetectCargo;
import frc.robot.cargointake.cargocommands.EnableRollers;

public class NewIntakeCargo extends CommandGroup{
    private CargoIntake cargoIntake = CargoIntake.getInstance();

    public NewIntakeCargo(){
        setInterruptible(true);
        /*addSequential(command);
        addSequential(new EnableRollers().new SetRollers(CargoIntake.RollerState.ON));
        addSequential(new DetectCargo(CargoIntake.CargoPosition.CONSUMED));
        addSequential(new TrimCargoArm());
        addSequential(new Wait(0.5));
        addSequential(new EnableRollers().new SetRollers(CargoIntake.RollerState.OFF));
        addSequential(new EnableRollers().new IntakeForTime(CargoIntake.RollerState.ON, 0.25));
        addSequential(new EnableRollers().new SetRollers(CargoIntake.RollerState.OFF));*/
    }
}