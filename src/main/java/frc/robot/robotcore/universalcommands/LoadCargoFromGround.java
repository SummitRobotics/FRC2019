package frc.robot.robotcore.universalcommands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.cargointake.Intake;
import frc.robot.cargointake.cargocommands.MoveIntake;
import frc.robot.cargointake.cargocommands.ActuateIntake;
import frc.robot.cargointake.cargocommands.DetectCargo;

public class LoadCargoFromGround extends CommandGroup{
    public LoadCargoFromGround(){
        addSequential(new ActuateIntake().new SetIntake(Intake.IntakeSpinState.ON));
        addSequential(new MoveIntake(Intake.IntakeState.INTAKE_LOWER));
        addSequential(new DetectCargo(Intake.CargoPosition.STAGE_1));
        addSequential(new ActuateIntake().new SetIntake(Intake.IntakeSpinState.SLOW));
        addSequential(new MoveIntake(Intake.IntakeState.DOWN));
        addSequential(new DetectCargo(Intake.CargoPosition.STAGE_2));
        addSequential(new ActuateIntake().new SetIntake(Intake.IntakeSpinState.ON, 0.25));
        addSequential(new MoveIntake(Intake.IntakeState.UP));
        addSequential(new ActuateIntake().new SetIntake(Intake.IntakeSpinState.ON), 2);
        addSequential(new ActuateIntake().new SetIntake(Intake.IntakeSpinState.OFF));
        //spin rollers
        //lower cargo arm partways
        //wait until cargo detected
        //spin rollers slower
        //lowers cargo arm fullways
        //wait until cargo present
        //stop after 0.25 sec
        //raise arm back up (until limit switch)
        //spin rollers again
        //stop rollers
    }
}