package frc.robot.cargointake.cargoautomation;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.cargointake.CargoIntake;
import frc.robot.cargointake.cargocommands.EnableRollers;
import frc.robot.panelclaw.Peg;
import frc.robot.panelclaw.pegcommands.ActuateChair;
import frc.robot.panelclaw.pegcommands.ActuatePeg;

public class EjectCargoToRocket extends CommandGroup{

    public EjectCargoToRocket(){
        addSequential(new ActuatePeg().new SetPeg(Peg.PegState.DOWN));
        addSequential(new ActuateChair().new SetChair(Peg.PneumaticState.IN));
        addSequential(new EnableRollers().new IntakeForTime(CargoIntake.RollerState.ON, 1));
    }
}