package frc.robot.cargointake.cargoautomation;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.cargointake.CargoIntake;
import frc.robot.cargointake.cargocommands.SetCargoArm;
import frc.robot.cargointake.cargocommands.EnableRollers;
import frc.robot.cargointake.cargocommands.DetectCargo;

public class LoadCargoFromGround extends CommandGroup{
    public LoadCargoFromGround(){
        addSequential(new EnableRollers().new SetRollers(CargoIntake.RollerState.ON));
        addSequential(new SetCargoArm(CargoIntake.IntakeArmState.INTAKE_LOWER));
        addSequential(new DetectCargo(CargoIntake.CargoPosition.DETECTED));
        addSequential(new EnableRollers().new SetRollers(CargoIntake.RollerState.SLOW));
        addSequential(new SetCargoArm(CargoIntake.IntakeArmState.DOWN));
        addSequential(new DetectCargo(CargoIntake.CargoPosition.CONSUMED));
        addSequential(new EnableRollers().new IntakeForTime(CargoIntake.RollerState.ON, 0.25));
        addSequential(new SetCargoArm(CargoIntake.IntakeArmState.UP));
        addSequential(new EnableRollers().new SetRollers(CargoIntake.RollerState.ON), 2);
        addSequential(new EnableRollers().new SetRollers(CargoIntake.RollerState.OFF));

        /*spin rollers
        **lower cargo arm partways
        **wait until cargo detected
        **spin rollers slower
        **lowers cargo arm fullways
        **wait until cargo present
        **stop after 0.25 sec
        **raise arm back up (until limit switch)
        **spin rollers again
        **stop rollers*/
    }
}