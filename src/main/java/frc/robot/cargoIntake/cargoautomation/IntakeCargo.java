package frc.robot.cargointake.cargoautomation;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.cargointake.CargoIntake;
import frc.robot.cargointake.cargocommands.DetectCargo;
import frc.robot.cargointake.cargocommands.EnableRollers;
import frc.robot.cargointake.cargocommands.TrimCargoArm;
import frc.robot.robotcore.universalcommands.Wait;

public class IntakeCargo extends CommandGroup{

    public IntakeCargo(){
        setInterruptible(true);
        addSequential(new EnableRollers().new SetRollers(CargoIntake.RollerState.ON));
        addSequential(new DetectCargo(CargoIntake.CargoPosition.CONSUMED));
        addSequential(new TrimCargoArm());
        addSequential(new Wait(0.5));
        addSequential(new EnableRollers().new SetRollers(CargoIntake.RollerState.OFF));
        /*addSequential(new EnableRollers().new IntakeForTime(CargoIntake.RollerState.ON, 0.25));
        addSequential(new EnableRollers().new SetRollers(CargoIntake.RollerState.OFF));*/
    }
}