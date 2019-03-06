package frc.robot.panelclaw.chairautomation;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.panelclaw.Peg;
import frc.robot.panelclaw.Peg.PegState;
import frc.robot.panelclaw.pegcommands.ActuateChair;
import frc.robot.panelclaw.pegcommands.ActuatePeg;
import frc.robot.robotcore.universalcommands.Wait;

public class IntakePanelNew extends CommandGroup{

    public IntakePanelNew(){
        addSequential(new ActuatePeg(). new SetPeg(PegState.DOWN));
        addSequential(new Wait(0.15)); /*0.15*/
        addSequential(new ActuateChair().new SetChair(Peg.PneumaticState.OUT));
        addSequential(new Wait(0.15));
        addSequential(new ActuateChair().new SetChair(Peg.PneumaticState.IN));
        addSequential(new Wait(1.0));
        addSequential(new ActuatePeg().new SetPeg(PegState.UP));
    }
}