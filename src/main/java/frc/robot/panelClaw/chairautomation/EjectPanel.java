package frc.robot.panelclaw.chairautomation;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.drivetrain.Drivetrain;
import frc.robot.drivetrain.drivetraincommands.PowerDrive;
import frc.robot.panelclaw.Claw;
import frc.robot.panelclaw.Peg;
import frc.robot.panelclaw.Peg.PegState;
import frc.robot.panelclaw.pegcommands.ActuateChair;
import frc.robot.panelclaw.pegcommands.ActuatePeg;
import frc.robot.robotcore.universalcommands.Wait;

public class EjectPanel extends CommandGroup{

    private Claw claw = Claw.getInstance();

    public EjectPanel(){
        setInterruptible(true);
        requires(Peg.getInstance());
        requires(claw);
        
        addSequential(new ActuateChair().new SetChair(Peg.PneumaticState.IN));
        addSequential(new ActuatePeg().new SetPeg(PegState.DOWN));
        addSequential(new PowerDrive(0.25, false, 0.25));
        addSequential(new Wait(0.1)); 
        addSequential(new ActuateChair().new SetChair(Peg.PneumaticState.OUT));
        addSequential(new Wait(0.2));
        addSequential(new ActuateChair().new SetChair(Peg.PneumaticState.IN));
        addSequential(new Wait(0.25));
        //addSequential(new ActuatePeg().new SetPeg(PegState.UP));
        //addSequential(new MoveMast(LiftState.LOW));
    }

    @Override
    protected void interrupted() {
        super.interrupted();
        Drivetrain.getInstance().kill();
        claw.kill();
    }
}