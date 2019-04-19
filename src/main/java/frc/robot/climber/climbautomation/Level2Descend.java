package frc.robot.climber.climbautomation;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.cargointake.CargoIntake;
import frc.robot.cargointake.cargocommands.EnableRollers;
import frc.robot.cargointake.cargocommands.SetCargoArm;
import frc.robot.climber.Climb;
import frc.robot.climber.climbcommands.EngageFrontPistons;
import frc.robot.climber.climbcommands.EngageRearPiston;
import frc.robot.drivetrain.Drivetrain;
import frc.robot.drivetrain.drivetraincommands.EncoderDrive;
import frc.robot.drivetrain.drivetraincommands.PowerDrive;
import frc.robot.robotcore.universalcommands.Wait;

public class Level2Descend extends CommandGroup {

    Climb climb = Climb.getInstance();

    public Level2Descend() {
        requires(climb);
        requires(Drivetrain.getInstance());
        setInterruptible(false);

        addSequential(new EngageRearPiston(Value.kForward));
        addSequential(new Wait(0.8));
        addParallel(new TempCommandForDescend());
        addSequential(new PowerDrive(-0.5, 2));

   }
}