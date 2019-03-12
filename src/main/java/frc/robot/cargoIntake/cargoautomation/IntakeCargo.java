package frc.robot.cargointake.cargoautomation;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.cargointake.CargoIntake;
import frc.robot.cargointake.cargocommands.EnableRollers;
import frc.robot.cargointake.cargocommands.TrimCargoArm;

public class IntakeCargo extends CommandGroup{

    public IntakeCargo(){
        addSequential(new EnableRollers().new SetRollers(CargoIntake.RollerState.ON));
        //addSequential(new DetectCargo(Intake.CargoPosition.STAGE_2));
        //addSequential(new Wait(2.0));
        //addSequential(new ActuateIntake().new SetIntake(Intake.IntakeSpinState.OFF));
        addSequential(new TrimCargoArm());
        addSequential(new EnableRollers().new IntakeForTime(CargoIntake.RollerState.ON, 0.25));
        addSequential(new EnableRollers().new SetRollers(CargoIntake.RollerState.OFF));
    }
}