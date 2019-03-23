package frc.robot.robotcore.universalcommands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.lift.Lift;
import frc.robot.lift.Lift.LiftState;
import frc.robot.lift.liftcommands.MoveMast;
import frc.robot.panelclaw.chairautomation.EjectPanel;

public class LiftEject extends CommandGroup{

    public LiftEject(LiftState pos){
        addSequential(new MoveMast(pos));
        addSequential(new EjectPanel());
    }
}