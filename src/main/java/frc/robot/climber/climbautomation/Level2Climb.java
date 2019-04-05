package frc.robot.climber.climbautomation;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.cargointake.CargoIntake;
import frc.robot.cargointake.cargocommands.EnableRollers;
import frc.robot.cargointake.cargocommands.SetCargoArm;
import frc.robot.climber.Climb;
import frc.robot.climber.climbcommands.EngageFrontPistons;
import frc.robot.climber.climbcommands.EngageRearPiston;
import frc.robot.robotcore.universalcommands.Wait;

public class Level2Climb extends CommandGroup {

    Climb climb = Climb.getInstance();
    CargoIntake cargoIntake = CargoIntake.getInstance();

    public Level2Climb() {
        requires(climb);
        requires(cargoIntake);

        addSequential(new EngageFrontPistons(Value.kForward));
        addSequential(new EngageRearPiston(Value.kForward));
        addSequential(new Wait(1));
        addSequential(new SetCargoArm(CargoIntake.IntakeArmState.CLIMB));
        addSequential(new EnableRollers().new SetRollers(CargoIntake.RollerState.ON));
    }
}