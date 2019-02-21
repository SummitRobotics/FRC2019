package frc.robot.robotcore.universalcommands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.cargointake.Intake;
import frc.robot.cargointake.cargocommands.ActuateIntake;
import frc.robot.cargointake.cargocommands.DetectCargo;
import frc.robot.cargointake.cargocommands.MoveCargoWrist;

public class IntakeCargo extends CommandGroup{

    public IntakeCargo(){
        addSequential(new ActuateIntake().new IntakeBall());
        //addSequential(new DetectCargo(Intake.CargoPosition.STAGE_2));
        //addSequential(new Wait(2.0));
        //addSequential(new ActuateIntake().new SetIntake(Intake.IntakeSpinState.OFF));
        addSequential(new MoveCargoWrist());
        addSequential(new ActuateIntake().new SetIntake(Intake.IntakeSpinState.ON, 0.25));
        addSequential(new ActuateIntake().new SetIntake(Intake.IntakeSpinState.OFF));
    }
}