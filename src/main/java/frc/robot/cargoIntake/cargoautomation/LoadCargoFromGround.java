package frc.robot.cargointake.cargoautomation;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.cargointake.CargoIntake;
import frc.robot.cargointake.cargocommands.SetCargoArm;
import frc.robot.panelclaw.Claw;
import frc.robot.panelclaw.Claw.ClawArmState;
import frc.robot.panelclaw.Claw.ClawState;
import frc.robot.panelclaw.clawcommands.ActuateClaw;
import frc.robot.panelclaw.clawcommands.RaiseClaw;
import frc.robot.robotcore.universalcommands.Wait;
import frc.robot.cargointake.cargocommands.EnableRollers;
import frc.robot.cargointake.cargocommands.DetectCargo;

public class LoadCargoFromGround extends CommandGroup{

    CargoIntake cargoIntake = CargoIntake.getInstance();
    Claw claw = Claw.getInstance();

    public LoadCargoFromGround(){
        setInterruptible(true);
        requires(cargoIntake);
        //requires(claw);

        SmartDashboard.putBoolean("Cargo killed", false);

        addSequential(new EnableRollers().new SetRollers(CargoIntake.RollerState.ON));
        addSequential(new SetCargoArm(CargoIntake.IntakeArmState.INTAKE_LOWER));
        addSequential(new Wait(1.0));
        addSequential(new DetectCargo(CargoIntake.CargoPosition.DETECTED));
        addSequential(new Wait(0.01));
        addSequential(new EnableRollers().new SetRollers(CargoIntake.RollerState.SLOW));
        addSequential(new Wait(0.01));
        addSequential(new SetCargoArm(CargoIntake.IntakeArmState.DOWN));
        addSequential(new Wait(0.01));
        addSequential(new DetectCargo(CargoIntake.CargoPosition.CONSUMED));
        addSequential(new EnableRollers().new SetRollers(CargoIntake.RollerState.OFF));
        addSequential(new SetCargoArm(CargoIntake.IntakeArmState.UP));
        addSequential(new Wait(3.0));
        //move claw down
        //addSequential(new RaiseClaw(ClawArmState.CARGO_DOWN));
        //close it
        //addSequential(new ActuateClaw().new SetClaw(ClawState.CLOSE));
        //addSequential(new EnableRollers().new IntakeForTime(CargoIntake.RollerState.ON, 2));
        //addSequential(new EnableRollers().new SetRollers(CargoIntake.RollerState.OFF));
        //move claw up
        //addSequential(new RaiseClaw(ClawArmState.UP));

        /*spin rollers
        **lower cargo arm partways
        **wait until cargo detected
        **spin rollers slower
        **lowers cargo arm fullways
        **wait until cargo present
        **stop after 0.25 sec
        **raise arm back up (until limit switch)
        **spin rollers again
        **stop rollers*/
    }

    @Override
    protected void interrupted() {
        super.interrupted();
        cargoIntake.kill();
        claw.kill();
    }
}