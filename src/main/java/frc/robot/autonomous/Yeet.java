package frc.robot.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.climber.climbautomation.Level2Descend;
import frc.robot.drivetrain.Drivetrain;
import frc.robot.drivetrain.drivetraincommands.EncoderDrive;
import frc.robot.drivetrain.drivetraincommands.GyroTurn;
import frc.robot.drivetrain.drivetraincommands.PowerDrive;
import frc.robot.robotcore.universalcommands.Wait;

public class Yeet extends CommandGroup{

    public Yeet(){
        setInterruptible(true);
        requires(Drivetrain.getInstance());

        addSequential(new Level2Descend());
        addSequential(new Wait(0.5));
        addSequential(new GyroTurn(10));
        addSequential(new PowerDrive(-0.7, 1.5));
        addSequential(new GyroTurn(45));

    }

    @Override
    protected void interrupted() {
        super.interrupted();
        Drivetrain.getInstance().kill();
    }
}