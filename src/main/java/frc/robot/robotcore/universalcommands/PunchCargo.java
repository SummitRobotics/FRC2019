package frc.robot.robotcore.universalcommands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.panelclaw.Peg;
import frc.robot.panelclaw.pegcommands.ActuatePeg;
import frc.robot.panelclaw.pegcommands.BopIt;

public class PunchCargo extends CommandGroup{

    public PunchCargo(){
        addSequential(new ActuatePeg().new SetPeg(Peg.PegState.DOWN));
        addSequential(new Wait(0.1));
        addSequential(new BopIt().new SetBop(Peg.PneumaticState.OUT));
        addSequential(new Wait(0.25));
        addSequential(new BopIt().new SetBop(Peg.PneumaticState.IN));
        addSequential(new ActuatePeg().new SetPeg(Peg.PegState.UP));
    }
}