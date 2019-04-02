package frc.robot.panelclaw.chairautomation;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.panelclaw.Claw;
import frc.robot.panelclaw.Peg;
import frc.robot.panelclaw.Claw.ClawState;
import frc.robot.panelclaw.clawcommands.ActuateClaw;
import frc.robot.panelclaw.clawcommands.PowerMoveClawWrist;
import frc.robot.panelclaw.pegcommands.ActuatePeg;
import frc.robot.panelclaw.pegcommands.BopIt;
import frc.robot.robotcore.universalcommands.Wait;

public class PunchCargo extends CommandGroup{
    private Peg peg = Peg.getInstance();
    private Claw claw = Claw.getInstance();

    public PunchCargo(){
        setInterruptible(true);
        requires(peg);
        requires(claw);

        addSequential(new ActuateClaw().new SetClaw(ClawState.OPEN));
        addSequential(new Wait(.1));
        addSequential(new ActuatePeg().new SetPeg(Peg.PegState.DOWN));
        addSequential(new Wait(0.1));
        addSequential(new BopIt().new SetBop(Peg.PneumaticState.OUT));
        addSequential(new Wait(0.25));
        addSequential(new BopIt().new SetBop(Peg.PneumaticState.IN));
        addSequential(new ActuatePeg().new SetPeg(Peg.PegState.UP));
        addSequential(new PowerMoveClawWrist(1, Claw.ClawSpeed.REVERSE));
        addSequential(new ActuateClaw().new SetClaw(ClawState.CLOSE));
    }
}