package frc.robot.panelclaw.chairautomation;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.lift.Lift.LiftState;
import frc.robot.lift.liftcommands.MoveMast;
import frc.robot.panelclaw.Claw;
import frc.robot.panelclaw.Peg;
import frc.robot.panelclaw.Peg.PegState;
import frc.robot.panelclaw.pegcommands.ActuateChair;
import frc.robot.panelclaw.pegcommands.ActuatePeg;
import frc.robot.robotcore.universalcommands.Wait;

public class EjectPanel extends CommandGroup{
    private Claw claw = Claw.getInstance();

    public EjectPanel(){
        requires(claw);

        addSequential(new ActuatePeg(). new SetPeg(PegState.DOWN));
        addSequential(new Wait(0.15)); 
        addSequential(new ActuateChair().new SetChair(Peg.PneumaticState.OUT));
        addSequential(new Wait(0.15));
        addSequential(new ActuateChair().new SetChair(Peg.PneumaticState.IN));
        addSequential(new Wait(1.0));
        addSequential(new ActuatePeg().new SetPeg(PegState.UP));
        //addSequential(new MoveMast(LiftState.LOW));
    }
}