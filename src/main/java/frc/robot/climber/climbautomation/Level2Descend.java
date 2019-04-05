package frc.robot.climber.climbautomation;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.cargointake.CargoIntake;
import frc.robot.cargointake.cargocommands.EnableRollers;
import frc.robot.cargointake.cargocommands.SetCargoArm;
import frc.robot.climber.Climb;
import frc.robot.climber.climbcommands.EngageFrontPistons;
import frc.robot.climber.climbcommands.EngageRearPiston;
import frc.robot.drivetrain.drivetraincommands.EncoderDrive;
import frc.robot.robotcore.universalcommands.Wait;

public class Level2Descend extends CommandGroup {

    Climb climb = Climb.getInstance();
    CargoIntake cargoIntake = CargoIntake.getInstance();

    public Level2Descend() {
        requires(climb);
        requires(cargoIntake);

        addSequential(new Wait(2));
        addSequential(new EncoderDrive(3));
        addSequential(new Wait(5));
        addSequential(new EngageFrontPistons(Value.kForward));
        addSequential(new Wait(0.7));
        addSequential(new Wait(5));
        addSequential(new EncoderDrive(3));
        addSequential(new Wait(0.7));
        addSequential(new Wait(5));
        addSequential(new EngageRearPiston(Value.kForward));
        addSequential(new Wait(0.5));
        addSequential(new Wait(5));
        addSequential(new EncoderDrive(3));
        addSequential(new Wait(5));
        addSequential(new EngageFrontPistons(Value.kReverse));
        addSequential(new EngageRearPiston(Value.kReverse));
        addSequential(new Wait(1.2));
        addSequential(new Wait(5));
        addSequential(new EncoderDrive(12));
   }
}