package frc.robot.cargointake.cargoautomation;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.cargointake.CargoIntake;
import frc.robot.cargointake.cargocommands.EnableRollers;
import frc.robot.drivetrain.drivetraincommands.PowerDrive;
import frc.robot.panelclaw.Peg;
import frc.robot.panelclaw.chairautomation.PunchCargo;
import frc.robot.panelclaw.pegcommands.ActuateChair;
import frc.robot.panelclaw.pegcommands.ActuatePeg;
import frc.robot.robotcore.universalcommands.Wait;

public class EjectCargoShip extends CommandGroup{
    private CargoIntake cargoIntake = CargoIntake.getInstance();

    public EjectCargoShip(){
        requires(cargoIntake);
        addSequential(new EnableRollers().new IntakeForTime(CargoIntake.RollerState.ON, 1.0));
        addSequential(new ActuateChair().new SetChair(Peg.PneumaticState.IN));
        addSequential(new ActuatePeg().new SetPeg(Peg.PegState.UP));
        addSequential(new Wait(0.5));
        addSequential(new PowerDrive(-0.2, 1));
        addSequential(new PowerDrive(0.35, 0.75));
        addSequential(new PunchCargo());
    }
}