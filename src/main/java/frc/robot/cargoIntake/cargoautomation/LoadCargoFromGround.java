package frc.robot.cargointake.cargoautomation;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.cargointake.CargoIntake;
import frc.robot.cargointake.cargocommands.SetCargoArm;
import frc.robot.panelclaw.Claw;
import frc.robot.panelclaw.Claw.ClawState;
import frc.robot.panelclaw.Peg.PegState;
import frc.robot.panelclaw.Peg.PneumaticState;
import frc.robot.panelclaw.clawcommands.ActuateClaw;
import frc.robot.panelclaw.clawcommands.PowerMoveClawWrist;
import frc.robot.panelclaw.pegcommands.ActuateChair;
import frc.robot.panelclaw.pegcommands.ActuatePeg;
import frc.robot.robotcore.universalcommands.Wait;
import frc.robot.cargointake.cargocommands.EnableRollers;
import frc.robot.cargointake.cargocommands.DetectCargo;

public class LoadCargoFromGround extends CommandGroup{

    public LoadCargoFromGround(){
        setInterruptible(true);
        requires(CargoIntake.getInstance());
        //requires(claw);

        //Initialize with the claw closed, the rollers on, the peg up, and the chair in.
        addSequential(new ActuateClaw().new SetClaw(ClawState.CLOSE));
        addSequential(new EnableRollers().new SetRollers(CargoIntake.RollerState.ON));
        addSequential(new ActuateChair().new SetChair(PneumaticState.IN));
        addSequential(new ActuatePeg().new SetPeg(PegState.UP));

        //While initializing, move the claw wrist downwards. 
        //TODO - Make this command positional
        addParallel(new PowerMoveClawWrist(1.5, Claw.ClawSpeed.FORWARD));

        //Lower the cargo arm and wait to execute new commands.
        //TODO - Make SetCargoArm non-instant, remove waits
        addSequential(new SetCargoArm(CargoIntake.IntakeArmState.INTAKE_LOWER));
        addSequential(new Wait(1.0));

        //Wait until first break beam is broken (meaning cargo is in roller's grasp)
        addSequential(new DetectCargo(CargoIntake.CargoPosition.DETECTED));
        addSequential(new Wait(0.01));
        //Slow down the rollers
        addSequential(new EnableRollers().new SetRollers(CargoIntake.RollerState.SLOW));
        addSequential(new Wait(0.01));
        //Lower the cargo arm more to fully intake ball
        addSequential(new SetCargoArm(CargoIntake.IntakeArmState.DOWN));
        addSequential(new Wait(0.01));
        //Wait until second break beam is broken (meaning cargo has been fully sucked in)
        addSequential(new DetectCargo(CargoIntake.CargoPosition.CONSUMED));
        //Disable the rollers to hold the ball
        addSequential(new EnableRollers().new SetRollers(CargoIntake.RollerState.OFF));

        //Bring the cargo arm up, wait until arm is up to execute more commands.
        //TODO - Make SetCargoArm non-instant, remove waits
        addSequential(new SetCargoArm(CargoIntake.IntakeArmState.UP));
        addSequential(new Wait(1));

        //Eject Ball into Chair
        addSequential(new EnableRollers().new IntakeForTime(CargoIntake.RollerState.ON, 2));
        addSequential(new EnableRollers().new SetRollers(CargoIntake.RollerState.OFF));
        
        //move claw up
        addSequential(new PowerMoveClawWrist(.25, Claw.ClawSpeed.REVERSE));

    }

    @Override
    protected void interrupted() {
        super.interrupted();
        CargoIntake.getInstance().kill();
        Claw.getInstance().kill();
    }
}